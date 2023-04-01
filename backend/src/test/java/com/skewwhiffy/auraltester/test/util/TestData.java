package com.skewwhiffy.auraltester.test.util;

import com.skewwhiffy.auraltester.notation.factory.*;
import com.skewwhiffy.auraltester.notation.model.clef.Clef;
import com.skewwhiffy.auraltester.notation.model.interval.DirectedInterval;
import com.skewwhiffy.auraltester.notation.model.interval.Interval;
import com.skewwhiffy.auraltester.notation.model.key.Key;
import com.skewwhiffy.auraltester.notation.model.note.*;
import com.skewwhiffy.auraltester.notation.model.scale.Scale;
import com.skewwhiffy.auraltester.notation.model.scale.ScaleDirection;
import com.skewwhiffy.auraltester.notation.model.scale.ScaleType;

import java.text.MessageFormat;
import java.util.*;
import java.util.stream.IntStream;

public class TestData {
    public static RandomTestData random() {
        return new RandomTestData();
    }

    public static NoteFactories noteFactories() {
        return new NoteFactories();
    }

    public static class RandomTestData {
        private final Random random = new Random();

        public String string() {
            return MessageFormat.format("{0}", UUID.randomUUID());
        }

        private String oneOf(String source) {
            List<String> charList = source
                    .chars()
                    .mapToObj(it -> (char) it)
                    .map(Object::toString)
                    .toList();
            return oneOf(charList);
        }

        public <T> T oneOf(T... source) {
            return oneOf(Arrays.stream(source).toList());
        }

        public <T> T oneOf(List<T> source) {
            return source.get(random.nextInt(source.size()));
        }

        public AbsoluteNote absoluteNote() {
            return new AbsoluteNote(note(), octave(), Optional.empty());
        }

        private Accidental accidental() {
            return new Accidental(random.nextInt(3) - 1);
        }

        public Clef clef() {
            return new Clef(string(), absoluteNote(), absoluteNote());
        }

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

        public Note note() {
            return new Note(oneOf("ABCDEFG"), accidental());
        }

        private Octave octave() {
            return new Octave(random.nextInt(8) - 4);
        }

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
    }

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

}