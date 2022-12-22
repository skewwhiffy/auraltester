package com.skewwhiffy.notation.model.abc

import com.skewwhiffy.notation.factory.ClefFactory
import com.skewwhiffy.notation.factory.NoteFactory
import com.skewwhiffy.notation.model.AbsoluteNote
import com.skewwhiffy.notation.model.Clef
import com.skewwhiffy.notation.model.Key
import com.skewwhiffy.notation.model.NoteLength
import com.skewwhiffy.test.util.TestData
import org.junit.jupiter.api.BeforeEach
import org.mockito.Mockito.mock

import org.mockito.Mockito.`when`

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
    notesAbc = (1..10).map { TestData.random.string }.toList()
    notes = notesAbc.map {
      val note: AbsoluteNote = mock()
      `when`(note.abc(Key.cMajor)).thenReturn(it)
      note
    }
  }

  /*
  it should "populate title" in {
    Given("title is supplied")

    When("getting single line ABC")
    val abc = SingleLineAbc(title, clef, noteLength, List(notes))

    Then("abc has index")
    assert(abc.abc.contains("X:1"))
    And("populates title")
    assert(abc.abc.contains(s"T:$title"))
    And("clef is populated")
    assert(abc.abc.contains(s"K:clef=${clef.abc}"))
    And("note length is populated")
    assert(abc.abc.contains(s"L:${noteLength.abc}"))
    And("notes abc is correct")
    assert(abc.abc.contains(notesAbc.mkString))
  }

  it should "not populate title" in {
    Given("title is not supplied")

    When("getting single line ABC")
    val abc = SingleLineAbc(clef, noteLength, List(notes))

    Then("abc has no title")
    assert(!abc.abc.contains("T:"))
  }

  // TODO: Multiple bars
}
   */
}