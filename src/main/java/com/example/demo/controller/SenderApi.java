package com.example.demo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Metadata;
import com.example.demo.service.ContentService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;



@RestController
@RequestMapping("/api/sender")
public class SenderApi {
    private final ContentService contentService;

    public SenderApi(ContentService contentService) {
        this.contentService = contentService;
    }

    @PostMapping("/send")
    public ResponseEntity<Metadata> sendContent(@RequestBody Metadata metadata) {
        Metadata savedMetadata = contentService.saveMetadata(metadata);
        return ResponseEntity.ok(savedMetadata);
    }

    @GetMapping("/content/{senderId}")
    public List<Metadata> getContentBySenderId(@PathVariable Integer senderId) {
        return contentService.getContentBySenderId(senderId);
    }
    

}
