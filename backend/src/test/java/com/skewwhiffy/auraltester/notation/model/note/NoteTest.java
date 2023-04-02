package com.skewwhiffy.auraltester.notation.model.note;

import lombok.val;
import org.junit.jupiter.api.Test;

import java.util.function.Supplier;

import static org.assertj.core.api.Assertions.assertThat;

class NoteTest {
    @Test
    void returnsDisplayStringWhenNatural() {
        val note = Note.getA();

        val actual = note.getDisplayString();

        assertThat(actual).isEqualTo("A");
    }

    @Test
    void returnsDisplayStringWhenSharp() {
        val note = Note.getB().sharpen();

        val actual = note.getDisplayString();

        assertThat(actual).isEqualTo("B#");
    }

    @Test
    void returnsDisplayStringWhenFlat() {
        val note = Note.getD().flatten();

        val actual = note.getDisplayString();

        assertThat(actual).isEqualTo("Db");
    }

    @Test
    void equatesEquivalentNotes() {
        Supplier<Note> getNote = () -> new Note("A", Accidental.getSharp());

        val first = getNote.get();
        val second = getNote.get();

        assertThat(first).isEqualTo(second);
        assertThat(first).isGreaterThanOrEqualTo(second);
        assertThat(first).isLessThanOrEqualTo(second);
    }

    @Test
    void compareNonEquivalentNotes() {
        val lower = Note.getC();

        val higher = Note.getA();

        assertThat(lower).isLessThan(higher);
    }
    // TODO: > and < with #s and bs
}
