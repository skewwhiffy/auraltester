package com.skewwhiffy.auraltester.notation.model.abc

import com.skewwhiffy.auraltester.helper.oneOf
import com.skewwhiffy.auraltester.notation.factory.ClefFactory
import com.skewwhiffy.auraltester.notation.factory.NoteFactory
import com.skewwhiffy.auraltester.notation.model.clef.Clef
import com.skewwhiffy.auraltester.notation.model.key.Key
import com.skewwhiffy.auraltester.notation.model.note.AbsoluteNote
import com.skewwhiffy.auraltester.notation.model.note.NoteLength
import com.skewwhiffy.auraltester.test.util.TestData
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class SingleLineAbcTest {
    private lateinit var title: String
    private lateinit var clef: Clef
    private lateinit var noteLength: NoteLength
    private lateinit var notes: List<AbsoluteNote>
    private lateinit var notesAbc: List<String>

    @BeforeEach
    fun setUp() {
        val noteFactory = NoteFactory()
        val clefFactory = ClefFactory(noteFactory)
        title = TestData.random.string
        clef = oneOf(
            clefFactory.treble,
            clefFactory.alto,
            clefFactory.tenor,
            clefFactory.bass
        )
        noteLength = oneOf(
            NoteLength.breve,
            NoteLength.semibreve,
            NoteLength.minim,
            NoteLength.crotchet,
            NoteLength.quaver
        )
        notes = (1..9).map { TestData.random.absoluteNote }
        notesAbc = notes.map { it.getAbc(Key.cMajor) }
    }

    @Test
    fun populatesTitle() {
        val abc = SingleLineAbc(title, clef, noteLength, listOf(notes))
        assertThat(abc.abc).contains("X:1")
        assertThat(abc.abc).contains("T:$title")
        assertThat(abc.abc).contains("K:clef=" + clef.abc)
        assertThat(abc.abc).contains("L:" + noteLength.abc)
        assertThat(abc.abc).contains(notesAbc.joinToString())
    }

    @Test
    fun notPopulateTitle() {
        val abc = SingleLineAbc(clef, noteLength, listOf(notes))
        assertThat(abc.abc).doesNotContain("T:")
    }
}
