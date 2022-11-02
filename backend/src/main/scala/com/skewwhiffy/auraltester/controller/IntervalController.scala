package com.skewwhiffy.auraltester.controller

import com.skewwhiffy.auraltester.controller.dto.IntervalResponse
import org.springframework.web.bind.annotation.{GetMapping, RequestMapping, RequestParam, RestController}

@RestController
@RequestMapping(path = Array("/api/interval"))
class IntervalController {
  @GetMapping
  def get(
    @RequestParam(required = true) clef: String,
    @RequestParam(required = true) bottomNote: String,
    @RequestParam(required = true) intervalQuality: String,
    @RequestParam(required = true) intervalSize: String,
    @RequestParam(required = false) keySignature: String
  ): IntervalResponse = {
    new IntervalResponse("Hello world")
  }
}
