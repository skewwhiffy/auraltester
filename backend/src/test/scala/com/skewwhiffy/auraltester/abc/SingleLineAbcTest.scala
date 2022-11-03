package com.skewwhiffy.auraltester.abc

import com.skewwhiffy.auraltester.clefs.{Clef, ClefFactory}
import com.skewwhiffy.auraltester.internalnotation.NoteFactory
import com.skewwhiffy.auraltester.notes.{AbsoluteNote, NoteLength}
import com.skewwhiffy.auraltester.scales.Key
import com.skewwhiffy.auraltester.testutils.{MockInstantiation, TestData}
import org.scalatest.BeforeAndAfter
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should

class SingleLineAbcTest extends AnyFlatSpec with MockInstantiation with should.Matchers with BeforeAndAfter {
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

  it should "when title supplied then title populated" in {
    val abc = new SingleLineAbc(title, clef, noteLength, notes)

    assert(abc.abc.contains("X:1"))
    assert(abc.abc.contains(s"T:$title"))
    assert(abc.abc.contains(s"K:clef=${clef.abc}"))
    assert(abc.abc.contains(s"L:${noteLength.abc}"))
    assert(abc.abc.contains(notesAbc.mkString))
  }

  it should "when title not supplied then title not populated" in {
    val abc = new SingleLineAbc(clef, noteLength, notes)

    assert(!abc.abc.contains("T:"))
  }
}