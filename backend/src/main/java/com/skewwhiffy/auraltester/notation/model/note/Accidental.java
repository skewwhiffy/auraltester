package com.skewwhiffy.auraltester.notation.model.note;

import com.skewwhiffy.auraltester.dao.AccidentalDao;
import org.apache.logging.log4j.util.Strings;
import org.hibernate.internal.util.StringHelper;

public record Accidental(int offset) {
    public AccidentalDao toDao() {
        return new AccidentalDao(offset);
    }

    public String getAbc() {
        if (offset == 0) {
            return "";
        }
        if (offset < 0) {
            return Strings.repeat("_", -offset);
        }
        return Strings.repeat("^", offset);
    }

    public String getDisplayString() {
        if (offset < 0) {
            return StringHelper.repeat("b", -offset);
        }
        if (offset % 2 == 0) {
            return StringHelper.repeat("x", offset / 2);
        }
        return StringHelper.repeat("x", (offset - 1) / 2) + "#";
    }

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
