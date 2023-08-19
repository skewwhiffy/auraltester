package com.skewwhiffy.auraltester.test.util

import com.skewwhiffy.auraltester.controller.ClefType
import com.skewwhiffy.auraltester.notation.model.clef.Clef
import com.skewwhiffy.auraltester.notation.model.note.AbsoluteNote
import com.skewwhiffy.auraltester.notation.model.note.Accidental
import com.skewwhiffy.auraltester.notation.model.note.Note
import com.skewwhiffy.auraltester.notation.model.note.Octave
import java.util.*

class TestData {
    companion object {
        val random: RandomTestData
            get() {
                return RandomTestData()
            }
    }
    /*

    public static NoteFactories noteFactories() {
        return new NoteFactories();
    }
    */

    class RandomTestData {
        private val random = Random()

        val string: String
            get() {
                return UUID.randomUUID().toString()
            }

        @Suppress("SameParameterValue")
        private fun oneOf(source: String): String {
            return oneOf(source.toList()).toString()
        }

        fun <T> oneOf(vararg source: T): T {
            return oneOf(source.toList())
        }

        fun <T> oneOf(source: List<T>): T {
            return source.size.let { random.nextInt(it) }.let { source.get(it) }
        }

        val absoluteNote: AbsoluteNote
            get() {
                return AbsoluteNote(note, octave, null)
            }

        val accidental: Accidental
            get() {
                return Accidental(random.nextInt(3) - 1)
            }

        val clef: Clef
            get() {
                return Clef(
                    clefType,
                    string.uppercase(),
                    absoluteNote.ignoreAccidental,
                    absoluteNote.ignoreAccidental
                )
            }

        val clefType: ClefType
            get() {
                return oneOf(ClefType.entries)
            }

        /*
        public DirectedInterval directedInterval() {
            return random.nextBoolean() ? interval().up() : interval().down();
        }

        public Interval interval() {
            return new Interval(
                    oneOf(IntStream.range(1, 9).boxed().toList()),
            random.nextInt(3) - 1
            );
        }

        public IntervalNotes intervalNotes() {
            return new IntervalNotes(absoluteNote(), interval());
        }

        public Key key() {
            return random.nextBoolean() ? Key.major(note()) : Key.minor(note());
        }
        */

        val note: Note
            get() {
                return Note(oneOf("ABCDEFG"), accidental)
            }

        val octave: Octave
            get() {
                return Octave(random.nextInt(8) - 4)
            }

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
