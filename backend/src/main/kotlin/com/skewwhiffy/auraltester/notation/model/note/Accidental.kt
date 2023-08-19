package com.skewwhiffy.auraltester.notation.model.note;

data class Accidental(val offset: Int) {
    companion object {
        val natural: Accidental
            get() {
                return Accidental(0)
            }

        val flat: Accidental
            get() {
                return Accidental(-1)
            }

        val sharp: Accidental
            get() {
                return Accidental(1)
            }
    }
    /*
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
     */
}
