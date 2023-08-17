package com.skewwhiffy.auraltester.helper;

import java.security.SecureRandom;
import java.util.List;

public class CollectionHelper {
    private final static SecureRandom random = new SecureRandom();
    private CollectionHelper() {
    }

    public static <T> T oneOf(List<T> source) {
        return source.get(random.nextInt(source.size()));
    }
}
