package com.example.demo.controller;

import com.example.demo.model.Metadata;
import com.example.demo.service.ContentService;
import com.example.demo.service.FileStorageService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(SenderApi.class)
public class SenderApiTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ContentService contentService;

    @MockBean
    private FileStorageService fileStorageService;

    private Metadata metadata;

    @BeforeEach
    public void setup() {
        metadata = new Metadata();
        metadata.setSenderId(1);
        metadata.setFileType("pdf");
        metadata.setReceiverId(100);
        metadata.setPayable(true);
        metadata.setFilePath("uploads/test.pdf");
    }

    @Test
    public void testSendContent() throws Exception {
        MockMultipartFile file = new MockMultipartFile("file", "test.pdf", MediaType.APPLICATION_PDF_VALUE, "test content".getBytes());
        MockMultipartFile metadataPart = new MockMultipartFile("metadata", "", MediaType.APPLICATION_JSON_VALUE, "{\"senderId\":1,\"fileType\":\"pdf\",\"receiverId\":100,\"isPayable\":true}".getBytes());

        Mockito.when(fileStorageService.saveFile(Mockito.any(), Mockito.anyString())).thenReturn("uploads/test.pdf");
        Mockito.when(contentService.saveMetadata(Mockito.any(Metadata.class))).thenReturn(metadata);

        mockMvc.perform(multipart("/api/sender/send")
                .file(file)
                .file(metadataPart)
                .contentType(MediaType.MULTIPART_FORM_DATA))
                .andExpect(status().isOk())
                .andExpect(content().json("{\"senderId\":1,\"fileType\":\"pdf\",\"receiverId\":100,\"isPayable\":true,\"filePath\":\"uploads/test.pdf\"}"));
    }
}