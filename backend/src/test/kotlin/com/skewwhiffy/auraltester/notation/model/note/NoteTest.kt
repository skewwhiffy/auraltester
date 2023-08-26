package com.skewwhiffy.auraltester.notation.model.note
/*

import lombok.`val`
internal class NoteTest {
    @org.junit.jupiter.api.Test
    fun returnsDisplayStringWhenNatural() {
        val note: `val` = Note.getA()
        val actual: `val` = note.getDisplayString()
        org.assertj.core.api.Assertions.assertThat<`val`>(actual).isEqualTo("A")
    }

    @org.junit.jupiter.api.Test
    fun returnsDisplayStringWhenSharp() {
        val note: `val` = Note.getB().sharpen()
        val actual: `val` = note.getDisplayString()
        org.assertj.core.api.Assertions.assertThat<`val`>(actual).isEqualTo("B#")
    }

    @org.junit.jupiter.api.Test
    fun returnsDisplayStringWhenFlat() {
        val note: `val` = Note.getD().flatten()
        val actual: `val` = note.getDisplayString()
        org.assertj.core.api.Assertions.assertThat<`val`>(actual).isEqualTo("Db")
    }

    @org.junit.jupiter.api.Test
    fun equatesEquivalentNotes() {
        val getNote = java.util.function.Supplier<Note> { Note("A", Accidental.getSharp()) }
        val first: `val` = getNote.get()
        val second: `val` = getNote.get()
        org.assertj.core.api.Assertions.assertThat<`val`>(first).isEqualTo(second)
        org.assertj.core.api.Assertions.assertThat<`val`>(first).isGreaterThanOrEqualTo(second)
        org.assertj.core.api.Assertions.assertThat<`val`>(first).isLessThanOrEqualTo(second)
    }

    @org.junit.jupiter.api.Test
    fun compareNonEquivalentNotes() {
        val lower: `val` = Note.getC()
        val higher: `val` = Note.getA()
        org.assertj.core.api.Assertions.assertThat<`val`>(lower).isLessThan(higher)
    } // TODO: > and < with #s and bs
}


 */