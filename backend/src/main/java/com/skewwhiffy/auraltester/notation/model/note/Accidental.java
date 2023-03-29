package com.skewwhiffy.auraltester.notation.model.note;

import org.apache.logging.log4j.util.Strings;

public record Accidental(int offset) {
    public String getAbc() {
        if (offset == 0) {
            return "";
        }
        if (offset < 0) {
            return Strings.repeat("_", -offset);
        }
        return Strings.repeat("^", offset);
    }

    /*
  val displayString: String = when {
    offset < 0 -> "b".repeat(-offset)
    offset % 2 == 0 -> "x".repeat(offset / 2)
    else -> "x".repeat((offset - 1) / 2) + "#"
  }
  */

    public Accidental flatten() {
        return new Accidental(offset - 1);
    }

    public Accidental sharpen() {
        return new Accidental(offset + 1);
    }

    public static Accidental getNatural() {
        return new Accidental(0);
    }

    public static Accidental getFlat() {
        return new Accidental(-1);
    }

    public static Accidental getSharp() {
        return new Accidental(1);
    }
}
