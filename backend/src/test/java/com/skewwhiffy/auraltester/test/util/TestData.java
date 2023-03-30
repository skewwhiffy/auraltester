package com.skewwhiffy.auraltester.test.util;

import com.skewwhiffy.auraltester.notation.model.clef.Clef;
import com.skewwhiffy.auraltester.notation.model.interval.Interval;
import com.skewwhiffy.auraltester.notation.model.note.*;

import java.text.MessageFormat;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.UUID;
import java.util.stream.IntStream;


public class TestData {
    /*
object TestData {
*/
    public static RandomTestData random() {
        return new RandomTestData();
    }
    /*
  val noteFactories = NoteFactories
}

*/

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

        /*
      fun <T> oneOf(vararg source: T): T = oneOf(source.toList())
    */


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
            /*
  val directedInterval: DirectedInterval
    get() = if (random.nextBoolean()) interval.up
    else interval.down
  */

        public Interval interval() {
            return new Interval(
                    oneOf(IntStream.range(1, 9).boxed().toList()),
                    random.nextInt(3) - 1
            );
        }

        public IntervalNotes intervalNotes() {
            return new IntervalNotes(absoluteNote(), interval());
        }
            /*

  val key: Key get() = Key(note, isMinor = random.nextBoolean())

  */

        public Note note() {
            return new Note(oneOf("ABCDEFG"), accidental());
        }

        private Octave octave() {
            return new Octave(random.nextInt(8) - 4);
        }

        /*
  val scale get(): Scale = Scale(absoluteNote, scaleType, scaleDirection)

  val scaleDirection get(): ScaleDirection = oneOf(*ScaleDirection.values())

  val scaleType: ScaleType
    get() = ScaleType(
      string,
      string,
      (1..7).map { directedInterval }.toList(),
      (1..7).map { directedInterval }.toList()
    )
     */
    }
/*

object NoteFactories {
  val note: NoteFactory = NoteFactory()
  val clef: ClefFactory = ClefFactory(note)
  val interval: IntervalFactory = IntervalFactory()

  val key: KeyFactory = KeyFactory(note)

  val internalNotation: InternalNotationFactory = InternalNotationFactory(
    clef,
    interval,
    key,
    note
  )
  val scaleTypeFactory: ScaleTypeFactory = ScaleTypeFactory(interval)
}

     */
}
