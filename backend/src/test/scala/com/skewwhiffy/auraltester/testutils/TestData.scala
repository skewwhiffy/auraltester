package com.skewwhiffy.auraltester.testutils

import com.skewwhiffy.auraltester.clefs.{Clef, ClefFactory}
import com.skewwhiffy.auraltester.internalnotation.{InternalNotationFactory, IntervalFactory, KeyFactory, NoteFactory}
import com.skewwhiffy.auraltester.notes.Interval.Interval
import com.skewwhiffy.auraltester.notes.{AbsoluteNote, Accidental, DirectedInterval, Note, Octave}
import com.skewwhiffy.auraltester.scales.{Key, ScaleTypeFactory}

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
    def absoluteNote: AbsoluteNote = new AbsoluteNote(note, octave)

    def accidental: Accidental = new Accidental(random.nextInt(3) - 1)

    def clef: Clef = new Clef(string, absoluteNote, absoluteNote)

    def directedInterval: DirectedInterval = if (random.nextBoolean()) interval.up else interval.down

    def interval: Interval = new Interval(random.nextInt(9), random.nextInt(3) - 1)

    def key: Key = new Key(note, isMinor = random.nextBoolean())

    def note: Note = new Note(oneOf("ABCDEFG"), accidental)

    def octave: Octave = new Octave(random.nextInt(8) - 4)
  }

  object noteFactories {
    lazy val clef: ClefFactory = new ClefFactory(note)
    lazy val interval: IntervalFactory = new IntervalFactory()
    lazy val key: KeyFactory = new KeyFactory()
    lazy val note: NoteFactory = new NoteFactory()
    lazy val internalNotation: InternalNotationFactory = new InternalNotationFactory(
      clef,
      interval,
      key,
      note
    )
    lazy val scaleTypeFactory: ScaleTypeFactory = new ScaleTypeFactory(interval)
  }
}
