package com.skewwhiffy.auraltester.helper

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

import java.util.HashMap

class CollectionHelperTest {
    @Test
    fun when_getOneOf_then_selectsEachOneWithRoughlyEqualProbability() {
        val source = (1..10).toList()
        val count = HashMap<Int, Int>()

        (1..1000).forEach {
            val actual = oneOf(source)
            count[actual] = (count[actual] ?: 0) + 1
        }

        assertThat(count.keys).containsExactlyElementsOf(source)
    }
}
