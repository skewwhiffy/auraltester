package com.skewwhiffy.auraltester.helper

object StringHelper {
    fun getTitleCase(vararg sourceElements: String): String {
        return sourceElements
            .joinToString(" ") { it.substring(0, 1).uppercase() + it.substring(1) }
    }
}
