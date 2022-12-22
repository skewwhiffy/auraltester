package com.skewwhiffy.auraltester.testutils

import com.skewwhiffy.auraltester.clefs.{Clef, ClefFactory}
import com.skewwhiffy.auraltester.internalnotation.{InternalNotationFactory, IntervalFactory, KeyFactory, NoteFactory}
import com.skewwhiffy.auraltester.notes.{AbsoluteNote, Accidental, DirectedInterval, Interval, Note, Octave}
import com.skewwhiffy.auraltester.scales.{Key, ScaleType, ScaleTypeFactory}

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
    def absoluteNote: AbsoluteNote = AbsoluteNote(note, octave)

    def accidental: Accidental = Accidental(random.nextInt(3) - 1)

    def clef: Clef = Clef(string, absoluteNote, absoluteNote)

    def directedInterval: DirectedInterval = if (random.nextBoolean()) interval.up else interval.down

    def interval: Interval = Interval(random.nextInt(9), random.nextInt(3) - 1)

    def key: Key = Key(note, isMinor = random.nextBoolean())

    //noinspection SpellCheckingInspection
    def note: Note = Note(oneOf("ABCDEFG"), accidental)

    def octave: Octave = Octave(random.nextInt(8) - 4)

    def scaleType: ScaleType = new ScaleType(
      string,
      string,
      Range(0, 7).map(_ => directedInterval).toList,
      Range(1, 8).map(_ => directedInterval).toList
    )
  }

  object noteFactories {
    lazy val clef: ClefFactory = new ClefFactory(note)
    lazy val interval: IntervalFactory = new IntervalFactory()
    lazy val key: KeyFactory = new KeyFactory(note)
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
