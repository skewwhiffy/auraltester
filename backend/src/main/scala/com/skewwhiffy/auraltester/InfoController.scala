package com.skewwhiffy.auraltester

import org.springframework.http.{CacheControl, MediaType, ResponseEntity}
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMethod.GET
import org.springframework.web.bind.annotation.{RequestMapping, RestController}

import scala.io.Source

@RestController
class InfoController:
  @RequestMapping(path = Array("/info"), method = Array(GET), produces = Array(MediaType.TEXT_PLAIN_VALUE))
  def get(): ResponseEntity[String] =
    ResponseEntity.ok().cacheControl(CacheControl.noStore()).body("Hello world")
