package com.skewwhiffy.auraltester.helper;

import java.util.function.BinaryOperator;

public class NoParallelStream<U> implements BinaryOperator<U> {
    @Override
    public U apply(U u, U u2) {
        throw new ParallelOperationNotSupportedException();
    }

    static class ParallelOperationNotSupportedException extends RuntimeException {

    }

    public static <T> NoParallelStream<T> get() {
        return new NoParallelStream<>();
    }
}
