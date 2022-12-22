package com.skewwhiffy.notation.model

data class Note(val noteName: String, val accidental: Accidental) {
  companion object {
    val a: Note = Note("A", Accidental.natural)
    val b: Note = Note("B", Accidental.natural)
    val c: Note = Note("C", Accidental.natural)
    val d: Note = Note("D", Accidental.natural)
    val e: Note = Note("E", Accidental.natural)
    val f: Note = Note("F", Accidental.natural)
    val g: Note = Note("G", Accidental.natural)

    @Suppress("SpellCheckingInspection")
    private val noteNames = "CDEFGAB"
  }
  /*

  def displayString: String = s"$noteName${accidental.displayString}"

  def sharp: Note = Note(noteName, accidental.sharp)

  def flat: Note = Note(noteName, accidental.flat)

  def upMajorSecond: Note = noteName match {
    case "A" | "C" | "D" | "F" | "G" => Note(nextNoteName, accidental)
    case _ => Note(nextNoteName, accidental.sharp)
  }

  def downMajorSecond: Note = noteName match {
    case "B" | "A" | "G" | "E" | "D" => Note(previousNoteName, accidental)
    case _ => Note(previousNoteName, accidental.flat)
  }

  def <=(other: Note): Boolean = this == other || this < other

  def >=(other: Note): Boolean = this == other || this > other

  def <(other: Note): Boolean = noteNames.indexOf(this.noteName) < noteNames.indexOf(other.noteName)

  def >(other: Note): Boolean = noteNames.indexOf(this.noteName) > noteNames.indexOf(other.noteName)

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
   */
}