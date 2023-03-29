package com.skewwhiffy.auraltester.notation.model.note;

import com.skewwhiffy.auraltester.fraction.Fraction;

public record NoteLength(Fraction length) {
    public NoteLength(int length) {
        this(new Fraction(length, 1));
    }
    /*
  val dotted
    get() = NoteLength((Fraction(3, 2) * length))
     */

    public String getAbc() {
        return length.getTopHeavyString();
    }

    public static NoteLength getBreve() {
        return new NoteLength(2);
    }

    public static NoteLength getSemibreve() {
        return new NoteLength(1);
    }

    public static NoteLength getMinim() {
        return new NoteLength(new Fraction(1, 2));
    }

    public static NoteLength getCrotchet() {
        return new NoteLength(new Fraction(1, 4));
    }

    public static NoteLength getQuaver() {
        return new NoteLength(new Fraction(1, 8));
    }

    public static NoteLength getSemiquaver() {
        return new NoteLength(new Fraction(1, 16));
    }

    public static NoteLength getDemisemiquaver() {
        return new NoteLength(new Fraction(1, 32));
    }

    public static NoteLength getHemidemisemiquaver() {
        return new NoteLength(new Fraction(1, 64));
    }

}
