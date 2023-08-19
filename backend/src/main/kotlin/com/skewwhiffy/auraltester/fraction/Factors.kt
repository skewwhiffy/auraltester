package com.skewwhiffy.auraltester.fraction

class Factors {
    companion object {
        fun lcm(first: Int, second: Int): Int {
            if (first < 0) {
                return lcm(-first, second)
            }
            return if (second < 0) {
                lcm(first, -second)
            } else first * second / hcf(first, second)
        }

        fun hcf(first: Int, second: Int): Int {
            if (first < 0) {
                return hcf(-first, second)
            }
            if (second < 0) {
                return hcf(first, -second)
            }
            if (first == 0) {
                return second
            }
            if (second == 0) {
                return first
            }
            if (first == second) {
                return first
            }
            return if (first > second) {
                hcf(first - second, second)
            } else hcf(first, second - first)
        }
    }
}