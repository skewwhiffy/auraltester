import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

class FactorsTest {
    @ParameterizedTest
    @CsvSource(
        "11, 12, 132, 1",
        "4, 6, 12, 2",
        "-4, 6, 12, 2",
        "4, -6, 12, 2",
        "-4, -6, 12, 2"
    )
    fun `when negative input then hcf and lcm work`(first: Int, second: Int, lcm: Int, hcf: Int) {
        val actualLcm = Factors.lcm(first, second)
        val actualHcf = Factors.hcf(first, second)

        assertThat(actualLcm).isEqualTo(lcm)
        assertThat(actualHcf).isEqualTo(hcf)
    }
}