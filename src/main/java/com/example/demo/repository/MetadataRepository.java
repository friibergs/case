package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.Metadata;

public interface MetadataRepository extends JpaRepository<Metadata, Long>{
    List<Metadata> findBySenderId(Integer senderId);

}
