package com.skewwhiffy.auraltester.controller;

import com.skewwhiffy.auraltester.dto.InfoResponse;
import com.skewwhiffy.auraltester.service.InfoService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class InfoController {
    private final InfoService infoService;

    @RequestMapping("/api/info")
    public InfoResponse get() {
        return infoService.get();
    }
}
