package com.skewwhiffy.auraltester.dto;

public class InfoResponse {
    private final String version;

    public InfoResponse(String version) {
        this.version = version;
    }

    public String getVersion() {
        return version;
    }
}
