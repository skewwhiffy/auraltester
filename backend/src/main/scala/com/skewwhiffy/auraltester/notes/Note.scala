package com.skewwhiffy.auraltester.notes

object Note {
  lazy val A: Note = Note("A", Accidental.NATURAL)
  lazy val B: Note = Note("B", Accidental.NATURAL)
  lazy val C: Note = Note("C", Accidental.NATURAL)
  lazy val D: Note = Note("D", Accidental.NATURAL)
  lazy val E: Note = Note("E", Accidental.NATURAL)
  lazy val F: Note = Note("F", Accidental.NATURAL)
  lazy val G: Note = Note("G", Accidental.NATURAL)
}

private class Note(val noteName: String, val accidental: Accidental) {
  lazy val displayString: String = s"$noteName${accidental.displayString}"

  lazy val sharp: Note = Note(noteName, accidental.sharp)

  lazy val flat: Note = Note(noteName, accidental.flat)

  lazy val upMajorSecond: Note = noteName match
    case "A" | "C" | "D" | "F" | "G" => Note(nextNoteName, accidental)
    case _ => Note(nextNoteName, accidental.sharp)

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
