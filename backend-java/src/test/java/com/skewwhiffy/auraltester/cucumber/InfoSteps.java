package com.skewwhiffy.auraltester.cucumber;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import lombok.AllArgsConstructor;
import lombok.val;
import org.springframework.http.HttpStatus;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@AllArgsConstructor
public class InfoSteps {
    private final RestRequests restRequests;

    @Given("I hit the info endpoint")
    public void hitInfoEndpoint() {
        restRequests.makeGetRequest("/api/info");
    }

    @Then("system information is returned")
    public void systemInformationReturned() {
        val response = restRequests.getLastResponse();
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        val responseMap = restRequests.getLastResponseAsMap();
        assertThat(responseMap.get("version")).isInstanceOf(String.class);
    }
}