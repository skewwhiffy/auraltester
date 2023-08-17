package com.skewwhiffy.auraltester.fraction;

public class Factors {
    public static int lcm(int first, int second) {
        if (first < 0) {
            return lcm(-first, second);
        }
        if (second < 0) {
            return lcm(first, -second);
        }
        return first * second / hcf(first, second);
    }

    public static int hcf(int first, int second) {
        if (first < 0) {
            return hcf(-first, second);
        }
        if (second < 0) {
            return hcf(first, -second);
        }
        if (first == 0) {
            return second;
        }
        if (second == 0) {
            return first;
        }
        if (first == second) {
            return first;
        }
        if (first > second) {
            return hcf(first - second, second);
        }
        return hcf(first, second - first);
    }

}
