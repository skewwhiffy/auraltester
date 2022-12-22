package com.skewwhiffy.test.util

import com.skewwhiffy.notation.factory.ClefFactory
import com.skewwhiffy.notation.factory.NoteFactory
import com.skewwhiffy.notation.model.AbsoluteNote
import com.skewwhiffy.notation.model.Accidental
import com.skewwhiffy.notation.model.Note
import com.skewwhiffy.notation.model.Octave
import java.util.Random
import java.util.UUID

object TestData {
  val random = RandomTestData
  val noteFactories = NoteFactories
}

object RandomTestData {
  private val random = Random()

  val string: String get() = "${UUID.randomUUID()}"

  private fun oneOf(source: String) = source
    .toCharArray()[random.nextInt(source.length)]
    .toString()

  fun <T> oneOf(vararg source: T): T = source[random.nextInt(source.size)]

  // fun <T> oneOf(source: List<T>): T = source[random.nextInt(source.size)]

  val absoluteNote: AbsoluteNote get() = AbsoluteNote(note, octave)

  private val accidental: Accidental get() = Accidental(random.nextInt(3) - 1)

  /*
  def clef: Clef = Clef(string, absoluteNote, absoluteNote)

  def directedInterval: DirectedInterval = if (random.nextBoolean()) interval.up else interval.down

  def interval: Interval = Interval(random.nextInt(9), random.nextInt(3) - 1)

  def key: Key = Key(note, isMinor = random.nextBoolean())
  */

  //noinspection SpellCheckingInspection
  val note: Note get() = Note(oneOf("ABCDEFG"), accidental)

  val octave: Octave get() = Octave(random.nextInt(8) - 4)

  /*
  def scaleType: ScaleType = new ScaleType(
    string,
    string,
    Range(0, 7).map(_ => directedInterval).toList,
    Range(1, 8).map(_ => directedInterval).toList
  )
   */
}

object NoteFactories {
  val note: NoteFactory = NoteFactory()
  val clef: ClefFactory = ClefFactory(note)

  /*
  val interval: IntervalFactory = new IntervalFactory()
  val key: KeyFactory = new KeyFactory(note)
  val internalNotation: InternalNotationFactory = InternalNotationFactory(
    clef,
    /*
    interval,
    key,
     */
    note
  )
  val scaleTypeFactory: ScaleTypeFactory = new ScaleTypeFactory (interval)
   */
}