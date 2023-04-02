package com.skewwhiffy.auraltester.helper;

import java.util.Arrays;
import java.util.Locale;
import java.util.stream.Collectors;

public class StringHelper {
    private StringHelper() {
    }

    public static String getTitleCase(String... sourceElements) {
        return Arrays.stream(sourceElements)
                .map(it -> it.substring(0, 1).toUpperCase(Locale.UK) + it.substring(1))
                .collect(Collectors.joining(" "));
    }

}
