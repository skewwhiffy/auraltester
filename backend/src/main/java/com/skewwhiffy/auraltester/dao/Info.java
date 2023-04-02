package com.skewwhiffy.auraltester.dao;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Info {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String version;

    protected Info() {}

    public Info(String version) {
        this.version = version;
    }

    @Override
    public String toString() {
        return String.format("Info[id=%d, version='%s']", id, version);
    }

    public Long getId() {
        return id;
    }

    public String getVersion() {
        return version;
    }
}
