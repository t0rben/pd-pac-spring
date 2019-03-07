package com.prodyna.pac.dialogue.controller;

import com.prodyna.pac.dialogue.service.BusinessService;
import com.prodyna.pac.dialogue.service.PrototypeScopedService;
import com.prodyna.pac.dialogue.service.RequestScopedService;
import com.prodyna.pac.dialogue.service.SessionScopedService;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureWebMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@RunWith(SpringRunner.class)
@SpringBootTest
@MockBean(classes = {BusinessService.class, PrototypeScopedService.class, RequestScopedService.class, SessionScopedService.class})
@AutoConfigureWebMvc
@AutoConfigureMockMvc
public class AccountControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void updateAccount() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.put("/account/DEPOSITz0.002123123"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.accountOperation", Matchers.is("DEPOSIT")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.amount", Matchers.is(0.002123123)));

    }

    @Test
    public void secureEndpoint() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.get("/api/secure"))
                .andExpect(MockMvcResultMatchers.status().isUnauthorized());

        mockMvc.perform(MockMvcRequestBuilders.get("/api/secure").header("Authorization", "Basic VG9yYmVuOkJvY2s="))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}