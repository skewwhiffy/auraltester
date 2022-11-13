package com.skewwhiffy.auraltester.scales

import com.skewwhiffy.auraltester.notes.{AbsoluteNote, Accidental, Note}
import com.skewwhiffy.auraltester.scales.Key.renderableKeys

object Key {
  private lazy val renderableKeys: List[String] = "C G D A E B F# C# F Bb Eb Ab Db Gb Cb".split(' ').toList
  lazy val cMajor: Key = new Key(Note.c)
}

class Key(val note: Note, val isMinor: Boolean = false) {
  lazy val abc: String = if (isMinor) s"${note.displayString}m" else note.displayString

  def abc(note: AbsoluteNote): String = {
    s"${accidentalAbc(note.note)}${noteAbc(note)}"
  }

  def canRenderSignature: Boolean = {
    if (isMinor) {
      return relativeMajor.canRenderSignature
    }
    renderableKeys.contains(note.displayString)
  }

  private def noteAbc(note: AbsoluteNote): String = {
    note.octave.offsetFromDefault match {
      case 0 => note.note.noteName
      case it if it > 0 => s"${note.note.noteName.toLowerCase}${"'".repeat(it - 1)}"
      case it if it < 0 => s"${note.note.noteName}${",".repeat(-it)}"
    }
  }

  private def accidentalAbc(note: Note): String = {
    notes.find(it => it.noteName == note.noteName) match {
      case None => throw new IllegalArgumentException(s"No note ${note.noteName} in scale")
      case Some(it) => note.accidental match {
        case it.accidental => ""
        case Accidental.natural => "="
        case it => it.abc
      }
    }
  }

  def relativeMinor: Key = if (isMinor) this else new Key(note.downMajorSecond.downMajorSecond.sharp, true)

  def relativeMajor: Key = if (isMinor) new Key(note.upMajorSecond.upMajorSecond.flat, false) else this

  def notes: List[Note] = {
    (if (isMinor) List(2, 1, 2, 2, 1, 2) else List(2, 2, 1, 2, 2, 2))
      .scanLeft(note) {
        case (it, 2) => it.upMajorSecond
        case (it, 1) => it.upMajorSecond.flat
      }
  }

  override def equals(obj: Any): Boolean = obj match {
    case other: Key => note == other.note && isMinor == other.isMinor
    case _ => false
  }
}

