package com.skewwhiffy.auraltester.notation.model.note

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

    @SuppressWarnings("SpellCheckingInspection")
    /*
    public NoteDao toDao {
        return new NoteDao(noteName, accidental.toDao())
    }

    public String getDisplayString {
        return noteName + accidental.getDisplayString()
    }

    public Note sharpen {
        return new Note(noteName, accidental.sharpen())
    }

    public Note flatten {
        return new Note(noteName, accidental.flatten())
    }

    public Note upMajorSecond {
        if ("ACDFG".contains(noteName)) {
            return new Note(getNextNoteName(), accidental)
        }
        return new Note(getNextNoteName(), accidental.sharpen())
    }

    public Note downMajorSecond {
        return "BAGED".contains(noteName)
        ? new Note(getPreviousNoteName(), accidental)
        : new Note(getPreviousNoteName(), accidental.flatten())
    }
     */

    override fun compareTo(other: Note): Int {
        return if (this == other) 0 else noteNames.indexOf(noteName) - (noteNames.indexOf(other.noteName))
    }

    /*
    private String getNextNoteName {
        return switch (noteName) {
            case "A" -> "B"
            case "B" -> "C"
            case "C" -> "D"
            case "D" -> "E"
            case "E" -> "F"
            case "F" -> "G"
            case "G" -> "A"
            default -> throw new IllegalArgumentException(
            MessageFormat.format("Not a valid note name: '{0}'", noteName)
            )
        }
    }

    /*
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
  */

    private String getPreviousNoteName {
        return switch (noteName) {
            case "A" -> "G"
            case "B" -> "A"
            case "C" -> "B"
            case "D" -> "C"
            case "E" -> "D"
            case "F" -> "E"
            case "G" -> "F"
            default -> throw new IllegalArgumentException(
            MessageFormat.format("Not a valid note name: '{0}'", noteName)
            )
        }
    }

     */
}
