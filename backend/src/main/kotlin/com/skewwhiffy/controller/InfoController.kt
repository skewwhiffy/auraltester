package com.skewwhiffy.controller

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class InfoController {
  @RequestMapping("/api/info")
  fun get(): Any {
    return mapOf(
      "version" to "0.0.1-kotlin"
    )
  }

}