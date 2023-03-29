package com.skewwhiffy.auraltester.service;

import com.skewwhiffy.auraltester.notation.model.abc.SingleLineAbc;
import com.skewwhiffy.auraltester.notation.model.note.NoteLength;
import com.skewwhiffy.auraltester.notation.model.note.NoteSequence;
import lombok.val;
import org.springframework.stereotype.Service;
import com.skewwhiffy.auraltester.notation.model.clef.Clef;
import com.skewwhiffy.auraltester.notation.model.abc.AbcProvider;

import java.text.MessageFormat;
import java.util.Optional;

@Service
public class AbcService {
    /*
  fun getAbc(clef: Clef, scale: Scale, key: Key? = null): AbcProvider = listOfNotNull(
    scale.displayName,
    scale.direction.displayString,
    key?.let { "(with key signature)" }
  )
    .joinToString(" ")
    .titleCase
    .let { SingleLineAbc(it, clef, NoteLength.semibreve, key, listOf(scale.notes))}

*/
    public AbcProvider getAbc(Clef clef) {
        val displayName = Optional.of(clef)
                .map(Clef::getDisplayName)
                .map(it -> MessageFormat.format("{0} Clef Notes", it));
        return new SingleLineAbc(
                displayName,
                clef,
                NoteLength.getSemibreve(),
                clef.getNotes().stream().map(NoteSequence::getNotes).toList()
        );
                /*
        fun getAbc(clef: Clef): AbcProvider = SingleLineAbc(
                "${clef.displayName} Clef Notes".titleCase,
                clef,
                NoteLength.semibreve,
                clef.notes.map { it.notes }
  )
    */
    }
    /*

  fun getAbc(clef: Clef, intervalNotes: IntervalNotes, key: Key): AbcProvider = SingleLineAbc(
    intervalNotes.interval.displayString.titleCase,
    clef,
    NoteLength.semibreve,
    key,
    listOf(intervalNotes.notes)
  )

  fun getAbc(clef: Clef, key: Key): AbcProvider = SingleLineAbc(
    "${key.displayString} / ${key.relative.displayString}".titleCase,
    clef,
    NoteLength.semibreve,
    key,
    listOf(listOf())
  )
}
     */
}
