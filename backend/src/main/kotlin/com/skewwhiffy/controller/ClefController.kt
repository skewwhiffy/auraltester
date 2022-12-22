package com.skewwhiffy.controller

import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class ClefController {
  @RequestMapping("/api/clef")
  fun get(): Any {
    return mapOf(
      "hello" to "goodbye"
    )
  }
}