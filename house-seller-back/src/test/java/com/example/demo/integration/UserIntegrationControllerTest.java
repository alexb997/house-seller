//package com.example.demo.integration;
//import org.apache.commons.lang3.StringUtils;
//import org.junit.jupiter.api.MethodOrderer;
//import org.junit.jupiter.api.Order;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.TestMethodOrder;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.http.MediaType;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.MvcResult;
//
//import static org.hamcrest.Matchers.is;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
//import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
//
//@AutoConfigureMockMvc
//@SpringBootTest
//@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
//public class UserIntegrationControllerTest {
//
//    @Autowired
//    private MockMvc mvc;
//
//    static String id = StringUtils.EMPTY;
//
//    @Test
//    @Order(1)
//    public void createAndDeleteUserTest() throws Exception {
//        String exampleUserJSON = "{\"username\":\"Mr.Nobody\",\"password\":\"pass1234\"}";
//        MvcResult result = mvc.perform(post("/users/add")
//                        .accept(MediaType.APPLICATION_JSON).content(exampleUserJSON)
//                        .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isCreated())
//                .andExpect(content()
//                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
//                .andDo(print())
//                .andExpect(jsonPath("$.password",is("pass1234")))
//                .andReturn();
//
//        String content = result.getResponse().getContentAsString();
//        id = content.substring(7, 31);
//
//        mvc.perform(delete("/users/delete/"+id)
//                        .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk());
//    }
//}
