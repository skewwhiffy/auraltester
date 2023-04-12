package com.skewwhiffy.auraltester.dao;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;

import java.util.UUID;

@Entity
public class Info {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Getter
    private UUID id;
    @Getter
    private String version;

    Info() {
    }

    public Info(String version) {
        this.version = version;
    }
}
