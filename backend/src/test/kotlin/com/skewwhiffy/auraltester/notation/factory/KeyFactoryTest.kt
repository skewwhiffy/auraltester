package com.skewwhiffy.auraltester.notation.factory

import com.ninjasquad.springmockk.MockkBean
import com.skewwhiffy.auraltester.test.util.TestData
import io.mockk.every
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class KeyFactoryTest {
    @MockkBean
    private lateinit var noteFactory: NoteFactory

    @Autowired
    private lateinit var keyFactory: KeyFactory

    @Test
    fun instantiatesMajorKey() {
        val rawKey = "a#"
        val expectedNote = TestData.random.note
        every { noteFactory.getNote("A#") } returns expectedNote
        val actual = keyFactory.getKey(rawKey)
        assertThat(actual.note).isEqualTo(expectedNote)
        assertThat(actual.isMinor).isFalse()
    }

    @Test
    fun recognizesMSuffixForMinor() {
        val rawKey = "abm"
        val expectedNote = TestData.random.note
        every { noteFactory.getNote("Ab") } returns expectedNote
        val actual = keyFactory.getKey(rawKey)
        assertThat(actual.note).isEqualTo(expectedNote)
        assertThat(actual.isMinor).isTrue()
    }

    @Test
    fun recognizesMinorSuffixForMinor() {
        val rawKey = "bx minor"
        val expectedNote = TestData.random.note
        every { noteFactory.getNote("Bx") } returns expectedNote
        val actual = keyFactory.getKey(rawKey)
        assertThat(actual.note).isEqualTo(expectedNote)
        assertThat(actual.isMinor).isTrue()
    }
}
