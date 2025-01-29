package com.example.demo.controller;

import com.example.demo.model.Metadata;
import com.example.demo.service.ContentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/consumer")
public class ConsumerApi {
    private final ContentService contentService;

    public ConsumerApi(ContentService contentService) {
        this.contentService = contentService;
    }

    @GetMapping("/content/{senderId}")
    public List<Metadata> getContentBySenderId(@PathVariable Integer senderId) {
        return contentService.getContentBySenderId(senderId);
    }
}
