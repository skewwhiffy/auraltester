package com.skewwhiffy.auraltester.notation.model.key;

import com.skewwhiffy.auraltester.helper.NoParallelStream;
import com.skewwhiffy.auraltester.notation.model.note.AbsoluteNote;
import com.skewwhiffy.auraltester.notation.model.note.Accidental;
import com.skewwhiffy.auraltester.notation.model.note.Note;
import lombok.val;
import org.apache.logging.log4j.util.Strings;

import java.text.MessageFormat;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.stream.Stream;

public record Key(Note note, boolean isMinor) {
    public Key(Note note) {
        this(note, false);
    }


    /*
  val abc: String = if (isMinor) "${note.displayString}m" else note.displayString

  val displayString: String = "${note.displayString} ${if (isMinor) "minor" else "major"}"

*/

    public String getAbc(AbsoluteNote note) {
        return getAccidentalAbc(note.note()) + getNoteAbc(note);
    }

    /*

    val canRenderSignature: Boolean = if (isMinor) relativeMajor.canRenderSignature
    else renderableKeys.contains(note.displayString)
    */

    private String getNoteAbc(AbsoluteNote note) {
        val offset = note.octave().offsetFromDefault();
        if (offset > 0) {
            return note.note().noteName().toLowerCase(Locale.UK) + Strings.repeat("'", offset - 1);
        }
        if (offset < 0) {
            return note.note().noteName() + Strings.repeat(",", -offset);
        }
        return note.note().noteName();
    }

    private String getAccidentalAbc(Note note) {
        val noteWithNoteName = getNotes()
                .stream()
                .filter(it -> it.noteName().equals(note.noteName()))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(
                        MessageFormat.format("No note {0} in scale", note.noteName())
                ));
        if (note.accidental().equals(noteWithNoteName.accidental())) {
            return "";
        }
        if (note.accidental().equals(Accidental.getNatural())) {
            return "=";
        }
        return note.accidental().getAbc();
    }
    /*

    val relativeMinor:

    Key
    get() =if(isMinor)this else

    Key(
            note.downMajorSecond.downMajorSecond.sharp,
            isMinor =true
    )

    val relativeMajor:

    Key get() =if(isMinor)

    Key(note.upMajorSecond.upMajorSecond.flat) else this

    val relative:

    Key get() =if(isMinor)relativeMajor else relativeMinor
    */

    public List<Note> getNotes() {
        val semitones = isMinor ? Stream.of(2, 1, 2, 2, 1, 2) : Stream.of(2, 2, 1, 2, 2, 2);
        return semitones
                .reduce(
                        Collections.singletonList(note),
                        (soFar, nextInterval) -> {
                            val lastNote = soFar.get(soFar.size() - 1);
                            val nextNote = nextInterval == 2
                                    ? lastNote.upMajorSecond()
                                    : lastNote.upMajorSecond().flatten();
                            return Stream.concat(soFar.stream(), Stream.of(nextNote)).toList();
                        },
                        NoParallelStream.get()
                );
    }

    /*
}
    companion object {
@Suppress("SpellCheckingInspection")
private val renderableKeys:List<String> ="C G D A E B F# C# F Bb Eb Ab Db Gb Cb"
        .split(' ')
        .toList()

        val cMajor:Key=Key(Note.c)
        }
        */
    public static Key getCMajor() {
        return new Key(Note.getC());
    }
}
