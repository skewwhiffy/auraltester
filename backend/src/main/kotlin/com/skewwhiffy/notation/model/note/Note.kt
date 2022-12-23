package com.skewwhiffy.notation.model.note

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

  val displayString: String = "$noteName${accidental.displayString}"

  val sharp: Note get() = Note(noteName, accidental.sharp)

  val flat: Note get() = Note(noteName, accidental.flat)

  val upMajorSecond: Note
    get() {
      return when (noteName) {
        in listOf("A", "C", "D", "F", "G") -> Note(nextNoteName, accidental)
        else -> Note(nextNoteName, accidental.sharp)
      }
    }

  val downMajorSecond: Note
    get() {
      return when (noteName) {
        in listOf("B", "A", "G", "E", "D") -> Note(previousNoteName, accidental)
        else -> Note(previousNoteName, accidental.flat)
      }
    }

  /*
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

  private val previousNoteName: String = when (noteName) {
    "A" -> "G"
    "B" -> "A"
    "C" -> "B"
    "D" -> "C"
    "E" -> "D"
    "F" -> "E"
    "G" -> "F"
    else -> throw IllegalArgumentException("Not a valid note name: '$noteName'")
  }
}