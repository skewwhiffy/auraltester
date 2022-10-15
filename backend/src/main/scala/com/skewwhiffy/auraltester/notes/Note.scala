package com.skewwhiffy.auraltester.notes

object Note {
  lazy val A: Note = Note("A", Accidental.natural)
  lazy val B: Note = Note("B", Accidental.natural)
  lazy val C: Note = Note("C", Accidental.natural)
  lazy val D: Note = Note("D", Accidental.natural)
  lazy val E: Note = Note("E", Accidental.natural)
  lazy val F: Note = Note("F", Accidental.natural)
  lazy val G: Note = Note("G", Accidental.natural)
}

class Note(val noteName: String, private val accidental: Accidental) {
  lazy val displayString: String = s"$noteName${accidental.displayString}"

  lazy val sharp: Note = Note(noteName, accidental.sharp)

  lazy val flat: Note = Note(noteName, accidental.flat)

  lazy val upMajorSecond: Note = noteName match
    case "A" | "C" | "D" | "F" | "G" => Note(nextNoteName, accidental)
    case _ => Note(nextNoteName, accidental.sharp)

  override def equals(obj: Any): Boolean = obj match
    case other: Note => other.noteName == noteName && other.accidental == accidental
    case _ => false

  private lazy val nextNoteName: String = noteName match
    case "A" => "B"
    case "B" => "C"
    case "C" => "D"
    case "D" => "E"
    case "E" => "F"
    case "F" => "G"
    case "G" => "A"
    case _ => throw new IllegalArgumentException(s"Not a valid note name: '$noteName'")
}
