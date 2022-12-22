package com.skewwhiffy.controller

import com.skewwhiffy.dto.InfoResponse
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class InfoController {
  @RequestMapping("/api/info")
  fun get(): InfoResponse {
    return InfoResponse("0.0.1-kotlin")
  }

}