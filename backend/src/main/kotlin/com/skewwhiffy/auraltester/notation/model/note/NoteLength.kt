package com.skewwhiffy.auraltester.notation.model.note

import com.skewwhiffy.auraltester.fraction.Fraction

data class NoteLength(val length: Fraction) {
    constructor(length: Int): this(Fraction(length))

    companion object {
        val breve: NoteLength
            get() = NoteLength(2)

        val semibreve: NoteLength
            get() = NoteLength(1)

        val minim: NoteLength
            get() = NoteLength(Fraction(1, 2))

        val crotchet: NoteLength
            get() = NoteLength(Fraction(1, 4))

        val quaver: NoteLength
            get() = NoteLength(Fraction(1, 8))

        val semiquaver: NoteLength
            get() = NoteLength(Fraction(1, 16))

        val demisemiquaver: NoteLength
            get() = NoteLength(Fraction(1, 32))

        val hemidemisemiquaver: NoteLength
            get() = NoteLength(Fraction(1, 64))

    }

    val dotted by lazy { NoteLength(Fraction(3, 2) * length) }

    val abc: String
        get() = length.topHeavyString
}
