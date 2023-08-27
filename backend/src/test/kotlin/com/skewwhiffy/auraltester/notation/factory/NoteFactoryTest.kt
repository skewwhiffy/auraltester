package com.skewwhiffy.auraltester.notation.factory

import com.skewwhiffy.auraltester.notation.model.key.Key
import com.skewwhiffy.auraltester.notation.model.note.AbsoluteNote
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class NoteFactoryTest {
    @Autowired
    private lateinit var noteFactory: NoteFactory

    @Test
    fun instantiatesMiddleC() {
        val expected = AbsoluteNote.middleC
        val actual = noteFactory.getAbsoluteNote("C")
        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun instantiatesNoteAboveMiddleC() {
        val expected = "c''"
        val actual = noteFactory.getAbsoluteNote(expected)
        assertThat(actual.getAbc(Key.cMajor)).isEqualTo(expected)
    }

    @Test
    fun instantiatesNoteBelowMiddleC() {
        val internalNotation = "Dx#,,,"
        val expected = "^^^D,,,"
        val actual = noteFactory.getAbsoluteNote(internalNotation)
        assertThat(actual.getAbc(Key.cMajor)).isEqualTo(expected)
    }

    @Test
    fun when_noteNameInvalid_then_throws() {
        assertThatThrownBy { noteFactory.getAbsoluteNote("H") }
            .isInstanceOf(IllegalArgumentException::class.java)
    }
}

