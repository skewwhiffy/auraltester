package com.skewwhiffy.notation.model

data class AbsoluteNote(val note: Note, val octave: Octave, val lyric: String? = null) {
  companion object {
    val middleC: AbsoluteNote = AbsoluteNote(Note.c, Octave.default)
  }

  /*

  def apply(interval: DirectedInterval): AbsoluteNote = {
    interval.direction match {
      case IntervalDirection.`up` => add(interval.interval)
      case IntervalDirection.`down` => subtract(interval.interval)
    }
  }

*/
  fun withLyric(lyric: String): AbsoluteNote = copy(lyric = lyric)

  operator fun plus(interval: Interval): AbsoluteNote {
    val defaultNote: AbsoluteNote = when (interval.degree) {
      1 -> this
      2 -> upMajorSecond
      3 -> upMajorSecond.upMajorSecond
      4 -> (this + Interval.major(3)).upMinorSecond
      5 -> (this + Interval.perfect(4)).upMajorSecond
      6 -> (this + Interval.perfect(5)).upMajorSecond
      7 -> (this + Interval.major(6)).upMajorSecond
      8 -> AbsoluteNote(note, octave.up)
      else -> throw IllegalArgumentException()
    }

    return interval.deviation.let {
      when {
        it < 0 -> (0..-it).fold(defaultNote) { note, _ -> note.flat }
        it > 0 -> (1..it).fold(defaultNote) { note, _ -> note.sharp }
        else -> defaultNote
      }
    }
  }

  operator fun minus(interval: Interval): AbsoluteNote {
    val defaultNote: AbsoluteNote = when (interval.degree) {
      1 -> this
      2 -> downMajorSecond
      3 -> downMajorSecond.downMajorSecond
      4 -> (this - Interval.major(3)).downMinorSecond
      5 -> (this - Interval.perfect(4)).downMajorSecond
      6 -> (this - Interval.perfect(5)).downMajorSecond
      7 -> (this - Interval.major(6)).downMajorSecond
      8 -> AbsoluteNote(note, octave.down)
      else -> throw IllegalArgumentException()
    }

    return interval.deviation.let {
      when {
        it < 0 -> (1..-it).fold(defaultNote) { note, _ -> note.sharp }
        it > 0 -> (1..it).fold(defaultNote) { note, _ -> note.flat }
        else -> defaultNote
      }
    }
  }

  fun abc(key: Key): String = key.abc(this)

  val wordAbc: String = (lyric ?: "").ifEmpty { "*" }

  private val sharp: AbsoluteNote get() = AbsoluteNote(note.sharp, octave)

  private val flat: AbsoluteNote get() = AbsoluteNote(note.flat, octave)

  /*
  def <=(other: AbsoluteNote): Boolean = this < other || this == other

  def >=(other: AbsoluteNote): Boolean = this > other || this == other

  def <(other: AbsoluteNote): Boolean = this.octave < other.octave ||
    (this.octave == other.octave && this.note < other.note)

  def >(other: AbsoluteNote): Boolean = this.octave > other.octave ||
    (this.octave == other.octave && this.note > other.note)

  override def toString: String = abc(Key.cMajor)
  */
  private val upMajorSecond
    get() = (if ("B" == note.noteName) octave.up else octave)
      .let { AbsoluteNote(note.upMajorSecond, it) }

  private val upMinorSecond
    get() = upMajorSecond.let { AbsoluteNote(it.note.flat, it.octave) }

  private val downMajorSecond
    get() = (if ("C" == note.noteName) octave.down else octave)
      .let { AbsoluteNote(note.downMajorSecond, it) }

  private val downMinorSecond
    get() = downMajorSecond.let { AbsoluteNote(it.note.sharp, it.octave) }

}