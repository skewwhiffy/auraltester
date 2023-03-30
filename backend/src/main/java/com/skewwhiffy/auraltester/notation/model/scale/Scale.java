package com.skewwhiffy.auraltester.notation.model.scale;

import com.skewwhiffy.auraltester.notation.model.note.AbsoluteNote;
import com.skewwhiffy.auraltester.notation.model.note.NoteSequence;
import lombok.val;

import java.text.MessageFormat;
import java.util.Comparator;
import java.util.List;

public record Scale(AbsoluteNote lowestNote, ScaleType scaleType, ScaleDirection direction)
        implements NoteSequence {
    public String getDisplayName() {
        return MessageFormat.format(
                "{0} {1}",
                lowestNote.note().getDisplayString(),
                scaleType.displayName()
        );
    }

    @Override
    public List<AbsoluteNote> getNotes() {
        val notes = scaleType
                .getIntervals(direction)
                .stream()
                .map(lowestNote::apply);
        return (switch (direction) {
            case ASCENDING -> notes;
            case DESCENDING -> notes.sorted(Comparator.reverseOrder());
        }).toList();
    }
      /*
  fun abc(key: Key): String = notes.joinToString("") { it.abc(key) }
     */
}
