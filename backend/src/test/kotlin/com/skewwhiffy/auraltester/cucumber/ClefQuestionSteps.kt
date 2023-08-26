package com.skewwhiffy.auraltester.cucumber

import com.skewwhiffy.auraltester.model.ClefQuestionFactory
import com.skewwhiffy.auraltester.repository.QuestionRepository
import io.cucumber.java.en.Then
import io.cucumber.java.en.When
import org.assertj.core.api.Assertions.assertThat
import java.util.UUID
import kotlin.jvm.optionals.getOrNull

class ClefQuestionSteps(
    private val restRequests: RestRequests,
    private val questionRepository: QuestionRepository,
    private val clefQuestionFactory: ClefQuestionFactory,
    private var questionId: UUID? = null
) {
    @When("I request a clef question")
    fun when_requestClefQuestion() {
        val request = mapOf("type" to "CLEF")
        restRequests.makePostRequest("/api/question", request)
    }

    @Suppress("UNCHECKED_CAST")
    @Then("I get a clef question")
    fun then_clefQuestionReceived() {
        val responseMap = restRequests.lastResponseAsMap
        questionId = UUID.fromString(responseMap["questionId"].toString())
        val answerTypes = responseMap["answerTypes"] as List<String>
        assertThat(answerTypes).hasSize(1)
        assertThat(answerTypes[0]).isEqualTo("NOTE_NAME")
    }

    @When("I answer the question correctly")
    fun when_answerCorrectly() {
        val answer = answer
        val payload = mapOf("id" to questionId, "answer" to answer)
        restRequests.makePostRequest("/api/question/answer", payload)
    }

    @When("I answer the question incorrectly")
    fun when_answerIncorrectly() {
        val payload = mapOf(
            "id" to questionId,
            "answer" to listOf("not correct")
        )
        restRequests.makePostRequest("/api/question/answer", payload)
    }

    @Then("response is correct")
    fun then_responseIsCorrect() {
        val response = restRequests.lastResponseAsMap
        assertThat(response["isCorrect"]).isEqualTo(true)
    }

    @Then("response is incorrect")
    fun then_responseIsIncorrect() {
        val response = restRequests.lastResponseAsMap
        assertThat(response["isCorrect"]).isEqualTo(false)
    }

    private val answer: List<String>
        get() {
            val questionJson = questionId
                ?.let { questionRepository.findById(it).getOrNull()?.question }
                ?: throw AssertionError()

            val question = clefQuestionFactory.getQuestion(questionJson)
            return question.answer
        }
}
