package com.skewwhiffy.auraltester.model

case class IntervalRequest(
  clef: String,
  bottomNote: String,
  intervalQuality: String,
  intervalSize: Int,
  keySignature: Option[String]
)
