//package com.example.demo.controller;
//
//import com.example.demo.models.Characteristics;
//import com.example.demo.models.House;
//import com.example.demo.services.HouseService;
//import org.json.JSONObject;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.mockito.Mockito;
//import org.skyscreamer.jsonassert.JSONAssert;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.PageImpl;
//import org.springframework.data.domain.Pageable;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.MediaType;
//import org.springframework.mock.web.MockHttpServletResponse;
//import org.springframework.security.test.context.support.WithMockUser;
//import org.springframework.test.context.junit4.SpringRunner;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.MvcResult;
//import org.springframework.test.web.servlet.RequestBuilder;
//import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Optional;
//
//import static org.hamcrest.Matchers.*;
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//@RunWith(SpringRunner.class)
//@WebMvcTest(value =HouseController.class)
//@WithMockUser
//public class HouseControllerTest {
//
//    @Autowired
//    private MockMvc mockMvc;
//
//    @MockBean
//    private HouseService houseService;
//
//    House mockHouse = new House(121,"Fully-mobilated","300x600x900","Somewhere","Ms.Nobody",1200,10 ,"SomeDescription",new Characteristics());
//
//    String mockHouseJSON ="{\"number\":\"121\",\"status\":\"Fully-mobilated\",\"dimensions\":\"300x600x900\",\"address\":\"Somewhere\",\"owner\":\"Ms.Nobody\",\"price\":\"1200\",\"reduction\":\"10\",\"description\":\"SomeDescription\",\"characteristics\":\"{}\"}";
//
//    @Test
//    public void getAllHousesTest() throws Exception {
//        House mockHouse2 = new House(122,"Fully-mobilated","300x600x900","Somewhere-else","Mr.Nobody",1200,10 ,"SomeDescription",new Characteristics());
//        House mockHouse3 = new House(123,"Fully-mobilated","300x600x900","Somewhere","Ms.Nobody",1200, 10 ,"SomeDescription",new Characteristics());
//        List<House> mockHouses= new ArrayList<>();
//        mockHouses.add(mockHouse);
//        mockHouses.add(mockHouse2);
//        mockHouses.add(mockHouse3);
//        Page<House> pageHouses= new PageImpl<>(mockHouses);
//
//        Mockito.when(houseService.findAll(Mockito.any(Pageable.class))).thenReturn(pageHouses);
//        RequestBuilder requestBuilderGet = MockMvcRequestBuilders.get(
//                "/houses/all").accept(
//                MediaType.APPLICATION_JSON);
//
//        mockMvc.perform(requestBuilderGet).andDo(print()).andExpect(status().isOk())
//                .andExpect(jsonPath("$.items",hasSize(3)));
//    }
//
//    @Test
//    public void filterHouses() throws Exception {
//        House mockHouse2 = new House(122,"Fully-mobilated","300x600x900","Somewhere-else","Mr.Nobody",1200,10 ,"SomeDescription",new Characteristics());
//        House mockHouse3 = new House(123,"Fully-mobilated","300x600x900","Somewhere","Ms.Nobody",1200, 10 ,"SomeDescription",new Characteristics());
//        List<House> mockHouseList= new ArrayList<>();
//        mockHouseList.add(mockHouse);
//        mockHouseList.add(mockHouse2);
//        mockHouseList.add(mockHouse3);
//        Page<House> mockPageHouses= new PageImpl<>(mockHouseList);
//
//        Mockito.when(houseService.findByFilters(Mockito.anyMap(),Mockito.any(Pageable.class))).thenReturn(mockPageHouses);
//        RequestBuilder requestBuilderGet = MockMvcRequestBuilders.get(
//                "/houses/filter").accept(
//                MediaType.APPLICATION_JSON);
//
//        mockMvc.perform(requestBuilderGet).andDo(print()).andExpect(status().isOk())
//                .andExpect(jsonPath("$.items",hasSize(3)));
//    }
//
//    @Test
//    public void allOrderedByReductionTest() throws Exception {
//        House mockHouse2 = new House(122,"Fully-mobilated","300x600x900","Somewhere-else","Mr.Nobody",1200,11 ,"SomeDescription",new Characteristics());
//        List<House> mockHouses= new ArrayList<>();
//        mockHouses.add(mockHouse);
//        mockHouses.add(mockHouse2);
//        Page<House> pageHouses= new PageImpl<>(mockHouses);
//
//        Mockito.when(houseService.findAllReduced(Mockito.any(Pageable.class))).thenReturn(pageHouses);
//        RequestBuilder requestBuilderGet = MockMvcRequestBuilders.get(
//                "/houses/byReduction").accept(
//                MediaType.APPLICATION_JSON);
//
//        mockMvc.perform(requestBuilderGet).andDo(print()).andExpect(status().isOk())
//                .andExpect(jsonPath("$.items",hasSize(2)));
//    }
//
//    @Test
//    public void getHouseTest() throws Exception {
//        Mockito.when(houseService.getByID(Mockito.anyString())).thenReturn(mockHouse);
//        RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
//                "/houses/testID").accept(
//                MediaType.APPLICATION_JSON);
//        mockMvc.perform(requestBuilder).andExpect(status().isOk()).andExpect(jsonPath("$.number", is(121)));
//    }
//
//    @Test
//    public void addHouseTest() throws Exception{
//        Mockito.when(houseService.addHouse(Mockito.any(House.class))).thenReturn(mockHouse);
//        RequestBuilder requestBuilder = MockMvcRequestBuilders
//                .post("/houses/add")
//                .accept(MediaType.APPLICATION_JSON).content(mockHouseJSON)
//                .contentType(MediaType.APPLICATION_JSON);
//
//        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
//        MockHttpServletResponse response = result.getResponse();
//        assertEquals(HttpStatus.CREATED.value(), response.getStatus());
//    }
//
//    @Test
//    public void editHouseTest() throws Exception{
//        House mockHouseUpdated = new House(123,"Fully-mobilated","300x600x900","Somewhere","Ms.Nobody",1200,11,"SomeDescription",new Characteristics());
//        String mockHouseUpdatedJSON = "{\"number\":\"123\",\"status\":\"Fully-mobilated\",\"dimensions\":\"300x600x900\",\"address\":\"Somewhere\",\"owner\":\"Ms.Nobody\",\"price\":\"1200\",\"reduction\":\"11\",\"description\":\"SomeDescription\",\"characteristics\":\"{}\"}";
//
//        Mockito.when(houseService.editHouse(Mockito.anyString(),Mockito.any(House.class))).thenReturn(Optional.of(mockHouseUpdated));
//
//        RequestBuilder requestBuilderPut = MockMvcRequestBuilders
//                .put("/houses/edit/someID")
//                .accept(MediaType.APPLICATION_JSON).content(mockHouseUpdatedJSON)
//                .contentType(MediaType.APPLICATION_JSON);
//
//        mockMvc.perform(requestBuilderPut).andExpect(status().isOk()).andExpect(jsonPath("$.number", is(123)));
//    }
//
//    @Test
//    public void deleteHouseTest() throws Exception{
//        RequestBuilder requestBuilder = MockMvcRequestBuilders
//                .delete("/houses/remove/deleteID")
//                .contentType(MediaType.APPLICATION_JSON);
//
//        mockMvc.perform(requestBuilder).andExpect(status().isOk());
//    }
//
//    @Test
//    public void deleteAllHousesTest() throws Exception{
//        RequestBuilder requestBuilder = MockMvcRequestBuilders
//                .delete("/houses/remove")
//                .contentType(MediaType.APPLICATION_JSON);
//
//        mockMvc.perform(requestBuilder).andExpect(status().isOk());
//    }
//}
