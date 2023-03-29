package com.skewwhiffy.auraltester.notation.model.note;

import com.skewwhiffy.auraltester.notation.model.key.Key;

import java.util.Optional;

public record AbsoluteNote(Note note, Octave octave, Optional<String> lyric) {
    /*
  fun apply(interval: DirectedInterval): AbsoluteNote {
    return when (interval.direction) {
      IntervalDirection.UP -> this + interval.interval
      IntervalDirection.DOWN -> this - interval.interval
    }
  }
  */

    public AbsoluteNote withLyric(String lyric) {
        return new AbsoluteNote(note, octave, Optional.of(lyric));
    }
        /*
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
      else -> throw IllegalArgumentException("Interval degree of ${interval.degree} not supported")
    }

    return interval.deviation.let {
      when {
        it < 0 -> (1..-it).fold(defaultNote) { note, _ -> note.flat }
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
  */

    public String getAbc(Key key) {
        return key.getAbc(this);
    }

    public String getWordAbc() {
        return lyric.orElse("*");
    }

    /*
  private val sharp: AbsoluteNote get() = AbsoluteNote(note.sharp, octave)

  private val flat: AbsoluteNote get() = AbsoluteNote(note.flat, octave)

  operator fun compareTo(other: AbsoluteNote): Int {
    return if (this == other) 0
    else if (octave == other.octave) this.note.compareTo(other.note)
    else octave.compareTo(other.octave)
  }

  override fun toString(): String = abc(Key.cMajor)

  private val upMajorSecond
    get() = AbsoluteNote(
      note.upMajorSecond,
      (if ("B" == note.noteName) octave.up else octave)
    )

  private val upMinorSecond
    get() = upMajorSecond.let { AbsoluteNote(it.note.flat, it.octave) }

  private val downMajorSecond
    get() = AbsoluteNote(
      note.downMajorSecond,
      (if ("C" == note.noteName) octave.down else octave)
    )

  private val downMinorSecond
    get() = downMajorSecond.let { AbsoluteNote(it.note.sharp, it.octave) }

}
     */
    public static AbsoluteNote getMiddleC() {
        return new AbsoluteNote(Note.getC(), Octave.getDefault(), Optional.empty());
    }
}
