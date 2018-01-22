package com.britishgas.apitwo;

import com.britishgas.apitwo.controller.ApitwoController;
import com.britishgas.apitwo.model.Response;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.client.RestTemplate;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ApitwoApplicationTests {
    @Value("${anotherapiURL}")
    private String anotherapiURL;

    private MockMvc mockMvc;

    @MockBean
    private RestTemplate restTemplate;

    @InjectMocks
    @Autowired
    private ApitwoController controller;

    @Before
    public void setup() {
        Response message = new Response();
        message.addResponse("From apione: I got your call.");
        Mockito.when(restTemplate.getForObject(anotherapiURL, Response.class)).thenReturn(message);
        this.mockMvc = standaloneSetup(controller).build();
    }


    @Test
    public void contextLoads() {
    }

    @Test
    public void callByOtherPairReturnsOk() throws Exception {
        mockMvc.perform(get("/callByAnotherApi"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.messages").value("From apitwo: I got your call."));
    }

    @Test
    public void callReturnsAfterCallingAnotherApi() throws Exception {
        mockMvc.perform(get("/callAnotherApi"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.messages[0]").value("From apione: I got your call."))
                .andExpect(jsonPath("$.messages[1]").value("Initial call received by apitwo, other api is called and returning now"));
    }
}
