package com.skewwhiffy.auraltester.notation.model.note;

import com.skewwhiffy.auraltester.helper.NoParallelStream;
import com.skewwhiffy.auraltester.notation.model.interval.Interval;
import com.skewwhiffy.auraltester.notation.model.key.Key;
import lombok.val;

import java.text.MessageFormat;
import java.util.Optional;
import java.util.stream.IntStream;

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

    public AbsoluteNote plus(Interval interval) {
        val defaultNote = switch (interval.degree()) {
            case 1 -> this;
            case 2 -> upMajorSecond();
            case 3 -> upMajorSecond().upMajorSecond();
            case 4 -> plus(Interval.major(3)).upMinorSecond();
            case 5 -> plus(Interval.perfect(4)).upMajorSecond();
            case 6 -> plus(Interval.perfect(5)).upMajorSecond();
            case 7 -> plus(Interval.major(6)).upMajorSecond();
            case 8 -> new AbsoluteNote(note, octave.up(), lyric);
            default -> throw new IllegalArgumentException(
                    MessageFormat.format(
                            "Interval degree of ${interval.degree} not supported",
                            interval.degree()
                    )
            );
        };

        val deviation = interval.deviation();
        if (deviation < 0) {
            return IntStream
                    .range(0, -deviation)
                    .boxed()
                    .reduce(defaultNote, (note, it) -> note.flatten(), NoParallelStream.get());
        }
        if (deviation > 0) {
            return IntStream
                    .range(0, deviation)
                    .boxed()
                    .reduce(defaultNote, (note, it) -> note.sharpen(), NoParallelStream.get());
        }
        return defaultNote;
    }

    public AbsoluteNote minus(Interval interval) {
        val defaultNote = switch (interval.degree()) {
            case 1 -> this;
            case 2 -> downMajorSecond();
            case 3 -> downMajorSecond().downMajorSecond();
            case 4 -> minus(Interval.major(3)).downMinorSecond();
            case 5 -> minus(Interval.perfect(4)).downMajorSecond();
            case 6 -> minus(Interval.perfect(5)).downMajorSecond();
            case 7 -> minus(Interval.major(6)).downMajorSecond();
            case 8 -> new AbsoluteNote(note, octave.down(), lyric);
            default -> throw new IllegalArgumentException();
        };
        val deviation = interval.deviation();
        if (deviation < 0) {
            return IntStream
                    .range(0, -deviation)
                    .boxed()
                    .reduce(
                            defaultNote,
                            (it, i) -> it.sharpen(),
                            NoParallelStream.get()
                    );
        }
        if (deviation > 0) {
            return IntStream
                    .range(0, deviation)
                    .boxed()
                    .reduce(
                            defaultNote,
                            (it, i) -> it.flatten(),
                            NoParallelStream.get()
                    );
        }
        return defaultNote;
    }

    public String getAbc(Key key) {
        return key.getAbc(this);
    }

    public String getWordAbc() {
        return lyric.orElse("*");
    }

    public AbsoluteNote downOne() {
        return minus(Interval.minor(2)).ignoreAccidental();
    }

    public AbsoluteNote upOne() {
        return plus(Interval.minor(2)).ignoreAccidental();
    }

    public AbsoluteNote skipOne() {
        return this.plus(Interval.minor(3)).ignoreAccidental();
    }

    public AbsoluteNote ignoreAccidental() {
        val newNote = new Note(note.noteName(), Accidental.getNatural());
        return new AbsoluteNote(newNote, octave, lyric);
    }

    public AbsoluteNote withNoteName() {
        return withLyric(note.getDisplayString());
        //get() = withLyric(note.displayString)
    }

    private AbsoluteNote sharpen() {
        return new AbsoluteNote(note.sharpen(), octave, lyric);
    }

    private AbsoluteNote flatten() {
        return new AbsoluteNote(note.flatten(), octave, lyric);
    }

/*
  operator fun compareTo(other: AbsoluteNote): Int {
    return if (this == other) 0
    else if (octave == other.octave) this.note.compareTo(other.note)
    else octave.compareTo(other.octave)
  }

  override fun toString(): String = abc(Key.cMajor)
  */

    private AbsoluteNote upMajorSecond() {
        return new AbsoluteNote(
                note.upMajorSecond(),
                "B".equals(note.noteName()) ? octave.up() : octave,
                lyric
        );
    }

    private AbsoluteNote upMinorSecond() {
        val upMajorSecond = upMajorSecond();
        return new AbsoluteNote(
                upMajorSecond.note.flatten(),
                upMajorSecond.octave,
                lyric
        );
    }

    private AbsoluteNote downMajorSecond() {
        return new AbsoluteNote(
                note.downMajorSecond(),
                "C".equals(note.noteName()) ? octave.down() : octave,
                lyric
        );
    }

    private AbsoluteNote downMinorSecond() {
        val downMajorSecond = downMajorSecond();
        return new AbsoluteNote(downMajorSecond.note.sharpen(), downMajorSecond.octave, lyric);
    }

    public static AbsoluteNote getMiddleC() {
        return new AbsoluteNote(Note.getC(), Octave.getDefault(), Optional.empty());
    }
}
