package com.skewwhiffy.auraltester.notes

import scala.util.chaining._

object AbsoluteNote:
  lazy val middleC: AbsoluteNote = AbsoluteNote(Note.C, Octave.default)

class AbsoluteNote(val note: Note, val octave: Octave):
  lazy val apply: DirectedInterval => AbsoluteNote = interval => {
    interval.direction match
      case IntervalDirection.Up => add(interval.interval)
      case IntervalDirection.Down => subtract(interval.interval)
  }

  lazy val add: Interval => AbsoluteNote = interval => {
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

  lazy val subtract: Interval => AbsoluteNote = interval => {
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
  lazy val abc: String = octave.getAbc(note)

  override def equals(obj: Any): Boolean = obj match
    case other: AbsoluteNote => other.note == note && other.octave == octave
    case _ => false

  override def toString: String = abc

  private lazy val sharp = AbsoluteNote(note.sharp, octave)

  private lazy val flat = AbsoluteNote(note.flat, octave)

  private lazy val upMinorSecond = upMajorSecond
    .pipe(it => AbsoluteNote(it.note.flat, it.octave))

  private lazy val downMinorSecond = downMajorSecond
    .pipe(it => AbsoluteNote(it.note.sharp, it.octave))

  private lazy val upMajorSecond = (if "B" == note.noteName then octave.up else octave)
    .pipe(it => AbsoluteNote(note.upMajorSecond, it))

  private lazy val downMajorSecond = (if "C" == note.noteName then octave.down else octave)
    .pipe(it => AbsoluteNote(note.downMajorSecond, it))