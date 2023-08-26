package com.skewwhiffy.auraltester.notation.factory

/*
import com.skewwhiffy.auraltester.notation.model.interval.Interval
import lombok.`val`
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test

@ExtendWith(MockitoExtension::class)
internal class IntervalFactoryTest {
    @InjectMocks
    private val intervalFactory: IntervalFactory? = null
    @ParameterizedTest
    @ValueSource(ints = [2, 3, 6, 7])
    fun instantiatesMajorInterval(intervalDegree: Int) {
        val expected: `val` = Interval.major(intervalDegree).up()
        val actual: `val` = intervalFactory!!.getDirectedInterval(intervalDegree.toString())
        Assertions.assertThat<`val`>(actual).isEqualTo(expected)
    }

    @Test
    fun when_deviationInvalid_then_throws() {
        Assertions.assertThatThrownBy(ThrowingCallable { intervalFactory!!.getDirectedInterval("5*") })
            .isInstanceOf(IllegalArgumentException::class.java)
    }

    @ParameterizedTest
    @ValueSource(ints = [1, 4, 5, 8])
    fun instantiatesPerfectInterval(degree: Int) {
        val expected: `val` = Interval.perfect(degree).up()
        val actual: `val` = intervalFactory!!.getDirectedInterval(degree.toString())
        Assertions.assertThat<`val`>(actual).isEqualTo(expected)
    }

    @Test
    fun instantiatesMinorThird() {
        val expected: `val` = Interval.minor(3).up()
        val actual: `val` = intervalFactory!!.getDirectedInterval("3-")
        Assertions.assertThat<`val`>(actual).isEqualTo(expected)
    }
}

 */