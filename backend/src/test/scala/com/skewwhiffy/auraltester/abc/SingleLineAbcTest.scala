package com.skewwhiffy.auraltester.abc

import com.skewwhiffy.auraltester.clefs.Clef
import com.skewwhiffy.auraltester.notes.{AbsoluteNote, NoteLength}
import com.skewwhiffy.auraltester.scales.Scale
import com.skewwhiffy.auraltester.testutils.TestData
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.extension.ExtendWith
import org.junit.jupiter.api.{BeforeEach, Test}
import org.mockito.Mockito.{mock, when}
import org.mockito.junit.jupiter.MockitoExtension

class SingleLineAbcTest:
  private var title: String = _
  private var clef: Clef = _
  private var noteLength: NoteLength = _
  private var notes: List[AbsoluteNote] = _
  private var notesAbc: List[String] = _

  @BeforeEach
  def setUp(): Unit = {
    title = TestData.random.string
    clef = TestData.random.oneOf(Clef.treble, Clef.alto, Clef.tenor, Clef.bass)
    noteLength = TestData.random.oneOf(
      NoteLength.breve,
      NoteLength.semibreve,
      NoteLength.minim,
      NoteLength.crotchet,
      NoteLength.quaver
    )
    notes = Range(0, 10).map(_ => mock(classOf[AbsoluteNote])).toList
    notesAbc = notes.map(_ => TestData.random.string)
    notes.zip(notesAbc).foreach(pair => when(pair._1.abc).thenReturn(pair._2))
  }

  @Test
  def when_titleSupplied_then_titlePopulated(): Unit = {
    val abc = SingleLineAbc(title, clef, noteLength, notes)

    assertThat(abc.abc).contains(s"T:$title")
    assertThat(abc.abc).contains(notesAbc.mkString)
  }
