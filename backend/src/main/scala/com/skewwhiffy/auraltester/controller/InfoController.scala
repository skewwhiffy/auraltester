package com.skewwhiffy.auraltester.controller

import com.skewwhiffy.auraltester.model.ApplicationInformation
import org.springframework.http.{CacheControl, MediaType, ResponseEntity}
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMethod.GET
import org.springframework.web.bind.annotation.{GetMapping, RequestMapping, RestController}
import scala.util.chaining.*

@RestController
@RequestMapping(path = Array("/info"))
class InfoController:
  @GetMapping(produces = Array(MediaType.APPLICATION_JSON_VALUE))
  def get(): ResponseEntity[ApplicationInformation] =
    ApplicationInformation("0.0.1")
      .pipe(it => ResponseEntity.ok().body(it))
