package com.skewwhiffy.auraltester.notation.model.note

import com.skewwhiffy.auraltester.dao.OctaveDao

// @JvmRecord
data class Octave(val offsetFromDefault: Int) : Comparable<Octave?> {
    companion object {
        val default: Octave
            get() = Octave(0)
    }

    override fun compareTo(other: Octave?): Int {
        if (other == null) {
            return 1
        }
        return offsetFromDefault - other.offsetFromDefault
    }

}

val Octave.dao
    get() = OctaveDao(offsetFromDefault)

val Octave.up
    get() = Octave(offsetFromDefault + 1)

val Octave.down
    get() = Octave(offsetFromDefault - 1)

