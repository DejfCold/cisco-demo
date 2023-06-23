package cz.dejfcold.cisco_demo.restapi.controllers;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureMockMvc
class TaggedContentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void should_haveDocument_when_tagExists() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/taggedContent?tag=test"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.length()", Matchers.equalTo(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0]", Matchers.endsWith("b1f8e48c-894f-436f-8f9e-716e12541f0f")));
    }


    @Test
    void should_haveDocumentAndParentDocument_when_parentTagExists() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/taggedContent?tag=test2"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.length()", Matchers.equalTo(2)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.*", Matchers.containsInAnyOrder(
                        Matchers.endsWith("b1f8e48c-894f-436f-8f9e-716e12541f0f"),
                        Matchers.endsWith("28b14d97-748f-42cd-89e0-4942645f334f"))));
    }

    @Test
    void should_404_when_tagDoesntExist() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/taggedContent?tag=missing"))
                .andExpect(MockMvcResultMatchers.status().isNotFound());

    }
}