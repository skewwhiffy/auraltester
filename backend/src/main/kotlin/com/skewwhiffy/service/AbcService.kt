package com.skewwhiffy.service

import com.skewwhiffy.notation.model.abc.AbcProvider
import com.skewwhiffy.notation.model.clef.Clef
import com.skewwhiffy.notation.model.key.Key
import com.skewwhiffy.notation.model.note.NoteLength
import com.skewwhiffy.notation.model.abc.SingleLineAbc
import com.skewwhiffy.notation.model.note.IntervalNotes
import com.skewwhiffy.notation.model.scale.Scale
import org.springframework.stereotype.Service

@Service
class AbcService {
  fun getAbc(clef: Clef, scale: Scale, key: Key? = null): AbcProvider = listOfNotNull(
    scale.displayName,
    scale.direction.displayString,
    key?.let { "(with key signature)" }
  )
    .joinToString(" ")
    .titleCase
    .let { SingleLineAbc(it, clef, NoteLength.semibreve, key, listOf(scale.notes))}

  fun getAbc(clef: Clef): AbcProvider = SingleLineAbc(
    "${clef.displayName} Clef Notes".titleCase,
    clef,
    NoteLength.semibreve,
    clef.notes.map { it.notes }
  )

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

private val String.titleCase
  get() = split(" ").joinToString(" ") { it.substring(0, 1).uppercase() + it.substring(1) }
