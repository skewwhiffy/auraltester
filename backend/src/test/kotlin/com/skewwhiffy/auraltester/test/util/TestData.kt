package com.skewwhiffy.auraltester.test.util

import com.skewwhiffy.auraltester.controller.ClefType
import com.skewwhiffy.auraltester.notation.model.clef.Clef
import com.skewwhiffy.auraltester.notation.model.interval.Interval
import com.skewwhiffy.auraltester.notation.model.note.*
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

        @Suppress("SameParameterValue")
        private fun oneOf(source: String) = oneOf(source.toList()).toString()

        fun oneOf(source: IntRange) = oneOf(source.toList())

        private fun <T> oneOf(source: List<T>) = source.size.let(random::nextInt).let(source::get)

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

        /*
        public DirectedInterval directedInterval() {
            return random.nextBoolean() ? interval().up() : interval().down();
        }
         */

        val interval
            get() = Interval(oneOf((1..8)), random.nextInt(3) - 1)

        val intervalNotes
            get() = IntervalNotes(absoluteNote, interval)

        /*
        public Key key() {
            return random.nextBoolean() ? Key.major(note()) : Key.minor(note());
        }
        */

        @Suppress("SpellCheckingInspection")
        val note
            get() = Note(oneOf("ABCDEFG"), accidental)

        private val octave
            get() = Octave(random.nextInt(8) - 4)

        /*
        public Scale scale() {
            return new Scale(absoluteNote(), scaleType(), scaleDirection());
        }

        public ScaleDirection scaleDirection() {
            return oneOf(Arrays.stream(ScaleDirection.values()).toList());
        }

        public ScaleType scaleType() {
            return new ScaleType(
                    string(),
            string(),
            IntStream.range(1, 6).mapToObj(it -> directedInterval()).toList(),
            IntStream.range(1, 6).mapToObj(it -> directedInterval()).toList()
            );
        }
        */
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
