package com.skewwhiffy.auraltester.notation.model.note

import com.skewwhiffy.auraltester.dao.AbsoluteNoteDao
import com.skewwhiffy.auraltester.notation.model.abc.upOne
import com.skewwhiffy.auraltester.notation.model.interval.DirectedInterval
import com.skewwhiffy.auraltester.notation.model.interval.Interval
import com.skewwhiffy.auraltester.notation.model.interval.IntervalDirection
import com.skewwhiffy.auraltester.notation.model.key.Key

data class AbsoluteNote(val note: Note, val octave: Octave, val lyric: String?) : Comparable<AbsoluteNote> {
    companion object {
        val middleC: AbsoluteNote
            get() = AbsoluteNote(Note.c, Octave.default, null)

        fun range(lower: AbsoluteNote, upper: AbsoluteNote): List<AbsoluteNote> {
            val nonNaturalNotes = listOf(lower, upper).filter { it.note.accidental.offset != 0 }
            if (nonNaturalNotes.isNotEmpty()) {
                throw IllegalArgumentException(
                    "Note(s) '${nonNaturalNotes.joinToString(", ") { it.getAbc(Key.cMajor) }}' have accidentals.",
                )
            }
            return if (lower > upper) range(upper, lower)
            else if (lower == upper) listOf(lower)
            else listOf(lower) + range(lower.upOne, upper)
        }
    }

    val dao by lazy { AbsoluteNoteDao(note.dao, octave.dao, lyric) }

    fun apply(interval: DirectedInterval) = when (interval.direction) {
        IntervalDirection.UP -> this + interval.interval
        IntervalDirection.DOWN -> this - interval.interval
    }

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
            return (1..-deviation).fold(defaultNote) { note, _ -> note.flatten }
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

    operator fun minus(other: AbsoluteNote): Interval {
        TODO()
    }

    fun getAbc(key: Key): String {
        return key.getAbc(this)
    }

    val wordAbc: String
        get() = lyric ?: "*"

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

    private val downMajorSecond: AbsoluteNote
        get() = AbsoluteNote(
            note.downMajorSecond,
            if (note.noteName == "C") octave.down else octave,
            lyric
        )

    private val downMinorSecond: AbsoluteNote
        get() = AbsoluteNote(
            downMajorSecond.note.sharpen,
            downMajorSecond.octave,
            lyric
        )

    override fun compareTo(other: AbsoluteNote): Int {
        if (this == other) {
            return 0
        }
        if (octave == other.octave) {
            return note.compareTo(other.note)
        }
        return octave.compareTo(other.octave)
    }

    override fun toString() = getAbc(Key.cMajor)
}
