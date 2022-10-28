package com.skewwhiffy.auraltester.scales

import com.skewwhiffy.auraltester.notes.AbsoluteNote

import scala.util.chaining.scalaUtilChainingOps

class Scale(
  private val lowestNote: AbsoluteNote,
  private val scaleType: ScaleType,
  private val direction: ScaleDirection
) {
  lazy val displayName: String = s"${lowestNote.note.displayString} ${scaleType.displayName}"
  lazy val notes: List[AbsoluteNote] = scaleType
    .intervals(direction)
    .map(lowestNote.apply)
    .pipe(it => direction match {
      case ScaleDirection.ascending => it
      case ScaleDirection.descending => it.reverse
      // TODO: Check what happens with invalid choice
    })

  lazy val abc: String = notes.map(it => it.abc).mkString("")
}