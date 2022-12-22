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
*/
  val flat: Note
    get() = Note(noteName, accidental.flat)

  val upMajorSecond: Note
    get() {
      return when (noteName) {
        in listOf("A", "C", "D", "F", "G") -> Note(nextNoteName, accidental)
        else -> Note(nextNoteName, accidental.sharp)
      }
    }

  /*

  def downMajorSecond : Note = noteName match
  {
    case "B" | "A" | "G" | "E" | "D" => Note(previousNoteName, accidental)
    case _ => Note (previousNoteName, accidental.flat)
  }

  def <= (other: Note): Boolean = this == other || this < other

  def >= (other: Note): Boolean = this == other || this > other

  def < (other: Note): Boolean = noteNames.indexOf(this.noteName) < noteNames.indexOf(other.noteName)

  def > (other: Note): Boolean = noteNames.indexOf(this.noteName) > noteNames.indexOf(other.noteName)

*/
  private val nextNoteName: String = when (noteName) {
    "A" -> "B"
    "B" -> "C"
    "C" -> "D"
    "D" -> "E"
    "E" -> "F"
    "F" -> "G"
    "G" -> "A"
    else -> throw IllegalArgumentException("Not a valid note name: '$noteName'")
  }
  /*

  private lazy
  val previousNoteName: String = noteName match {
    case "A" => "G"
    case "B" => "A"
    case "C" => "B"
    case "D" => "C"
    case "E" => "D"
    case "F" => "E"
    case "G" => "F"
    case _ => throw new IllegalArgumentException (s"Not a valid note name: '$noteName'")
  }
}
*/
}