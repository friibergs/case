package com.example.demo.controller;

import com.example.demo.model.Metadata;
import com.example.demo.service.ContentService;
import com.example.demo.service.FileStorageService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api/sender")
public class SenderApi {
    private final ContentService contentService;
    private final FileStorageService fileStorageService;

    public SenderApi(ContentService contentService, FileStorageService fileStorageService) {
        this.contentService = contentService;
        this.fileStorageService = fileStorageService;
    }

    @PostMapping("/send")
    public ResponseEntity<Metadata> sendContent(@RequestPart("file") MultipartFile file,
                                                @RequestPart("metadata") Metadata metadata) throws IOException {
        try {
            String filePath = fileStorageService.saveFile(file.getBytes(), file.getOriginalFilename());

            metadata.setFilePath(filePath);
            Metadata savedMetadata = contentService.saveMetadata(metadata);

            return ResponseEntity.ok(savedMetadata);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
