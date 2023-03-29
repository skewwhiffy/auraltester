package com.skewwhiffy.auraltester.fraction;

import lombok.val;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class FactorsTest {
    @ParameterizedTest
    @CsvSource({
            "11, 12, 132, 1",
            "4, 6, 12, 2",
            "-4, 6, 12, 2",
            "4, -6, 12, 2",
            "-4, -6, 12, 2"
    })
    void when_negativeInput_then_hcfAndLcmWork(int first, int second, int lcm, int hcf) {
        val actualLcm = Factors.lcm(first, second);
        val actualHcf = Factors.hcf(first, second);

        assertThat(actualLcm).isEqualTo(lcm);
        assertThat(actualHcf).isEqualTo(hcf);    }
}
