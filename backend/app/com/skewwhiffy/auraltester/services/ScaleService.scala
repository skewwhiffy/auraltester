package com.skewwhiffy.auraltester.services

import com.skewwhiffy.auraltester.clefs.Clef
import com.skewwhiffy.auraltester.notes.Note
import com.skewwhiffy.auraltester.scales.{Scale, ScaleDirection, ScaleType}

import scala.util.chaining.scalaUtilChainingOps

class ScaleService {
  def getScale(clef: Clef, note: Note, scaleType: ScaleType, direction: ScaleDirection): Scale = {
    clef.getNoteNearBottom(note).pipe(it => new Scale(it, scaleType, direction))
  }
}