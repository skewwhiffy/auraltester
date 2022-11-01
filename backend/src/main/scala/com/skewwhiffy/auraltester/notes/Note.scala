package com.skewwhiffy.auraltester.notes

import com.skewwhiffy.auraltester.notes.Note.noteNames
import com.skewwhiffy.auraltester.scales.Key

object Note {
  lazy val a: Note = new Note("A", Accidental.natural)
  lazy val b: Note = new Note("B", Accidental.natural)
  lazy val c: Note = new Note("C", Accidental.natural)
  lazy val d: Note = new Note("D", Accidental.natural)
  lazy val e: Note = new Note("E", Accidental.natural)
  lazy val f: Note = new Note("F", Accidental.natural)
  lazy val g: Note = new Note("G", Accidental.natural)
  //noinspection SpellCheckingInspection
  private lazy val noteNames = "CDEFGAB"
}

class Note(val noteName: String, val accidental: Accidental) {
  def displayString: String = s"$noteName${accidental.displayString}"

  def sharp: Note = new Note(noteName, accidental.sharp)

  def flat: Note = new Note(noteName, accidental.flat)

  def upMajorSecond: Note = noteName match {
    case "A" | "C" | "D" | "F" | "G" => new Note(nextNoteName, accidental)
    case _ => new Note(nextNoteName, accidental.sharp)
  }

  def downMajorSecond: Note = noteName match {
    case "B" | "A" | "G" | "E" | "D" => new Note(previousNoteName, accidental)
    case _ => new Note(previousNoteName, accidental.flat)
  }

  def <=(other: Note): Boolean = this == other || this < other

  def >=(other: Note): Boolean = this == other || this > other

  def <(other: Note): Boolean = noteNames.indexOf(this.noteName) < noteNames.indexOf(other.noteName)

  def >(other: Note): Boolean = noteNames.indexOf(this.noteName) > noteNames.indexOf(other.noteName)

  override def equals(obj: Any): Boolean = obj match {
    case other: Note => other.noteName == noteName && other.accidental == accidental
    case _ => false
  }

  private lazy val nextNoteName: String = noteName match {
    case "A" => "B"
    case "B" => "C"
    case "C" => "D"
    case "D" => "E"
    case "E" => "F"
    case "F" => "G"
    case "G" => "A"
    case _ => throw new IllegalArgumentException(s"Not a valid note name: '$noteName'")
  }

  private lazy val previousNoteName: String = noteName match {
    case "A" => "G"
    case "B" => "A"
    case "C" => "B"
    case "D" => "C"
    case "E" => "D"
    case "F" => "E"
    case "G" => "F"
    case _ => throw new IllegalArgumentException(s"Not a valid note name: '$noteName'")
  }
}