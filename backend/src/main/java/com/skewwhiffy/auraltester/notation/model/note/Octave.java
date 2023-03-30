package com.skewwhiffy.auraltester.notation.model.note;

import org.jetbrains.annotations.NotNull;

public record Octave(int offsetFromDefault) implements Comparable<Octave> {
    public Octave up() {
        return new Octave(offsetFromDefault + 1);
    }

    public Octave down() {
        return new Octave(offsetFromDefault - 1);
    }

    @Override
    public int compareTo(@NotNull Octave o) {
        return offsetFromDefault - o.offsetFromDefault;
    }

    public static Octave getDefault() {
        return new Octave(0);
    }
}
