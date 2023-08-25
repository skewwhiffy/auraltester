package com.skewwhiffy.auraltester.dao

import com.skewwhiffy.auraltester.notation.model.note.Octave

data class OctaveDao(val offsetFromDefault: Int) {
    val model
        get() = Octave(offsetFromDefault)
}
