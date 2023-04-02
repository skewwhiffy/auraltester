package com.skewwhiffy.auraltester.notation.model.note;

import com.skewwhiffy.auraltester.fraction.Fraction;
import lombok.val;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class NoteLengthTest {
    @Test
    void correctlyAddsDot() {
        val original = NoteLength.getCrotchet();
        val expected = new NoteLength(new Fraction(3, 8));

        val actual = original.dotted();

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void instantiatesSpecializedNoteLengths() {
        val breve = NoteLength.getBreve();
        val semibreve = NoteLength.getSemibreve();
        val minim = NoteLength.getMinim();
        val crotchet = NoteLength.getCrotchet();
        val quaver = NoteLength.getQuaver();
        val semiquaver = NoteLength.getSemiquaver();
        val demisemiquaver = NoteLength.getDemisemiquaver();
        val hemidemisemiquaver = NoteLength.getHemidemisemiquaver();

        assertThat(breve.getAbc()).isEqualTo("2");
        assertThat(semibreve.getAbc()).isEqualTo("1");
        assertThat(minim.getAbc()).isEqualTo("1/2");
        assertThat(crotchet.getAbc()).isEqualTo("1/4");
        assertThat(quaver.getAbc()).isEqualTo("1/8");
        assertThat(semiquaver.getAbc()).isEqualTo("1/16");
        assertThat(demisemiquaver.getAbc()).isEqualTo("1/32");
        assertThat(hemidemisemiquaver.getAbc()).isEqualTo("1/64");
    }
}
