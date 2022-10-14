package com.skewwhiffy.auraltester.notes

object Note {
  val A: Note = Note("A", Accidental.NATURAL)
  val B: Note = Note("B", Accidental.NATURAL)
  val C: Note = Note("C", Accidental.NATURAL)
  val D: Note = Note("D", Accidental.NATURAL)
  val E: Note = Note("E", Accidental.NATURAL)
  val F: Note = Note("F", Accidental.NATURAL)
  val G: Note = Note("G", Accidental.NATURAL)
}

private class Note(val noteName: String, val accidental: Accidental) {
  def getDisplayString: String = {
    noteName + accidental.displayString
  }

  def getSharp: Note = {
    Note(noteName, accidental.sharp)
  }

  def getFlat: Note = {
    new Note(noteName, accidental.flat)
  }

  def addMajorSecond(): Note = {
    noteName match
      case "A" | "C" | "D" | "F" | "G" => Note(getNextNoteName, accidental)
      case _ => Note(getNextNoteName, accidental.sharp)
  }

  private def getNextNoteName: String = {
    noteName match
      case "A" => "B"
      case "B" => "C"
      case "C" => "D"
      case "D" => "E"
      case "E" => "F"
      case "F" => "G"
      case "G" => "A"
      case _ => throw new IllegalArgumentException(String.format("Not a valid note name: '%s'", noteName))
  }
}
