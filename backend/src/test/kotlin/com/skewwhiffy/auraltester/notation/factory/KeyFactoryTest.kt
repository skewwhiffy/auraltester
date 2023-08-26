package com.skewwhiffy.auraltester.notation.factory

import com.skewwhiffy.auraltester.test.util.TestData
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(MockKExtension::class)
class KeyFactoryTest {
    @MockK
    private lateinit var noteFactory: NoteFactory

    @InjectMockKs
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
