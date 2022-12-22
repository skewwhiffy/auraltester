package com.skewwhiffy.notation.factory

import com.skewwhiffy.notation.model.AbsoluteNote
import com.skewwhiffy.notation.model.Clef
import org.springframework.stereotype.Service
import java.util.*

@Service
class InternalNotationFactory(
  private val clefFactory: ClefFactory,
  /*
  val intervalFactory: IntervalFactory,
  val keyFactory: KeyFactory,
   */
  val noteFactory: NoteFactory
) {
  fun clef(clefRaw: String): Clef {
    return when (clefRaw.lowercase(Locale.UK)) {
      "treble" -> clefFactory.treble
      "alto" -> clefFactory.alto
      "tenor" -> clefFactory.tenor
      "bass" -> clefFactory.bass
      else -> throw IllegalArgumentException("Unrecognized clef type: '$clefRaw'")
    }
  }

  fun getNote(noteRaw: String): AbsoluteNote {
    return noteFactory.getAbsoluteNote(noteRaw)
  }

  /*
  def getNotes(notesRaw: String): List[AbsoluteNote] = notesRaw
    .split(' ')
    .map(getNote)
    .toList

  def getDirectedInterval(rawInterval: String): DirectedInterval = intervalFactory
    .getDirectedInterval(rawInterval)

  def getDirectedIntervals(rawIntervals: String): List[DirectedInterval] = intervalFactory
    .getDirectedIntervals(rawIntervals)

  def getKey(rawKey: String): Key = keyFactory.getKey(rawKey)
}

   */
}