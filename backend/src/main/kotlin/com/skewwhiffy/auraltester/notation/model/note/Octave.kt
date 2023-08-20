package com.skewwhiffy.auraltester.notation.model.note

// @JvmRecord
data class Octave(val offsetFromDefault: Int) : Comparable<Octave?> {
    companion object {
        val default: Octave
            get() = Octave(0)
    }

    /*
    fun toDao(): OctaveDao {
        return OctaveDao(offsetFromDefault)
    }
    */

    val up: Octave
        get() = Octave(offsetFromDefault + 1)

    val down: Octave
        get() = Octave(offsetFromDefault - 1)

    override fun compareTo(other: Octave?): Int {
        if (other == null) {
            return 1
        }
        return offsetFromDefault - other.offsetFromDefault
    }

}
