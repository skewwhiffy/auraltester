package com.skewwhiffy.auraltester.services

import com.skewwhiffy.auraltester.clefs.Clef
import com.skewwhiffy.auraltester.notes.Interval.Interval
import com.skewwhiffy.auraltester.notes.Note
import com.skewwhiffy.auraltester.scales.IntervalNotes

import scala.util.chaining.scalaUtilChainingOps

class IntervalService {
  def getInterval(clef: Clef, note: Note, interval: Interval): IntervalNotes = {
    clef.getNoteNearBottom(note).pipe(it => new IntervalNotes(it, interval))
  }

}
