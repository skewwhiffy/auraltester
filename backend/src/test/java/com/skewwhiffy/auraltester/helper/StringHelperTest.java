package com.skewwhiffy.auraltester.helper;

import lombok.val;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class StringHelperTest {
    @Test
    void when_getTitleCase_then_eachWordIsCapitalized() {
        val source = "this is a title";
        val expected = "This Is A Title";

        val actual = StringHelper.getTitleCase(source.split(" "));

        assertThat(actual).isEqualTo(expected);
    }
}
