package com.skewwhiffy.auraltester.service;

import com.skewwhiffy.auraltester.dto.InfoResponse;
import com.skewwhiffy.auraltester.repository.InfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.stream.StreamSupport;

@Service
public class InfoService {
    private final InfoRepository infoRepository;

    public InfoService(@Autowired InfoRepository infoRepository) {
        this.infoRepository = infoRepository;
    }

    public InfoResponse get() {
        var allEntries = StreamSupport
                .stream(infoRepository.findAll().spliterator(), false);
        return allEntries
                .findFirst()
                .map(it -> new InfoResponse(it.getVersion()))
                .orElseThrow();
    }
}
