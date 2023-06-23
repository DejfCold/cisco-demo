package cz.dejfcold.cisco_demo.restapi.controllers;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureMockMvc
class DocumentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void should_200_when_documentExists() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/documents/b1f8e48c-894f-436f-8f9e-716e12541f0f"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }


    @Test
    void should_404_when_documentDoesntExists() throws Exception {
        var uuid = UUID.randomUUID();
        mockMvc.perform(MockMvcRequestBuilders.get("/documents/" + uuid))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }
}