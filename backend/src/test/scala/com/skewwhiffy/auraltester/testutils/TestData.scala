package com.skewwhiffy.auraltester.testutils

import com.skewwhiffy.auraltester.clefs.ClefFactory
import com.skewwhiffy.auraltester.internalnotation.{InternalNotationFactory, IntervalFactory, NoteFactory}
import com.skewwhiffy.auraltester.notes.{AbsoluteNote, Accidental, Note, Octave}

import java.util.UUID
import scala.util.Random

object TestData {
  object random {
    private lazy val random = new Random()

    def string: String = s"${UUID.randomUUID()}"

    def oneOf(source: String): Char = source.charAt(random.nextInt(source.length))

    def oneOf[T](source: T*): T = source(random.nextInt(source.size))

    def oneOf[T](source: List[T]): T = source(random.nextInt(source.size))

    //noinspection SpellCheckingInspection
    def absoluteNote: AbsoluteNote = {
      val octave = new Octave(random.nextInt(8) - 4)
      val accidental = new Accidental(random.nextInt(3) - 1)
      val note = new Note(oneOf("ABCDEFG"), accidental)
      new AbsoluteNote(note, octave)
    }
  }

  object noteFactories {
    lazy val note: NoteFactory = new NoteFactory()
    lazy val interval: IntervalFactory = new IntervalFactory()
    lazy val clef: ClefFactory = new ClefFactory(note)
    lazy val internalNotation: InternalNotationFactory = new InternalNotationFactory(
      clef,
      note,
      interval
    )
  }
}
