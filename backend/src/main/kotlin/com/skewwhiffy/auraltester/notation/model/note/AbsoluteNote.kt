package com.skewwhiffy.auraltester.notation.model.note

import com.skewwhiffy.auraltester.notation.model.interval.Interval
import com.skewwhiffy.auraltester.notation.model.key.Key

data class AbsoluteNote(val note: Note, val octave: Octave, val lyric: String?) : Comparable<AbsoluteNote> {
    companion object {
        val middleC: AbsoluteNote
            get() = AbsoluteNote(Note.c, Octave.default, null)
    }

    /*
    public AbsoluteNoteDao toDao {
        return new AbsoluteNoteDao(note.toDao(), octave.toDao(), lyric.orElse(null))
    }

    public AbsoluteNote apply(DirectedInterval interval) {
        return switch (interval.direction()) {
            case UP -> plus(interval.interval())
            case DOWN -> minus(interval.interval())
        }
    }
    */

    fun withLyric(lyric: String): AbsoluteNote = AbsoluteNote(note, octave, lyric)

    operator fun plus(interval: Interval): AbsoluteNote {
        val defaultNote: AbsoluteNote = when (interval.degree) {
            1 -> this
            2 -> upMajorSecond
            3 -> upMajorSecond.upMajorSecond
            4 -> (this + Interval.major(3)).upMinorSecond
            5 -> (this + Interval.perfect(4)).upMajorSecond
            6 -> (this + Interval.perfect(5)).upMajorSecond
            7 -> (this + Interval.major(6)).upMajorSecond
            8 -> AbsoluteNote(note, octave.up, lyric)
            else -> throw IllegalArgumentException("Interval degree of ${interval.degree} not supported")
        }

        // TODO: HERE, KENNY
        val deviation = interval.deviation
        if (deviation < 0) {
            return (1..-deviation) .fold(defaultNote) { note, _ -> note.flatten }
        }
        if (deviation > 0) {
            return (1..deviation)
                .fold(defaultNote) { note, _ -> note.sharpen }
        }
        return defaultNote
    }

    operator fun minus(interval: Interval): AbsoluteNote {
        val defaultNote = when (interval.degree) {
            1 -> this
            2 -> downMajorSecond
            3 -> downMajorSecond.downMajorSecond
            4 -> (this - Interval.major(3)).downMinorSecond
            5 -> (this - Interval.perfect(4)).downMajorSecond
            6 -> (this - Interval.perfect(5)).downMajorSecond
            7 -> (this - Interval.major(6)).downMajorSecond
            8 -> AbsoluteNote(note, octave.down, lyric)
            else -> throw IllegalArgumentException()
        }
        val deviation = interval.deviation
        if (deviation < 0) {
            return (1..-deviation).fold(defaultNote) { it, _ -> it.sharpen }
        }
        if (deviation > 0) {
            return (1..deviation).fold(defaultNote) { it, _ -> it.flatten }
        }
        return defaultNote
    }

    fun getAbc(key: Key): String {
        return key.getAbc(this)
    }

    val wordAbc: String
        get() = lyric ?: "*"

    val downOne: AbsoluteNote
        get() = (this - Interval.minor(2)).ignoreAccidental

    val upOne: AbsoluteNote
        get() = (this + Interval.minor(2)).ignoreAccidental

    val skipOne: AbsoluteNote
        get() = (this + Interval.minor(3)).ignoreAccidental

    val ignoreAccidental: AbsoluteNote
        get() = AbsoluteNote(Note(note.noteName, Accidental.natural), octave, lyric)

    val withNoteName: AbsoluteNote
        get() = withLyric(note.displayString)

    private val sharpen: AbsoluteNote
        get() = AbsoluteNote(note.sharpen, octave, lyric)

    private val flatten: AbsoluteNote
        get() = AbsoluteNote(note.flatten, octave, lyric)

    private val upMajorSecond: AbsoluteNote
        get() = AbsoluteNote(
            note.upMajorSecond,
            if ("B" == note.noteName) octave.up else octave,
            lyric
        )

    private val upMinorSecond: AbsoluteNote
        get() = AbsoluteNote(
            upMajorSecond.note.flatten,
            upMajorSecond.octave,
            lyric
        )

    /*
    private AbsoluteNote downMajorSecond {
        return new AbsoluteNote(
                note.downMajorSecond(),
        "C".equals(note.noteName()) ? octave.down() : octave,
        lyric
        )
    }

    private AbsoluteNote downMinorSecond {
        val downMajorSecond = downMajorSecond()
        return new AbsoluteNote(downMajorSecond.note.sharpen(), downMajorSecond.octave, lyric)
    }

    */

    override fun compareTo(other: AbsoluteNote): Int {
        if (this == other) {
            return 0
        }
        if (octave == other.octave) {
            return note.compareTo(other.note)
        }
        return octave.compareTo(other.octave)
    }

    /*
    @Override
    public String toString {
        return getAbc(Key.getCMajor())
    }

    public static List<AbsoluteNote> range(AbsoluteNote lower, AbsoluteNote upper) {
        val nonNaturalNotes = Stream
            .of(lower, upper)
            .filter(it -> it.note.accidental().offset() != 0)
        .toList()
        if (!nonNaturalNotes.isEmpty()) {
            throw new IllegalArgumentException(
                    MessageFormat.format(
                        "Note(s) '{0}' have accidentals.",
                        nonNaturalNotes.stream().map(it -> it.getAbc(Key.getCMajor())).collect(Collectors.joining(", "))
            )
            )
        }
        return lower.compareTo(upper) > 0
        ? range(upper, lower)
        : lower.equals(upper)
        ? Collections.singletonList(lower)
        : Stream.concat(Stream.of(lower), range(lower.upOne(), upper).stream()).toList()
    }

     */
}
