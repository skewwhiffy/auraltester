package com.skewwhiffy.auraltester.cucumber

import io.cucumber.java.en.Then
import io.cucumber.java.en.When
import org.assertj.core.api.Assertions

class IntervalQuestionSteps(
    private val restRequests: RestRequests
) {
    @When("I request an interval question")
    fun when_requestClefQuestion() {
        val request = mapOf("type" to "INTERVAL")
        restRequests.makePostRequest("/api/question", request)
    }

    @Suppress("UNCHECKED_CAST")
    @Then("I get an interval question")
    fun then_getIntervalQuestion() {
        val responseMap = restRequests.lastResponseAsMap
        val answerTypes = responseMap["answerTypes"] as List<String>
        Assertions.assertThat(answerTypes).hasSize(1)
        Assertions.assertThat(answerTypes[0]).isEqualTo("NOTE_NAME")
    }
}