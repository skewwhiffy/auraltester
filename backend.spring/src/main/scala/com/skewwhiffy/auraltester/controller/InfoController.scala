package com.skewwhiffy.auraltester.controller

import com.skewwhiffy.auraltester.model.ApplicationInformation
import org.springframework.web.bind.annotation.{GetMapping, RequestMapping, RestController}

@RestController
@RequestMapping(path = Array("/info"))
class InfoController {
  @GetMapping
  def get():ApplicationInformation = new ApplicationInformation("0.0.1")
}
