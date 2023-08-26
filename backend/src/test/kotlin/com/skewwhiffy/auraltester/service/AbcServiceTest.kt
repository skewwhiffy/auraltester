package com.skewwhiffy.auraltester.service

import com.skewwhiffy.auraltester.helper.getTitleCase
import com.skewwhiffy.auraltester.notation.model.abc.SingleLineAbc
import com.skewwhiffy.auraltester.notation.model.note.NoteLength
import com.skewwhiffy.auraltester.test.util.TestData
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.junit5.MockKExtension
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(MockKExtension::class)
class AbcServiceTest {
    @InjectMockKs
    private lateinit var abcService: AbcService

    @Test
    fun getsCorrectAbcForScale() {
        val clef = TestData.random.clef
        val scale = TestData.random.scale
        val expected = SingleLineAbc(
            getTitleCase("${scale.displayName} ${scale.direction.displayString}"),
            clef,
            NoteLength.semibreve,
            listOf(scale.notes)
        )

        val actual = abcService.getAbc(clef, scale)

        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun getsCorrectAbcForScaleWithKeySignature() {
        val clef = TestData.random.clef
        val scale = TestData.random.scale
        val key = TestData.random.key
        val expected = SingleLineAbc("${scale.displayName} ${scale.direction.displayString} (with key signature)",
            clef,
            NoteLength.semibreve,
            key,
            listOf(scale.notes)
        )

        val actual = abcService.getAbc(clef, scale, key)

        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun getsCorrectAbcForClef() {
        val clef = TestData.random.clef
        val expected = SingleLineAbc(
            "${clef.displayName} Clef Notes",
            clef,
            NoteLength.semibreve,
            clef.notes.map { it.notes }
        )

        val actual = abcService.getAbc(clef)

        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun getsCorrectAbcForInterval() {
        val clef = TestData.random.clef
        val intervalNotes = TestData.random.intervalNotes
        val key = TestData.random.key
        val expected = SingleLineAbc(
            getTitleCase(intervalNotes.interval.displayString),
            clef,
            NoteLength.semibreve,
            key,
            listOf(intervalNotes.notes)
        )

        val actual = abcService.getAbc(clef, intervalNotes, key)

        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun getsCorrectAbcForKey() {
        val clef = TestData.random.clef
        val key = TestData.random.key
        val expected = SingleLineAbc(
            getTitleCase("${key.displayString} / ${key.relative.displayString}"),
            clef,
            NoteLength.semibreve,
            key,
            listOf(listOf())
        )

        val actual = abcService.getAbc(clef, key)

        assertThat(actual).isEqualTo(expected)
    }
}
