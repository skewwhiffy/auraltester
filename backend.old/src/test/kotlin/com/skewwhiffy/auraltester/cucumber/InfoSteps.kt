package com.skewwhiffy.auraltester.cucumber

import io.cucumber.java.en.Given
import io.cucumber.java.en.Then
import org.assertj.core.api.Assertions.assertThat
import org.springframework.http.HttpStatus

class InfoSteps(private val restRequests: RestRequests) {
    @Given("I hit the info endpoint")
    fun hitInfoEndpoint() {
        restRequests.makeGetRequest("/api/info")
    }

    @Then("system information is returned")
    fun systemInformationReturned() {
        val response = restRequests.lastResponse
        assertThat(response.status).isEqualTo(HttpStatus.OK.value())
        val responseMap = restRequests.lastResponseAsMap
        assertThat(responseMap["version"]).isInstanceOf(String::class.java)
    }
}