package com.skewwhiffy.auraltester.cucumber

import io.cucumber.java.en.Then
import io.cucumber.java.en.When
import org.assertj.core.api.Assertions.assertThat
import org.springframework.http.HttpStatus

class ClefSteps(private val restRequests: RestRequests) {
    @When("I request a(n) {} clef")
    fun when_requestClef(clef: String) {
        restRequests.makeGetRequest("/api/clef?clef=$clef")
    }

    @Then("I get a clef")
    fun then_getClef() {
        val response = restRequests.lastResponse
        assertThat(response.status).isEqualTo(HttpStatus.OK.value())
        val responseMap = restRequests.lastResponseAsMap
        assertThat(responseMap["abc"]).isInstanceOf(String::class.java)
    }
}
