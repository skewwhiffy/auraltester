package com.skewwhiffy.auraltester.notation.model.abc;

import com.skewwhiffy.auraltester.notation.model.clef.Clef;
import com.skewwhiffy.auraltester.notation.model.key.Key;
import com.skewwhiffy.auraltester.notation.model.note.AbsoluteNote;
import com.skewwhiffy.auraltester.notation.model.note.NoteLength;

import java.util.Optional;
import java.util.stream.Stream;

public record SingleLineAbc (
        Optional<String> displayName,
        Clef clef,
        NoteLength noteLength,
        Optional<Key> keySignature,
        Stream<Stream<AbsoluteNote>> notes
        ) implements AbcProvider {

        public SingleLineAbc(
                Optional<String> displayName,
                Clef clef,
                NoteLength noteLength,
                Stream<Stream<AbsoluteNote>> notes
        ) {
                this(
                        displayName,
                        clef,
                        noteLength,
                        Optional.empty(),
                        notes
                );
        }

    /*
  constructor(
    clef: Clef,
    noteLength: NoteLength,
    notes: List<List<AbsoluteNote>>,
  ) : this(null, clef, noteLength, null, notes)

  fun includeKeySignature(key: Key): SingleLineAbc = SingleLineAbc(
    displayName,
    clef,
    noteLength,
    key,
    notes
  )
  */

        @Override
        public String getAbc() {
                return "POOBUM SingleLineAbc";
                /*
                      fun barAbc(value: List<AbsoluteNote>): String {
        return value.joinToString("") { it.abc(keySignature ?: Key.cMajor) }
      }

      val notesAbc = notes.joinToString("|") { barAbc(it) } + "|"
      val words = notes.flatten().joinToString(" ") { it.wordAbc }

      return listOf(
        "X:1",
        displayName?.let { "T:$it" } ?: "",
        "K:clef=${clef.abc}",
        keySignature?.let { "K:${it.abc}" } ?: "",
        "L:${noteLength.abc}",
        notesAbc,
        "w:$words"
      )
        .filter { it.isNotEmpty() }
        .joinToString(System.lineSeparator())
                 */

        }
}
