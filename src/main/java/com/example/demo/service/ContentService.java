package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.model.Metadata;
import com.example.demo.repository.MetadataRepository;

@Service
public class ContentService {
private final MetadataRepository metadataRepository;

public ContentService(MetadataRepository metadataRepository) {
    this.metadataRepository = metadataRepository;
}

public Metadata saveMetadata(Metadata metadata) {
    return metadataRepository.save(metadata);
}

public List<Metadata> getContentBySenderId(Integer senderId) {
    return metadataRepository.findBySenderId(senderId);

}
}

