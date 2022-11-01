package com.skewwhiffy.auraltester.scales

import com.skewwhiffy.auraltester.notes.AbsoluteNote

import scala.util.chaining.scalaUtilChainingOps

class Scale(
  private val _lowestNote: AbsoluteNote,
  val scaleType: ScaleType,
  val direction: ScaleDirection
) {
  def lowestNote: AbsoluteNote = _lowestNote

  def displayName: String = s"${lowestNote.note.displayString} ${scaleType.displayName}"

  def notes: List[AbsoluteNote] = scaleType
    .intervals(direction)
    .map(lowestNote.apply)
    .pipe(it => direction match {
      case ScaleDirection.ascending => it
      case ScaleDirection.descending => it.reverse
      // TODO: Check what happens with invalid choice
    })

  def abc(key: Key): String = notes.map(it => it.abc(key)).mkString("")
}