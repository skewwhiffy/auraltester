package com.skewwhiffy.auraltester.notation.model.note;

public record Octave(int offsetFromDefault) {
    public Octave up() {
        return new Octave(offsetFromDefault + 1);
    }

    public Octave down() {
        return new Octave(offsetFromDefault - 1);
    }
        /*
  operator fun compareTo(other: Octave): Int =
    offsetFromDefault.compareTo(other.offsetFromDefault)
}
     */
    public static Octave getDefault() {
        return new Octave(0);
    }
}
