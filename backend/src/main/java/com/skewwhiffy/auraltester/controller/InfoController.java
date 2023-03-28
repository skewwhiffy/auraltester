package com.skewwhiffy.auraltester.controller;

import com.skewwhiffy.auraltester.dto.InfoResponse;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class InfoController {
    @RequestMapping("/api/info")
    public InfoResponse get() {
        return new InfoResponse("0.0.1-java");
    }
}
