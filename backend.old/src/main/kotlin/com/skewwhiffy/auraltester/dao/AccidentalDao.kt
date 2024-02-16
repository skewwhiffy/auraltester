package com.skewwhiffy.auraltester.dao

import com.skewwhiffy.auraltester.notation.model.note.Accidental

data class AccidentalDao(val offset: Int) {
    val model
        get() = Accidental(offset)
}
