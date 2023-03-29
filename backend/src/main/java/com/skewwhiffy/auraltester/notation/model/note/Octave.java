package com.skewwhiffy.auraltester.notation.model.note;

import lombok.Getter;

public record Octave(int offsetFromDefault) {
    /*
data class Octave(val offsetFromDefault: Int) {

  val up: Octave get() = Octave(offsetFromDefault + 1)

  val down: Octave get() = Octave(offsetFromDefault - 1)

  operator fun compareTo(other: Octave): Int =
    offsetFromDefault.compareTo(other.offsetFromDefault)
}
     */
    public static Octave getDefault() {
        return new Octave(0);
    }
}
