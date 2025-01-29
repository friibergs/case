package com.example.demo.controller;

import com.example.demo.model.Metadata;
import com.example.demo.service.ContentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ConsumerApi.class)
public class ConsumerApiTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ContentService contentService;

    private Metadata metadata;

    @BeforeEach
    public void setup() {
        metadata = new Metadata();
        metadata.setSenderId(1);
        metadata.setFileType("pdf");
        metadata.setReceiverId(100);
        metadata.setPayable(true);
    }

    @Test
    public void testGetContentBySenderId() throws Exception {
        Mockito.when(contentService.getContentBySenderId(1)).thenReturn(Collections.singletonList(metadata));

        mockMvc.perform(get("/api/consumer/content/1"))
                .andExpect(status().isOk())
                .andExpect(content().json("[{\"senderId\":1,\"fileType\":\"pdf\",\"receiverId\":100,\"isPayable\":true}]"));
    }
}
