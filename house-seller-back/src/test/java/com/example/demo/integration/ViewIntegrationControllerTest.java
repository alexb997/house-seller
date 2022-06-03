package com.example.demo.integration;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.Date;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@AutoConfigureMockMvc
@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ViewIntegrationControllerTest {

    @Autowired
    private MockMvc mvc;

    static String id = StringUtils.EMPTY;

    @Test
    @Order(1)
    public void createAndDeleteViewTest() throws Exception {
        String exampleViewJSON = "{\"houseID\":\"houseID\",\"userID\":\"userID\",\"viewDate\":\"2022-06-03T07:35:34.047+00:00\"}";
        MvcResult result = mvc.perform(post("/views/add")
                        .accept(MediaType.APPLICATION_JSON).content(exampleViewJSON)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(jsonPath("$.userID",is("userID")))
                .andReturn();

        String content = result.getResponse().getContentAsString();
        id = content.substring(7, 31);

        mvc.perform(delete("/views/delete/"+id)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}
