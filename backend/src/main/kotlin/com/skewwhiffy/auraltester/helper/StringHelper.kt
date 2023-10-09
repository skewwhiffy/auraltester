package com.skewwhiffy.auraltester.helper

fun getTitleCase(vararg sourceElements: String) = sourceElements
    .joinToString(" ") { if (it == "") "" else it.substring(0, 1).uppercase() + it.substring(1) }
