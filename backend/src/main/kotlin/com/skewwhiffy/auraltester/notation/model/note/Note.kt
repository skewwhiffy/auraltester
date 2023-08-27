package com.skewwhiffy.auraltester.notation.model.note

import com.skewwhiffy.auraltester.dao.NoteDao

data class Note(val noteName: String, val accidental: Accidental) : Comparable<Note> {
    @Suppress("unused")
    companion object {
        @Suppress("SpellCheckingInspection")
        val noteNames = "CDEFGAB"
        val a: Note
            get() {
                return Note("A", Accidental.natural)
            }
        val b: Note
            get() {
                return Note("B", Accidental.natural)
            }
        val c: Note
            get() {
                return Note("C", Accidental.natural)
            }
        val d: Note
            get() {
                return Note("D", Accidental.natural)
            }
        val e: Note
            get() {
                return Note("E", Accidental.natural)
            }
        val f: Note
            get() {
                return Note("F", Accidental.natural)
            }
        val g: Note
            get() {
                return Note("G", Accidental.natural)
            }
    }


    override fun compareTo(other: Note): Int {
        return if (this == other) 0 else noteNames.indexOf(noteName) - (noteNames.indexOf(other.noteName))
    }

    val nextNoteName: String
        get() = when (noteName) {
            "A" -> "B"
            "B" -> "C"
            "C" -> "D"
            "D" -> "E"
            "E" -> "F"
            "F" -> "G"
            "G" -> "A"
            else -> throw IllegalArgumentException("Not a valid note name: '$noteName'")
        }

    val previousNoteName: String
        get() = when (noteName) {
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

val Note.dao
    get() = NoteDao(noteName, accidental.dao)

val Note.displayString
    get() = "$noteName${accidental.displayString}"

val Note.sharpen
    get() = Note(noteName, accidental.sharpen)

val Note.flatten
    get() = Note(noteName, accidental.flatten)

@Suppress("SpellCheckingInspection")
val Note.upMajorSecond
    get() = if ("ACDFG".contains(noteName)) Note(nextNoteName, accidental) else Note(
        nextNoteName,
        accidental.sharpen
    )

@Suppress("SpellCheckingInspection")
val Note.downMajorSecond
    get() = if ("BAGED".contains(noteName))
        Note(previousNoteName, accidental)
    else Note(previousNoteName, accidental.flatten)
