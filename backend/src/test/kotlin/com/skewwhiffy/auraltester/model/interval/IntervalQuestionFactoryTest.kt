package com.skewwhiffy.auraltester.model.interval

import com.skewwhiffy.auraltester.dao.model
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class IntervalQuestionFactoryTest {
    @Autowired
    private lateinit var intervalQuestionFactory: IntervalQuestionFactory

    @Test
    fun `all possible intervals are selected`() {
        val allIntervals = (1..1000)
            .map { intervalQuestionFactory.makeNewQuestion() as IntervalQuestion }
            .map { it.dao }
            .map { it.upperNote.model - it.lowerNote.model }

        (1..8)
            .map { allIntervals.filter { interval -> interval.interval.degree == it } }
            .forEach { intervals ->
                assertThat(intervals).isNotEmpty
                val expectedDeviations = when (intervals.first().interval.degree) {
                    in listOf(1, 4, 5, 8) -> (-1..1)
                    else -> (-2..1)
                }
                val deviations = intervals.map { it.interval.deviation }.distinct()
                assertThat(deviations).hasSameElementsAs(expectedDeviations)
            }
    }
}
