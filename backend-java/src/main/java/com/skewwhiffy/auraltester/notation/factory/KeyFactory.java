package com.skewwhiffy.auraltester.notation.factory;

import com.skewwhiffy.auraltester.notation.model.key.Key;
import lombok.AllArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;

@Service
@AllArgsConstructor
public class KeyFactory {
    private final NoteFactory noteFactory;

    public Key getKey(String rawKey) {
        val rawNote = getRawNote(rawKey);
        val note = noteFactory.getNote(rawNote);
        return isMinor(rawKey) ? Key.minor(note) : Key.major(note);
    }

    private String getRawNote(String rawKey) {
        if (rawKey.isEmpty()) {
            throw new IllegalArgumentException("Empty key");
        }
        val rawNoteName = rawKey.substring(0, 1).toUpperCase(Locale.UK);

        val rawAccidental = getRawAccidental(rawKey.substring(1), "");
        return rawNoteName + rawAccidental;
    }

    private String getRawAccidental(String rawKeyRemaining, String noteSoFar) {
        if (rawKeyRemaining.isEmpty()) {
            return noteSoFar;
        }
        val firstCharacter = rawKeyRemaining.substring(0, 1);
        if ("#bx".contains(firstCharacter)) {
            return getRawAccidental(rawKeyRemaining.substring(1), firstCharacter + noteSoFar);
        }
        return noteSoFar;
    }

  private boolean isMinor(String rawKey) {
    val minorSuffixes = List.of("m", "minor", "min");
    val trimmedRawKey = rawKey.trim();
    return minorSuffixes.stream().anyMatch(trimmedRawKey::endsWith);
  }
}
