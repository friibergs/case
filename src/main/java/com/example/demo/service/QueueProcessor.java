package com.example.demo.service;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class QueueProcessor {

    @Async
    public void processFileAsync(String filePath) {
        // Simulate processing time
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        System.out.println("File processed: " + filePath);
    }
}
