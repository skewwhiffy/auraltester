package com.skewwhiffy.auraltester.notes

import com.skewwhiffy.auraltester.notes.Note.noteNames

object Note {
  lazy val A: Note = new Note("A", Accidental.natural)
  lazy val B: Note = new Note("B", Accidental.natural)
  lazy val C: Note = new Note("C", Accidental.natural)
  lazy val D: Note = new Note("D", Accidental.natural)
  lazy val E: Note = new Note("E", Accidental.natural)
  lazy val F: Note = new Note("F", Accidental.natural)
  lazy val G: Note = new Note("G", Accidental.natural)
  //noinspection SpellCheckingInspection
  private lazy val noteNames = "CDEFGAB"
}

class Note(val noteName: String, private val accidental: Accidental) {
  lazy val abc: String = s"${accidental.abc}$noteName"

  lazy val displayString: String = s"$noteName${accidental.displayString}"

  lazy val sharp: Note = new Note(noteName, accidental.sharp)

  lazy val flat: Note = new Note(noteName, accidental.flat)

  lazy val upMajorSecond: Note = noteName match {
    case "A" | "C" | "D" | "F" | "G" => new Note(nextNoteName, accidental)
    case _ => new Note(nextNoteName, accidental.sharp)
  }

  lazy val downMajorSecond: Note = noteName match {
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