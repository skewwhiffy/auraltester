package com.skewwhiffy.auraltester.controller

import com.skewwhiffy.auraltester.service.InfoService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
class InfoController(private val infoService: InfoService) {
    @GetMapping("/api/info")
    fun get() = infoService.get()
}
