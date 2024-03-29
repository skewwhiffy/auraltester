package com.skewwhiffy.auraltester.notation.model.note

import com.skewwhiffy.auraltester.dao.AccidentalDao

data class Accidental(val offset: Int) {
    companion object {
        val natural: Accidental
            get() {
                return Accidental(0)
            }

        val flat: Accidental
            get() {
                return Accidental(-1)
            }

        val sharp: Accidental
            get() {
                return Accidental(1)
            }
    }

}

val Accidental.dao
    get() = AccidentalDao(offset)

val Accidental.abc
    get() = if (offset == 0) ""
    else if (offset < 0) "_".repeat(-offset)
    else "^".repeat(offset)

val Accidental.displayString: String
    get() {
        if (offset < 0) {
            return "b".repeat(-offset)
        }
        if (offset % 2 == 0) {
            return "x".repeat(offset / 2)
        }
        return "x".repeat((offset - 1) / 2) + "#"
    }

val Accidental.flatten
    get() = Accidental(offset - 1)

val Accidental.sharpen
    get() = Accidental(offset + 1)
