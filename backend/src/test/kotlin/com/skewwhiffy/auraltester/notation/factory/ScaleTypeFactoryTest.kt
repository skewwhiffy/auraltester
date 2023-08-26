package com.skewwhiffy.auraltester.notation.factory

/*
import com.skewwhiffy.auraltester.test.util.TestData
import lombok.`val`
import org.junit.jupiter.api.Test
import java.util.Arrays
import java.util.function.Consumer
import java.util.function.Supplier
import java.util.stream.IntStream

@ExtendWith(MockitoExtension::class)
internal class ScaleTypeFactoryTest {
    @Mock
    private val intervalFactory: IntervalFactory? = null

    @InjectMocks
    private val scaleTypeFactory: ScaleTypeFactory? = null
    private var expectedIntervals: List<DirectedInterval>? = null
    private var expectedDescendingIntervals: List<DirectedInterval>? = null
    @BeforeEach
    fun setUp() {
        val getIntervals: Supplier<List<DirectedInterval>> = Supplier<List<DirectedInterval>> {
            IntStream
                .range(1, 10)
                .mapToObj<Any>(IntFunction<Any> { it: Int -> TestData.random().directedInterval() })
                .toList()
        }
        expectedIntervals = getIntervals.get()
        expectedDescendingIntervals = getIntervals.get()
    }

    @Test
    fun instantiatesMajorScale() {
        `when`(intervalFactory!!.getDirectedIntervals("1 2 3 4 5 6 7 8"))
            .thenReturn(expectedIntervals)
        val actual: `val` = scaleTypeFactory!!.major
        Arrays.stream<ScaleDirection>(ScaleDirection.entries.toTypedArray())
            .forEach(Consumer<ScaleDirection> { it: ScaleDirection? ->
                assertThat(actual.getIntervals(it)).isEqualTo(
                    expectedIntervals
                )
            })
    }

    @Test
    fun instantiatesMinorScale() {
        `when`(intervalFactory!!.getDirectedIntervals("1 2 3- 4 5 6- 7 8"))
            .thenReturn(expectedIntervals)
        val actual: `val` = scaleTypeFactory!!.minorHarmonic
        Arrays.stream<ScaleDirection>(ScaleDirection.entries.toTypedArray())
            .forEach(Consumer<ScaleDirection> { it: ScaleDirection? ->
                assertThat(actual.getIntervals(it)).isEqualTo(
                    expectedIntervals
                )
            })
    }

    @Test
    fun instantiatesMinorMelodicScale() {
        `when`(intervalFactory!!.getDirectedIntervals("1 2 3- 4 5 6 7 8"))
            .thenReturn(expectedIntervals)
        `when`(intervalFactory!!.getDirectedIntervals("1 2 3- 4 5 6- 7- 8"))
            .thenReturn(expectedDescendingIntervals)
        val actual: `val` = scaleTypeFactory!!.minorMelodic
        assertThat(actual.getIntervals(ScaleDirection.ASCENDING)).isEqualTo(expectedIntervals)
        assertThat(actual.getIntervals(ScaleDirection.DESCENDING)).isEqualTo(expectedDescendingIntervals)
    }
}

 */