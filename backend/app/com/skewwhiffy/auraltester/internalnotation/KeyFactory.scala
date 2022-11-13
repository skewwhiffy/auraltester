package com.skewwhiffy.auraltester.internalnotation

import com.skewwhiffy.auraltester.scales.Key

import javax.inject.{Inject, Singleton}
import scala.annotation.tailrec

@Singleton
class KeyFactory @Inject()(
  noteFactory: NoteFactory
) {
  def getKey(rawKey: String): Key = {
    val rawNote = getRawNote(rawKey)
    val note = noteFactory.getNote(rawNote)
    new Key(note, isMinor(rawKey))
  }

  private def getRawNote(rawKey: String): String = {
    if (rawKey.isEmpty) {
      throw new IllegalArgumentException("Empty key")
    }
    val rawNoteName = rawKey.substring(0, 1).toUpperCase
    @tailrec
    def getRawAccidental(rawKeyRemaining: String, noteSoFar: String): String = {
      if (rawKeyRemaining.isEmpty) {
        return noteSoFar
      }
      rawKeyRemaining.substring(0, 1) match {
        case it if "#bx".contains(it) => getRawAccidental(rawKeyRemaining.substring(1), s"$it$noteSoFar")
        case _ => noteSoFar
      }
    }
    val rawAccidental = getRawAccidental(rawKey.substring(1), "")
    s"$rawNoteName$rawAccidental"
  }

  private def isMinor(rawKey: String): Boolean = {
    val minorSuffixes = List("m", "minor", "min")
    val trimmedRawKey = rawKey.trim()
    minorSuffixes.exists(it => trimmedRawKey.endsWith(it))
  }
}
