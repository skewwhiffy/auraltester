package com.skewwhiffy.auraltester.cucumber

import com.skewwhiffy.auraltester.com.skewwhiffy.auraltester.model.clef.ClefQuestionFactory
import com.skewwhiffy.auraltester.repository.QuestionRepository
import io.cucumber.java.en.Then
import io.cucumber.java.en.When
import org.assertj.core.api.Assertions
import java.util.*
import kotlin.jvm.optionals.getOrNull

class QuestionSteps(
    private val questionRepository: QuestionRepository,
    private val clefQuestionFactory: ClefQuestionFactory,
    private val restRequests: RestRequests,
) {
    @Then("response is correct")
    fun then_responseIsCorrect() {
        val response = restRequests.lastResponseAsMap
        Assertions.assertThat(response["isCorrect"]).isEqualTo(true)
    }

    @Then("response is incorrect")
    fun then_responseIsIncorrect() {
        val response = restRequests.lastResponseAsMap
        Assertions.assertThat(response["isCorrect"]).isEqualTo(false)
    }

    @When("I answer the question correctly")
    fun when_answerCorrectly() {
        val answer = answer
        val payload = mapOf("id" to restRequests.lastQuestionId, "answer" to answer)
        restRequests.makePostRequest("/api/question/answer", payload)
    }

    private val answer: List<String>
        get() {
            val questionJson = restRequests.lastQuestionId
                .let { questionRepository.findById(it).getOrNull()?.question }
                ?: throw AssertionError()

            val question = clefQuestionFactory.getQuestion(questionJson)
            return question.answer
        }
}

val RestRequests.lastQuestionId: UUID
    get() = lastResponseAsMap["questionId"]
        .toString()
        .let(UUID::fromString)

