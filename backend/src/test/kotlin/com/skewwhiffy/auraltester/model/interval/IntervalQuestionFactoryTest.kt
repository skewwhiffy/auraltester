package com.skewwhiffy.auraltester.model.interval

import com.skewwhiffy.auraltester.dao.model
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class IntervalQuestionFactoryTest {
    @Autowired
    private lateinit var intervalQuestionFactory: IntervalQuestionFactory

    @Test
    fun `all possible intervals are selected`() {
        val intervals = (1..100)
            .map { intervalQuestionFactory.makeNewQuestion() as IntervalQuestion }
            .map { it.dao }
            .map { it.upperNote.model - it.lowerNote.model }

    }
}
