//package com.example.demo.controller;
//
//import com.example.demo.models.User;
//import com.example.demo.services.UserService;
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
//
//import static org.hamcrest.Matchers.hasSize;
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//@RunWith(SpringRunner.class)
//@WebMvcTest(value = UserController.class)
//@WithMockUser
//public class UserControllerTest {
//    @Autowired
//    private MockMvc mockMvc;
//
//    @MockBean
//    private UserService userService;
//
//    User mockUser = new User("Mr.Nobody","pass1234");
//
//    String mockUserJSON="{\"username\":\"Mr.Nobody\",\"password\":\"pass1234\"}";
//
//    @Test
//    public void getAllUsersTest() throws Exception {
//        User mockUser2 = new User("Mr.Nobody2","pass4321");
//        User mockUser3 = new User("Mr.Nobody3","pass42");
//        List<User> mockUsers= new ArrayList<>();
//        mockUsers.add(mockUser);
//        mockUsers.add(mockUser2);
//        mockUsers.add(mockUser3);
//        Page<User> pageUsers= new PageImpl<>(mockUsers);
//
//        Mockito.when(userService.allUsers(Mockito.any(Pageable.class))).thenReturn(pageUsers);
//        RequestBuilder requestBuilderGet = MockMvcRequestBuilders.get(
//                "/users/").accept(
//                MediaType.APPLICATION_JSON);
//
//        mockMvc.perform(requestBuilderGet).andDo(print()).andExpect(status().isOk())
//                .andExpect(jsonPath("$.items",hasSize(3)));
//    }
//
//    @Test
//    public void registerUserTest() throws Exception{
//        Mockito.when(userService.addNewUser(Mockito.any(User.class))).thenReturn(mockUser);
//
//        RequestBuilder requestBuilder = MockMvcRequestBuilders
//                .post("/users/add")
//                .accept(MediaType.APPLICATION_JSON).content(mockUserJSON)
//                .contentType(MediaType.APPLICATION_JSON);
//
//        MvcResult result = mockMvc.perform(requestBuilder).andDo(print()).andReturn();
//        MockHttpServletResponse response = result.getResponse();
//        assertEquals(HttpStatus.CREATED.value(), response.getStatus());
//    }
//
//    @Test
//    public void deleteUserTest() throws Exception {
//        RequestBuilder requestBuilder = MockMvcRequestBuilders
//                .delete("/users/delete/deleteID")
//                .contentType(MediaType.APPLICATION_JSON);
//
//
//        mockMvc.perform(requestBuilder).andExpect(status().isOk());
//    }
//
//}
