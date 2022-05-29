package com.example.demo.integration;

import com.example.demo.controller.HouseController;
import com.example.demo.models.House;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@AutoConfigureMockMvc
@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class HouseIntegrationControllerTest {
    @Autowired
    private MockMvc mvc;

    @Autowired
    private HouseController houseController;

    @Test
    @Order(1)
    public void givenHouses_whenGetHouses_thenStatus200() throws Exception {

        mvc.perform(get("/houses/all").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andDo(print());
    }

    @Test
    @Order(2)
    public void createReadUpdateDeleteHouseTest() throws Exception {
        String exampleHouseJson ="{\"number\":\"121\",\"status\":\"Fully-mobilated\",\"dimensions\":\"300x600x900\",\"address\":\"Somewhere\",\"owner\":\"Ms.Nobody\",\"price\":\"1200\"}";

        MvcResult result = mvc.perform(post("/houses/add")
                        .accept(MediaType.APPLICATION_JSON).content(exampleHouseJson)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andReturn();

        String returnedHouseID= result.getResponse().getContentAsString().substring(7,31);

        mvc.perform(get("/houses/"+returnedHouseID).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.number",is(121)));

        String exampleUpdateHouseJson ="{\"number\":\"122\",\"status\":\"Fully-mobilated\",\"dimensions\":\"300x600x900\",\"address\":\"Somewhere\",\"owner\":\"Ms.Nobody\",\"price\":\"1200\"}";
        mvc.perform(put("/houses/edit/"+returnedHouseID)
                        .accept(MediaType.APPLICATION_JSON).content(exampleUpdateHouseJson)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.number",is(122)))
                .andDo(print());

        mvc.perform(delete("/houses/remove/"+returnedHouseID)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

}
