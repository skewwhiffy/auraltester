package com.skewwhiffy.auraltester.controller

import com.skewwhiffy.auraltester.controller.dto.ScaleResponse
import org.springframework.http.{MediaType, ResponseEntity}
import org.springframework.web.bind.annotation.{GetMapping, PathVariable, RequestMapping, RestController}

@RestController
@RequestMapping(path = Array("/api/scale"))
class ScaleController:
  @GetMapping(path = Array("/{clef}/{note}/{scaleType}"))
  def get(
    @PathVariable clef: String,
    @PathVariable note: String,
    @PathVariable scaleType: String
  ): ScaleResponse = ScaleResponse("CDEF GABc|")