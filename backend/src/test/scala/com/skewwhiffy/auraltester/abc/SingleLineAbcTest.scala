package com.skewwhiffy.auraltester.abc

import com.skewwhiffy.auraltester.clefs.Clef
import com.skewwhiffy.auraltester.notes.{AbsoluteNote, Note, NoteLength, Octave}
import com.skewwhiffy.auraltester.testutils.TestData
import org.scalamock.scalatest.MockFactory
import org.scalatest.Outcome
import org.scalatest.funsuite.AnyFunSuite

class SingleLineAbcTest extends AnyFunSuite with MockFactory {
  private var title: String = _
  private var clef: Clef = _
  private var noteLength: NoteLength = _
  private var notes: List[AbsoluteNote] = _
  private var notesAbc: List[String] = _

  override def withFixture(test: NoArgTest): Outcome = {
    title = TestData.random.string
    clef = TestData.random.oneOf(Clef.treble, Clef.alto, Clef.tenor, Clef.bass)
    noteLength = TestData.random.oneOf(
      NoteLength.breve,
      NoteLength.semibreve,
      NoteLength.minim,
      NoteLength.crotchet,
      NoteLength.quaver
    )
    notesAbc = Range(0, 10).map(_ => TestData.random.string).toList
    notes = notesAbc.map(it => {
      class AbsoluteNoteMock extends AbsoluteNote(Note.c, Octave.default) {
        override lazy val abc: String = it
      }
      new AbsoluteNoteMock()
    })
    test()
  }

  test("when title supplied then title populated") {
    val abc = SingleLineAbc(title, clef, noteLength, notes)

    assert(abc.abc.contains("X:1"))
    assert(abc.abc.contains(s"T:$title"))
    assert(abc.abc.contains(s"K:clef=${clef.abc}"))
    assert(abc.abc.contains(s"L:${noteLength.abc}"))
    assert(abc.abc.contains(notesAbc.mkString))
  }

  test("when title not supplied then title not populated") {
    val abc = SingleLineAbc(clef, noteLength, notes)

    assert(!abc.abc.contains("T:"))
  }
}