package com.skewwhiffy.auraltester.notation.model.abc

import com.skewwhiffy.auraltester.notation.model.clef.Clef
import com.skewwhiffy.auraltester.notation.model.key.Key
import com.skewwhiffy.auraltester.notation.model.note.AbsoluteNote
import com.skewwhiffy.auraltester.notation.model.note.NoteLength


data class SingleLineAbc(
    val displayName: String?,
    val clef: Clef,
    val noteLength: NoteLength,
    val keySignature: Key?,
    val notes: List<List<AbsoluteNote>>
) : AbcProvider {
    /*
    public SingleLineAbc(
            String displayName,
    Clef clef,
    NoteLength noteLength,
    List<List<AbsoluteNote>> notes
    ) {
        this(Optional.of(displayName), clef, noteLength, Optional.empty(), notes);
    }

    public SingleLineAbc(
            String displayName,
    Clef clef,
    NoteLength noteLength,
    Key key,
    List<List<AbsoluteNote>> notes
    ) {
        this(Optional.of(displayName), clef, noteLength, Optional.of(key), notes);
    }

    public SingleLineAbc(
            Clef clef,
    NoteLength noteLength,
    List<List<AbsoluteNote>> notes
    ) {
        this(Optional.empty(), clef, noteLength, Optional.empty(), notes);
    }
  fun includeKeySignature(key: Key): SingleLineAbc = SingleLineAbc(
    displayName,
    clef,
    noteLength,
    key,
    notes
  )

*/
    override val abc: String
        get() {
            val barAbc: (List<AbsoluteNote>) -> String = { barNotes ->
                barNotes.joinToString { it.getAbc(keySignature ?: Key.cMajor) }
            }
            val notesAbc = notes.joinToString("|", transform = barAbc)
                .let { "$it|" }
            val words = notes .flatten().joinToString(" ") { it.wordAbc }
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
        }
}
