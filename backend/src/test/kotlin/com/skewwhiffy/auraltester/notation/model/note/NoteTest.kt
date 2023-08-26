package com.skewwhiffy.auraltester.notation.model.note

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class NoteTest {
    @Test
    fun returnsDisplayStringWhenNatural() {
        val note = Note.a

        val actual = note.displayString

        assertThat(actual).isEqualTo("A")
    }

    @Test
    fun returnsDisplayStringWhenSharp() {
        val note = Note.b.sharpen

        val actual = note.displayString

        assertThat(actual).isEqualTo("B#")
    }

    @Test
    fun returnsDisplayStringWhenFlat() {
        val note = Note.d.flatten

        val actual = note.displayString

        assertThat(actual).isEqualTo("Db")
    }

    @Test
    fun equatesEquivalentNotes() {
        val getNote = { Note("A", Accidental.sharp) }

        val first = getNote()
        val second = getNote()

        assertThat(first).isEqualTo(second)
        assertThat(first).isGreaterThanOrEqualTo(second)
        assertThat(first).isLessThanOrEqualTo(second)
    }

    @Test
    fun compareNonEquivalentNotes() {
        val lower = Note.c
        val higher = Note.a
        assertThat(lower).isLessThan(higher)
    }

    // TODO: > and < with #s and bs
}
