package com.skewwhiffy.auraltester.helper;

import lombok.val;
import org.junit.jupiter.api.Test;

import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.Assertions.fail;

class NoParallelStreamTest {
    @Test
    void when_parallel_then_throws() {
        assertThatThrownBy(() -> {
            val result = IntStream
                    .range(0, 100)
                    .boxed()
                    .parallel()
                    .reduce(0, Integer::sum, new NoParallelStream<>());
            fail(result.toString());
        }).isInstanceOf(NoParallelStream.ParallelOperationNotSupportedException.class);
    }
}