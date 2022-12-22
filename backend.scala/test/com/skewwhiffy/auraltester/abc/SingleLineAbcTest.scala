package com.skewwhiffy.auraltester.abc

import com.skewwhiffy.auraltester.clefs.{Clef, ClefFactory}
import com.skewwhiffy.auraltester.internalnotation.NoteFactory
import com.skewwhiffy.auraltester.notes.{AbsoluteNote, NoteLength}
import com.skewwhiffy.auraltester.scales.Key
import com.skewwhiffy.auraltester.testutils.{MockInstantiation, TestData}
import org.scalatest.{BeforeAndAfter, GivenWhenThen}
import org.scalatest.flatspec.AnyFlatSpec

class SingleLineAbcTest extends AnyFlatSpec with MockInstantiation with GivenWhenThen with BeforeAndAfter {
  private var title: String = _
  private var clef: Clef = _
  private var noteLength: NoteLength = _
  private var notes: List[AbsoluteNote] = _
  private var notesAbc: List[String] = _

  before {
    val noteFactory = new NoteFactory()
    val clefFactory = new ClefFactory(noteFactory)
    title = TestData.random.string
    clef = TestData.random.oneOf(clefFactory.treble, clefFactory.alto, clefFactory.tenor, clefFactory.bass)
    noteLength = TestData.random.oneOf(
      NoteLength.breve,
      NoteLength.semibreve,
      NoteLength.minim,
      NoteLength.crotchet,
      NoteLength.quaver
    )
    notesAbc = Range(0, 10).map(_ => TestData.random.string).toList
    notes = notesAbc.map(it => {
      val note = mock[AbsoluteNote]
      when(note.abc(Key.cMajor)).thenReturn(it)
      note
    })
  }

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