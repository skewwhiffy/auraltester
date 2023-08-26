package com.skewwhiffy.auraltester.test.util

import com.skewwhiffy.auraltester.controller.ClefType
import com.skewwhiffy.auraltester.helper.oneOf
import com.skewwhiffy.auraltester.notation.model.clef.Clef
import com.skewwhiffy.auraltester.notation.model.interval.Interval
import com.skewwhiffy.auraltester.notation.model.key.Key
import com.skewwhiffy.auraltester.notation.model.note.*
import com.skewwhiffy.auraltester.notation.model.scale.Scale
import com.skewwhiffy.auraltester.notation.model.scale.ScaleDirection
import com.skewwhiffy.auraltester.notation.model.scale.ScaleType
import java.util.*

class TestData {
    companion object {
        val random: RandomTestData by lazy(::RandomTestData)
    }
    /*

    public static NoteFactories noteFactories() {
        return new NoteFactories();
    }
    */

    class RandomTestData {
        private val random: Random by lazy(::Random)

        val string
            get() = UUID.randomUUID().toString()

        val absoluteNote
            get() = AbsoluteNote(note, octave, null)

        private val accidental
            get() = Accidental(random.nextInt(3) - 1)

        val clef
            get() = Clef(
                clefType,
                string.uppercase(),
                absoluteNote.ignoreAccidental,
                absoluteNote.ignoreAccidental
            )

        private val clefType
            get() = oneOf(ClefType.entries)

        val directedInterval
            get() = if (random.nextBoolean()) interval.up else interval.down

        val interval
            get() = Interval(oneOf((1..8)), random.nextInt(3) - 1)

        val intervalNotes
            get() = IntervalNotes(absoluteNote, interval)

        val key
            get() = if (random.nextBoolean()) Key.major(note) else Key.minor(note)

        @Suppress("SpellCheckingInspection")
        val note
            get() = Note(oneOf("ABCDEFG"), accidental)

        private val octave
            get() = Octave(random.nextInt(8) - 4)

        val scale
            get() = Scale(absoluteNote, scaleType, scaleDirection)

        private val scaleDirection
            get() = oneOf(ScaleDirection.entries.toList())

        val scaleType
            get() = ScaleType(
                string,
                string,
                (1..5).map { directedInterval },
                (1..5).map { directedInterval }
            )
    }

    /*
public static class NoteFactories {
public NoteFactory note() {
    return new NoteFactory();
}

public ClefFactory clef() {
    return new ClefFactory(note());
}

public IntervalFactory interval() {
    return new IntervalFactory();
}

public KeyFactory key() {
    return new KeyFactory(note());
}

public InternalNotationFactory internalNotation() {
    return new InternalNotationFactory(
            clef(),
    note(),
    interval(),
    key()
    );
}

public ScaleTypeFactory scaleType() {
    return new ScaleTypeFactory(interval());
}
}
*/
}
