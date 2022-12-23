package com.skewwhiffy.notation.model.abc

import com.skewwhiffy.notation.factory.ClefFactory
import com.skewwhiffy.notation.factory.NoteFactory
import com.skewwhiffy.notation.model.AbsoluteNote
import com.skewwhiffy.notation.model.Clef
import com.skewwhiffy.notation.model.Key
import com.skewwhiffy.notation.model.NoteLength
import com.skewwhiffy.test.util.TestData
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.io.File

class SingleLineAbcTest {
  private lateinit var title: String
  private lateinit var clef: Clef
  private lateinit var noteLength: NoteLength
  private lateinit var notes: List<AbsoluteNote>
  private lateinit var notesAbc: List<String>

  @BeforeEach
  fun `before each`() {
    val noteFactory = NoteFactory()
    val clefFactory = ClefFactory(noteFactory)
    title = TestData.random.string
    clef = TestData.random.oneOf(clefFactory.treble, clefFactory.alto, clefFactory.tenor, clefFactory.bass)
    noteLength = TestData.random.oneOf(
      NoteLength.breve,
      NoteLength.semibreve,
      NoteLength.minim,
      NoteLength.crotchet,
      NoteLength.quaver
    )
    notes = (1..10).map { TestData.random.absoluteNote }
    notesAbc = notes.map { it.abc(Key.cMajor) }
  }

  @Test
  fun `populates title`() {
    val abc = SingleLineAbc(title, clef, noteLength, listOf(notes))

    assertThat(abc.abc).contains("X:1")
    assertThat(abc.abc).contains("T:$title")
    assertThat(abc.abc).contains("K:clef=${clef.abc}")
    assertThat(abc.abc).contains("L:${noteLength.abc}")
    assertThat(abc.abc).contains(notesAbc.joinToString(""))
  }

  @Test
  fun `not populate title`() {
    val abc = SingleLineAbc(clef, noteLength, listOf(notes))

    assertThat(abc.abc).doesNotContain("T:")
  }

  // TODO: Multiple bars
}