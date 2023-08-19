package com.skewwhiffy.auraltester.notation.model.note

data class AbsoluteNote(val note: Note, val octave: Octave, val lyric: String?) : Comparable<AbsoluteNote> {
    companion object {
        val middleC: AbsoluteNote
            get() {
                return AbsoluteNote(Note.c, Octave.default, null)
            }
    }
    /*
    public AbsoluteNoteDao toDao {
        return new AbsoluteNoteDao(note.toDao(), octave.toDao(), lyric.orElse(null))
    }

    public AbsoluteNote apply(DirectedInterval interval) {
        return switch (interval.direction()) {
            case UP -> plus(interval.interval())
            case DOWN -> minus(interval.interval())
        }
    }

    public AbsoluteNote withLyric(String lyric) {
        return new AbsoluteNote(note, octave, Optional.of(lyric))
    }

    public AbsoluteNote plus(Interval interval) {
        val defaultNote = switch (interval.degree()) {
            case 1 -> this
            case 2 -> upMajorSecond()
            case 3 -> upMajorSecond().upMajorSecond()
            case 4 -> plus(Interval.major(3)).upMinorSecond()
            case 5 -> plus(Interval.perfect(4)).upMajorSecond()
            case 6 -> plus(Interval.perfect(5)).upMajorSecond()
            case 7 -> plus(Interval.major(6)).upMajorSecond()
            case 8 -> new AbsoluteNote(note, octave.up(), lyric)
            default -> throw new IllegalArgumentException(
            MessageFormat.format(
                "Interval degree of ${interval.degree} not supported",
                interval.degree()
            )
            )
        }

        val deviation = interval.deviation()
        if (deviation < 0) {
            return IntStream
                .range(0, -deviation)
                .boxed()
                .reduce(defaultNote, (note, it) -> note.flatten(), NoParallelStream.get())
        }
        if (deviation > 0) {
            return IntStream
                .range(0, deviation)
                .boxed()
                .reduce(defaultNote, (note, it) -> note.sharpen(), NoParallelStream.get())
        }
        return defaultNote
    }

    public AbsoluteNote minus(Interval interval) {
        val defaultNote = switch (interval.degree()) {
            case 1 -> this
            case 2 -> downMajorSecond()
            case 3 -> downMajorSecond().downMajorSecond()
            case 4 -> minus(Interval.major(3)).downMinorSecond()
            case 5 -> minus(Interval.perfect(4)).downMajorSecond()
            case 6 -> minus(Interval.perfect(5)).downMajorSecond()
            case 7 -> minus(Interval.major(6)).downMajorSecond()
            case 8 -> new AbsoluteNote(note, octave.down(), lyric)
            default -> throw new IllegalArgumentException()
        }
        val deviation = interval.deviation()
        if (deviation < 0) {
            return IntStream
                .range(0, -deviation)
                .boxed()
                .reduce(
                    defaultNote,
                    (it, i) -> it.sharpen(),
            NoParallelStream.get()
            )
        }
        if (deviation > 0) {
            return IntStream
                .range(0, deviation)
                .boxed()
                .reduce(
                    defaultNote,
                    (it, i) -> it.flatten(),
            NoParallelStream.get()
            )
        }
        return defaultNote
    }

    public String getAbc(Key key) {
        return key.getAbc(this)
    }

    public String getWordAbc {
        return lyric.orElse("*")
    }

    public AbsoluteNote downOne {
        return minus(Interval.minor(2)).ignoreAccidental()
    }

    public AbsoluteNote upOne {
        return plus(Interval.minor(2)).ignoreAccidental()
    }

    public AbsoluteNote skipOne {
        return this.plus(Interval.minor(3)).ignoreAccidental()
    }
    */

    val ignoreAccidental: AbsoluteNote
        get() {
            return AbsoluteNote(Note(note.noteName, Accidental.natural), octave, lyric)
        }

    /*
    public AbsoluteNote withNoteName {
        return withLyric(note.getDisplayString())
        //get() = withLyric(note.displayString)
    }

    private AbsoluteNote sharpen {
        return new AbsoluteNote(note.sharpen(), octave, lyric)
    }

    private AbsoluteNote flatten {
        return new AbsoluteNote(note.flatten(), octave, lyric)
    }

    private AbsoluteNote upMajorSecond {
        return new AbsoluteNote(
                note.upMajorSecond(),
        "B".equals(note.noteName()) ? octave.up() : octave,
        lyric
        )
    }

    private AbsoluteNote upMinorSecond {
        val upMajorSecond = upMajorSecond()
        return new AbsoluteNote(
                upMajorSecond.note.flatten(),
        upMajorSecond.octave,
        lyric
        )
    }

    private AbsoluteNote downMajorSecond {
        return new AbsoluteNote(
                note.downMajorSecond(),
        "C".equals(note.noteName()) ? octave.down() : octave,
        lyric
        )
    }

    private AbsoluteNote downMinorSecond {
        val downMajorSecond = downMajorSecond()
        return new AbsoluteNote(downMajorSecond.note.sharpen(), downMajorSecond.octave, lyric)
    }

*/

    override fun compareTo(other: AbsoluteNote): Int {
        if (this == other) {
            return 0
        }
        if (octave == other.octave) {
            return note.compareTo(other.note)
        }
        return octave.compareTo(other.octave)
    }

    /*
    @Override
    public String toString {
        return getAbc(Key.getCMajor())
    }

    public static List<AbsoluteNote> range(AbsoluteNote lower, AbsoluteNote upper) {
        val nonNaturalNotes = Stream
            .of(lower, upper)
            .filter(it -> it.note.accidental().offset() != 0)
        .toList()
        if (!nonNaturalNotes.isEmpty()) {
            throw new IllegalArgumentException(
                    MessageFormat.format(
                        "Note(s) '{0}' have accidentals.",
                        nonNaturalNotes.stream().map(it -> it.getAbc(Key.getCMajor())).collect(Collectors.joining(", "))
            )
            )
        }
        return lower.compareTo(upper) > 0
        ? range(upper, lower)
        : lower.equals(upper)
        ? Collections.singletonList(lower)
        : Stream.concat(Stream.of(lower), range(lower.upOne(), upper).stream()).toList()
    }

     */
}
