package com.skewwhiffy.test.util

import com.skewwhiffy.notation.factory.*
import com.skewwhiffy.notation.model.clef.Clef
import com.skewwhiffy.notation.model.interval.DirectedInterval
import com.skewwhiffy.notation.model.interval.Interval
import com.skewwhiffy.notation.model.key.Key
import com.skewwhiffy.notation.model.note.*
import com.skewwhiffy.notation.model.scale.Scale
import com.skewwhiffy.notation.model.scale.ScaleDirection
import com.skewwhiffy.notation.model.scale.ScaleType
import java.util.Random
import java.util.UUID

object TestData {
  val random = RandomTestData
  val noteFactories = NoteFactories
}

object RandomTestData {
  private val random = Random()

  val string: String get() = "${UUID.randomUUID()}"

  @Suppress("SameParameterValue")
  private fun oneOf(source: String): String = source
    .toCharArray()
    .map { it.toString() }
    .toTypedArray()
    .let { oneOf(*it) }

  fun <T> oneOf(vararg source: T): T = oneOf(source.toList())

  fun <T> oneOf(source: List<T>): T = source[random.nextInt(source.size)]

  val absoluteNote: AbsoluteNote get() = AbsoluteNote(note, octave)

  private val accidental: Accidental get() = Accidental(random.nextInt(3) - 1)

  val clef: Clef get() = Clef(string, absoluteNote, absoluteNote)

  val directedInterval: DirectedInterval
    get() = if (random.nextBoolean()) interval.up
    else interval.down

  val interval: Interval get() = Interval(
    oneOf((1..8).toList()),
    random.nextInt(3) - 1
  )

  val intervalNotes: IntervalNotes get() = IntervalNotes(
    absoluteNote,
    interval
  )

  val key: Key get() = Key(note, isMinor = random.nextBoolean())

  @Suppress("SpellCheckingInspection")
  val note: Note get() = Note(oneOf("ABCDEFG"), accidental)

  private val octave: Octave get() = Octave(random.nextInt(8) - 4)

  val scale get(): Scale = Scale(absoluteNote, scaleType, scaleDirection)

  val scaleDirection get(): ScaleDirection = oneOf(*ScaleDirection.values())

  val scaleType: ScaleType
    get() = ScaleType(
      string,
      string,
      (1..7).map { directedInterval }.toList(),
      (1..7).map { directedInterval }.toList()
    )
}

object NoteFactories {
  val note: NoteFactory = NoteFactory()
  val clef: ClefFactory = ClefFactory(note)
  val interval: IntervalFactory = IntervalFactory()

  val key: KeyFactory = KeyFactory(note)

  val internalNotation: InternalNotationFactory = InternalNotationFactory(
    clef,
    interval,
    key,
    note
  )
  val scaleTypeFactory: ScaleTypeFactory = ScaleTypeFactory(interval)
}