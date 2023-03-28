package com.skewwhiffy.auraltester.controller;

import com.skewwhiffy.auraltester.dto.InfoResponse;
import com.skewwhiffy.auraltester.service.InfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class InfoController {
    private final InfoService infoService;

    public InfoController(@Autowired InfoService infoService) {
        this.infoService = infoService;
    }

    @RequestMapping("/api/info")
    public InfoResponse get() {
        return infoService.get();
    }
}
