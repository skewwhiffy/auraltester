package com.skewwhiffy.auraltester.notation.factory

import com.skewwhiffy.auraltester.notation.model.key.Key
import org.springframework.stereotype.Service

import java.util.Locale

@Service
class KeyFactory(private val noteFactory: NoteFactory) {
    fun getKey(rawKey: String): Key {
        val rawNote = getRawNote(rawKey)
        val note = noteFactory.getNote(rawNote)
        return if (isMinor(rawKey)) Key.minor(note) else Key.major(note)
    }

    private fun getRawNote(rawKey: String): String {
        if (rawKey.isEmpty()) {
            throw IllegalArgumentException("Empty key")
        }
        val rawNoteName = rawKey.substring(0, 1).uppercase(Locale.UK)

        val rawAccidental = getRawAccidental(rawKey.substring(1), "")
        return rawNoteName + rawAccidental
    }

    private fun getRawAccidental(rawKeyRemaining: String, noteSoFar: String): String {
        if (rawKeyRemaining.isEmpty()) {
            return noteSoFar
        }
        val firstCharacter = rawKeyRemaining.substring(0, 1)
        if ("#bx".contains(firstCharacter)) {
            return getRawAccidental(rawKeyRemaining.substring(1), firstCharacter + noteSoFar)
        }
        return noteSoFar
    }

    private fun isMinor(rawKey: String) = listOf("m", "minor", "min").any(rawKey.trim()::endsWith)
}
