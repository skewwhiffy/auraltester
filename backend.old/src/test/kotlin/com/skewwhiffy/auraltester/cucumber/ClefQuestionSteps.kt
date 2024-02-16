package com.skewwhiffy.auraltester.cucumber

import io.cucumber.java.en.Then
import io.cucumber.java.en.When
import org.assertj.core.api.Assertions.assertThat

class ClefQuestionSteps(
    private val restRequests: RestRequests,
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
        val answerTypes = responseMap["answerTypes"] as List<String>
        assertThat(answerTypes).hasSize(1)
        assertThat(answerTypes[0]).isEqualTo("NOTE_NAME")
    }

    @When("I answer the question incorrectly")
    fun when_answerIncorrectly() {
        val payload = mapOf(
            "id" to restRequests.lastQuestionId,
            "answer" to listOf("not correct")
        )
        restRequests.makePostRequest("/api/question/answer", payload)
    }

}
