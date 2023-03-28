package com.skewwhiffy.auraltester.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class InfoController {
    @RequestMapping("/api/info")
    public String get() {
        return "Hello";
    }
}
