package com.skewwhiffy.notation.factory

import com.skewwhiffy.notation.model.key.Key
import org.springframework.stereotype.Service

@Service
class KeyFactory(private val noteFactory: NoteFactory) {
  fun getKey(rawKey: String): Key {
    val rawNote = getRawNote(rawKey)
    val note = noteFactory.getNote(rawNote)
    return Key(note, isMinor(rawKey))
  }

  private fun getRawNote(rawKey: String): String {
    if (rawKey.isEmpty()) {
      throw IllegalArgumentException("Empty key")
    }
    val rawNoteName = rawKey.substring(0, 1).uppercase()
    fun getRawAccidental(rawKeyRemaining: String, noteSoFar: String): String {
      return if (rawKeyRemaining.isEmpty()) noteSoFar
      else rawKeyRemaining.substring(0, 1).let {
        when {
          "#bx".contains(it) -> getRawAccidental(rawKeyRemaining.substring(1), "$it$noteSoFar")
          else -> noteSoFar
        }
      }
    }

    val rawAccidental = getRawAccidental(rawKey.substring(1), "")
    return "$rawNoteName$rawAccidental"
  }

  private fun isMinor(rawKey: String): Boolean {
    val minorSuffixes = listOf("m", "minor", "min")
    val trimmedRawKey = rawKey.trim()
    return minorSuffixes.any { trimmedRawKey.endsWith(it) }
  }
}