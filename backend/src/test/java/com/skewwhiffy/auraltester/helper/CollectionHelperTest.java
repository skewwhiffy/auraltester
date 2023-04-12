package com.skewwhiffy.auraltester.helper;

import lombok.val;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;

public class CollectionHelperTest {
    @Test
    void when_getOneOf_then_selectsEachOneWithRoughlyEqualProbability() {
        val source = IntStream.range(0, 10).boxed().toList();
        val count = new HashMap<Integer, Integer>();

        for (int i = 0; i < 1000; i++) {
            val actual = CollectionHelper.oneOf(source);
            val newCount = count.containsKey(actual) ? count.get(actual) + 1 : 1;
            count.put(actual, newCount);
        }

        assertThat(count.keySet()).containsExactlyElementsOf(source);
    }
}
