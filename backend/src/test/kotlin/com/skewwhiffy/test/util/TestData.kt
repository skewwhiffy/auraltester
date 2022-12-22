package com.skewwhiffy.test.util

import com.skewwhiffy.notation.model.AbsoluteNote
import com.skewwhiffy.notation.model.Accidental
import com.skewwhiffy.notation.model.Note
import com.skewwhiffy.notation.model.Octave
import java.util.Random
import java.util.UUID

interface RandomTestData {
  /*
      private lazy val random = new Random()

*/
  val string: String

  fun oneOf(source: String): String

  /*
    def oneOf[T](source: T*): T = source(random.nextInt(source.size))

    def oneOf[T](source: List[T]): T = source(random.nextInt(source.size))

*/
  val absoluteNote: AbsoluteNote

  val accidental: Accidental

  /*
    def clef: Clef = Clef(string, absoluteNote, absoluteNote)

    def directedInterval: DirectedInterval = if (random.nextBoolean()) interval.up else interval.down

    def interval: Interval = Interval(random.nextInt(9), random.nextInt(3) - 1)

    def key: Key = Key(note, isMinor = random.nextBoolean())

*/
  val note: Note

  val octave: Octave

  /*
    def scaleType: ScaleType = new ScaleType(
      string,
      string,
      Range(0, 7).map(_ => directedInterval).toList,
      Range(1, 8).map(_ => directedInterval).toList
    )
   */

}

object TestData {
  val random = object : RandomTestData {
    val random = Random()
    override val string: String get() = "${UUID.randomUUID()}"

    override fun oneOf(source: String) = source
      .toCharArray()[random.nextInt(source.length)]
      .toString()

    /*
    def oneOf[T](source: T*): T = source(random.nextInt(source.size))

    def oneOf[T](source: List[T]): T = source(random.nextInt(source.size))

*/
    override val absoluteNote: AbsoluteNote get() = AbsoluteNote(note, octave)

    override val accidental: Accidental get() = Accidental(random.nextInt(3) - 1)

    /*
    def clef: Clef = Clef(string, absoluteNote, absoluteNote)

    def directedInterval: DirectedInterval = if (random.nextBoolean()) interval.up else interval.down

    def interval: Interval = Interval(random.nextInt(9), random.nextInt(3) - 1)

    def key: Key = Key(note, isMinor = random.nextBoolean())
    */

    //noinspection SpellCheckingInspection
    override val note: Note get() = Note(oneOf("ABCDEFG"), accidental)

    override val octave: Octave get() = Octave(random.nextInt(8) - 4)

    /*
    def scaleType: ScaleType = new ScaleType(
      string,
      string,
      Range(0, 7).map(_ => directedInterval).toList,
      Range(1, 8).map(_ => directedInterval).toList
    )
     */
  }
}