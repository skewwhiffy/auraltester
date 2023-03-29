package com.skewwhiffy.auraltester.helper;

import java.util.function.BinaryOperator;

public class StreamHelper {
    public static <U> BinaryOperator<U> getNoParallel() {
        return (first, second) -> {
            throw new ParallelOperationNotSupportedException();
        };
    }

    static class ParallelOperationNotSupportedException extends RuntimeException {

    }
}
