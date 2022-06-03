package com.example.demo.controller;

import com.example.demo.models.View;
import com.example.demo.services.ViewService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(value = ViewController.class)
@WithMockUser
public class ViewControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ViewService userService;

    LocalDateTime myDateObj = LocalDateTime.now();
    String stringDate= myDateObj.toString();
    View mockView = new View("userID","houseID",new Date());

    String mockViewJSON="{\"userID\":\"userID\",\"houseID\":\"houseID\"}";

    @Test
    public void getAllViewsTest() throws Exception {
        View mockView2 = new View("userID2","houseID2",new Date());
        View mockView3 = new View("userID3","houseID3",new Date());
        List<View> mockViews= new ArrayList<>();
        mockViews.add(mockView);
        mockViews.add(mockView2);
        mockViews.add(mockView3);
        Page<View> pageViews= new PageImpl<>(mockViews);

        Mockito.when(userService.allViews(Mockito.any(Pageable.class))).thenReturn(pageViews);
        RequestBuilder requestBuilderGet = MockMvcRequestBuilders.get(
                "/views/").accept(
                MediaType.APPLICATION_JSON);

        mockMvc.perform(requestBuilderGet).andDo(print()).andExpect(status().isOk())
                .andExpect(jsonPath("$.items",hasSize(3)));
    }

    @Test
    public void getAllByUser() throws Exception {
        View mockView3 = new View("userID","houseID3",new Date());
        List<View> mockViews= new ArrayList<>();
        mockViews.add(mockView);
        mockViews.add(mockView3);
        Page<View> pageViews= new PageImpl<>(mockViews);

        Mockito.when(userService.allByUser(Mockito.anyString(),Mockito.any(Pageable.class))).thenReturn(pageViews);
        RequestBuilder requestBuilderGet = MockMvcRequestBuilders.get(
                "/views/byUser/houseID").accept(
                MediaType.APPLICATION_JSON);

        mockMvc.perform(requestBuilderGet).andDo(print()).andExpect(status().isOk());
    }

    @Test
    public void getAllByHouse() throws Exception {
        View mockView2 = new View("userID2","houseID",new Date());
        List<View> mockViews= new ArrayList<>();
        mockViews.add(mockView);
        mockViews.add(mockView2);
        Page<View> pageViews= new PageImpl<>(mockViews);

        Mockito.when(userService.allByHouse(Mockito.anyString(),Mockito.any(Pageable.class))).thenReturn(pageViews);
        RequestBuilder requestBuilderGet = MockMvcRequestBuilders.get(
                "/views/byHouse/houseID").accept(
                MediaType.APPLICATION_JSON);

        mockMvc.perform(requestBuilderGet).andDo(print()).andExpect(status().isOk());
    }

    @Test
    public void registerViewTest() throws Exception{
        Mockito.when(userService.addNewView(Mockito.any(View.class))).thenReturn(mockView);

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/views/add")
                .accept(MediaType.APPLICATION_JSON).content(mockViewJSON)
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andDo(print()).andReturn();
        MockHttpServletResponse response = result.getResponse();
        assertEquals(HttpStatus.CREATED.value(), response.getStatus());
    }

    @Test
    public void deleteViewTest() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .delete("/views/delete/deleteID")
                .contentType(MediaType.APPLICATION_JSON);


        mockMvc.perform(requestBuilder).andExpect(status().isOk());
    }

}
