package com.skewwhiffy.auraltester.helper

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class StringHelperTest {
    @Test
    fun when_getTitleCase_then_eachWordIsCapitalized() {
        val source = "this is a title"
        val expected = "This Is A Title"

        val actual = getTitleCase(*source.split(" ").toTypedArray())

        assertThat(actual).isEqualTo(expected)
    }
}
