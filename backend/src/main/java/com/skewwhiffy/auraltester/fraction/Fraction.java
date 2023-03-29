package com.skewwhiffy.auraltester.fraction;

import lombok.val;

import java.text.MessageFormat;

public record Fraction(int top, int bottom) {
    public Fraction {
        if (bottom == 0) {
            throw new ArithmeticException("/ by zero");
        }
    }

    public Fraction(int whole, int top, int bottom) {
        this(whole * bottom + top, bottom);
    }


    private Fraction getSimplified() {
        if (bottom < 0) {
            return new Fraction(-top, -bottom).getSimplified();
        }
        if (top == 0) {
            return new Fraction(0, 1);
        }
        val hcf = Factors.hcf(top, bottom);
        if (hcf == 1) {
            return this;
        }
        return new Fraction(top / hcf, bottom / hcf);
    }

    private int getWhole() {
        return top / bottom;
    }

    private int getTopWithoutWhole() {
        return top - (getWhole() * bottom);
    }

    public Fraction plus(Fraction other) {
        val lcd = Factors.lcm(bottom, other.bottom);
        val newTop = top * (lcd / bottom) + other.top * (lcd / other.bottom);
        return new Fraction(newTop, lcd).getSimplified();
    }

    public Fraction minus(Fraction other) {
        return plus(other.getNegative());
    }

    public Fraction getNegative() {
        return new Fraction(-top, bottom).getSimplified();
    }

    public Fraction times(Fraction other) {
        return new Fraction(top * other.top, bottom * other.bottom).getSimplified();
    }

    public String getTopHeavyString() {
        return bottom == 1 ? String.valueOf(top) : MessageFormat.format("{0}/{1}", top, bottom);
    }

    @Override
    public String toString() {
        val whole = getWhole();
        val wholePart = whole == 0 ? "" : whole + " ";
        return MessageFormat.format("{0}{1}/{2}", wholePart, getTopWithoutWhole(), bottom);
    }

    @Override
    public boolean equals(Object other) {
        if (other == null) {
            return false;
        }
        if (other instanceof Fraction) {
            Fraction simplified = getSimplified();
            Fraction otherSimplified = ((Fraction) other).getSimplified();
            return simplified.top == otherSimplified.top && simplified.bottom == otherSimplified.bottom;
        }
        return other.equals(2);
    }
}