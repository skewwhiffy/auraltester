package com.skewwhiffy.auraltester.notes

import com.skewwhiffy.auraltester.scales.Key

import scala.util.chaining.scalaUtilChainingOps

object AbsoluteNote {
  lazy val middleC: AbsoluteNote = AbsoluteNote(Note.c, Octave.default)
}

case class AbsoluteNote(
  note: Note,
  octave: Octave,
  lyric: Option[String] = None
) {
  def apply(interval: DirectedInterval): AbsoluteNote = {
    interval.direction match {
      case IntervalDirection.`up` => add(interval.interval)
      case IntervalDirection.`down` => subtract(interval.interval)
    }
  }

  def withLyric(lyric: String): AbsoluteNote = copy(lyric = Some(lyric))

  def +(interval: Interval): AbsoluteNote = add(interval)

  private def add(interval: Interval): AbsoluteNote = {
    val defaultNote: AbsoluteNote = interval.degree match {
      case 1 => this
      case 2 => upMajorSecond
      case 3 => upMajorSecond.upMajorSecond
      case 4 => add(Interval.major(3)).upMinorSecond
      case 5 => add(Interval.perfect(4)).upMajorSecond
      case 6 => add(Interval.perfect(5)).upMajorSecond
      case 7 => add(Interval.major(6)).upMajorSecond
      case 8 => AbsoluteNote(note, octave.up)
      case _ => throw new IllegalArgumentException()
    }

    interval.deviation match {
      case it if it < 0 => List.range(0, -it).foldRight(defaultNote)((_, note) => note.flat)
      case it if it > 0 => List.range(0, it).foldRight(defaultNote)((_, note) => note.sharp)
      case 0 => defaultNote
    }
  }

  def -(interval: Interval): AbsoluteNote = subtract(interval)

  private def subtract(interval: Interval): AbsoluteNote = {
    val defaultNote: AbsoluteNote = interval.degree match {
      case 1 => this
      case 2 => downMajorSecond
      case 3 => downMajorSecond.downMajorSecond
      case 4 => subtract(Interval.major(3)).downMinorSecond
      case 5 => subtract(Interval.perfect(4)).downMajorSecond
      case 6 => subtract(Interval.perfect(5)).downMajorSecond
      case 7 => subtract(Interval.major(6)).downMajorSecond
      case 8 => AbsoluteNote(note, octave.down)
      case _ => throw new IllegalArgumentException()
    }

    interval.deviation match {
      case it if it < 0 => List.range(0, -it).foldRight(defaultNote)((_, note) => note.sharp)
      case it if it > 0 => List.range(0, it).foldRight(defaultNote)((_, note) => note.flat)
      case 0 => defaultNote
    }
  }

  def abc(key: Key): String = key.abc(this)

  def wordAbc: String = if (lyric.getOrElse("").isEmpty) "*" else lyric.get

  def sharp: AbsoluteNote = AbsoluteNote(note.sharp, octave)

  def flat: AbsoluteNote = AbsoluteNote(note.flat, octave)

  def <=(other: AbsoluteNote): Boolean = this < other || this == other

  def >=(other: AbsoluteNote): Boolean = this > other || this == other

  def <(other: AbsoluteNote): Boolean = this.octave < other.octave ||
    (this.octave == other.octave && this.note < other.note)

  def >(other: AbsoluteNote): Boolean = this.octave > other.octave ||
    (this.octave == other.octave && this.note > other.note)

  override def toString: String = abc(Key.cMajor)

  private lazy val upMinorSecond = upMajorSecond
    .pipe(it => AbsoluteNote(it.note.flat, it.octave))

  private lazy val downMinorSecond = downMajorSecond
    .pipe(it => AbsoluteNote(it.note.sharp, it.octave))

  private lazy val upMajorSecond = (if ("B" == note.noteName) octave.up else octave)
    .pipe(it => AbsoluteNote(note.upMajorSecond, it))

  private lazy val downMajorSecond = (if ("C" == note.noteName) octave.down else octave)
    .pipe(it => AbsoluteNote(note.downMajorSecond, it))
}