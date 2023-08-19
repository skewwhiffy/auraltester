import org.assertj.core.api.Assertions
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class FractionTest {

    @Test
    fun when_bottomIsZero_then_throws() {
        Assertions.assertThatThrownBy { Fraction(0) / 0 }
            .isInstanceOf(ArithmeticException::class.java)
    }

    @Test
    fun when_topIsZero_then_simplifiesCorrectly() {
        val actual = Fraction(0, 5).simplified

        assertThat(actual.bottom).isEqualTo(1)
    }

    @Test
    fun canInstantiateSimpleFraction() {
        val expected = "1/2"
        val actual = Fraction(1, 2)
        assertThat(actual.toString()).isEqualTo(expected)
    }

    @Test
    fun canInstantiateMixedFraction() {
        val expected = "1 2/3"
        val actual = Fraction(1, 2, 3)
        assertThat(actual.toString()).isEqualTo(expected)
    }

    @Test
    fun canAddFractions() {
        val expected = "11/12"
        val actual = Fraction(3, 4) + Fraction(1, 6)

        assertThat(actual.toString()).isEqualTo(expected)
    }

    @Test
    fun canSubtractFractions() {
        val expected = "1/2"
        val actual = Fraction(3, 4) - (Fraction(1, 4))

        assertThat(actual.toString()).isEqualTo(expected)
    }

    @Test
    fun canMultiplyFractions() {
        val expected = "3/8"
        val actual = Fraction(1, 4) * 3 / 2
        val actualWithFraction = Fraction(1, 4) * Fraction(3, 2)

        assertThat(actual.toString()).isEqualTo(expected)
        assertThat(actualWithFraction.toString()).isEqualTo(expected)
    }

    @Test
    fun equatesIntegerToFraction() {
        val source = Fraction(4, 2)

        assertThat(source).isEqualTo(2)
    }

    @Test
    fun equatesEquivalentFractions() {
        val first = Fraction(2, 4)
        val second = Fraction(-3, -6)

        assertThat(first).isEqualTo(second)
    }

    @Test
    fun canGetTopHeavyString() {
        val fraction = Fraction(1, 1, 2)
        val expected = "3/2"
        val actual = fraction.topHeavyString

        assertThat(actual).isEqualTo(expected)
    }
}