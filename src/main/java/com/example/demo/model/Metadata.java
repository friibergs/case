package com.example.demo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Metadata {
@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer senderId;
    private Integer receiverId;
    private String  fileType;
    private boolean isPayable;
}
