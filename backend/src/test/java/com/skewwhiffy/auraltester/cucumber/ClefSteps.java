package com.skewwhiffy.auraltester.cucumber;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import lombok.AllArgsConstructor;
import lombok.val;
import org.springframework.http.HttpStatus;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@AllArgsConstructor
public class ClefSteps {
    private final RestRequests restRequests;

    @When("I request a(n) {} clef")
    public void when_requestClef(String clef) {
        restRequests.makeGetRequest("/api/clef?clef=" + clef);
    }

    @Then("I get a clef")
    public void then_getClef() {
        val response = restRequests.getLastResponse();
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        val responseMap = restRequests.getLastResponseAsMap();
        assertThat(responseMap.get("abc")).isInstanceOf(String.class);
    }
}
