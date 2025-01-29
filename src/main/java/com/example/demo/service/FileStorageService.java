package com.example.demo.service;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.stereotype.Service;

@Service
public class FileStorageService {
    private final String STORAGE_DIR = "uploads/";

    public String saveFile(byte[] fileData, String fileName) throws Exception {
        Path filePath = Paths.get(STORAGE_DIR + fileName);
        Files.write(filePath, fileData);
        return filePath.toString();
        
    }

}
