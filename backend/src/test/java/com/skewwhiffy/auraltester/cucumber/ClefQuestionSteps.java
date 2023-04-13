package com.skewwhiffy.auraltester.cucumber;

import com.skewwhiffy.auraltester.dao.QuestionDao;
import com.skewwhiffy.auraltester.model.ClefQuestionFactory;
import com.skewwhiffy.auraltester.repository.QuestionRepository;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import lombok.RequiredArgsConstructor;
import lombok.val;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@RequiredArgsConstructor
public class ClefQuestionSteps {
    private final RestRequests restRequests;
    private final QuestionRepository questionRepository;
    private final ClefQuestionFactory clefQuestionFactory;
    private UUID questionId;

    @When("I request a clef question")
    public void when_requestClefQuestion() {
        val request = Map.of("type", "CLEF");
        restRequests.makePostRequest("/api/question", request);
    }

    @Then("I get a clef question")
    public void then_clefQuestionReceived() {
        val responseMap = restRequests.getLastResponseAsMap();
        questionId = UUID.fromString(responseMap.get("questionId").toString());
        @SuppressWarnings("unchecked")
        List<String> answerTypes = (List<String>) responseMap.get("answerTypes");
        assertThat(answerTypes).hasSize(1);
        assertThat(answerTypes.get(0)).isEqualTo("NOTE_NAME");
    }

    @When("I answer the question correctly")
    public void when_answerCorrectly() {
        val answer = getAnswer();
        val payload = Map.of(
                "id", questionId,
                "answer", answer
        );
        restRequests.makePostRequest("/api/question/answer", payload);
    }

    @When("I answer the question incorrectly")
    public void when_answerIncorrectly() {
        val payload = Map.of(
                "id", questionId,
                "answer", Collections.singletonList("not correct")
        );
        restRequests.makePostRequest("/api/question/answer", payload);
    }

    @Then("response is correct")
    public void then_responseIsCorrect() {
        val response = restRequests.getLastResponseAsMap();
        assertThat(response.get("isCorrect")).isEqualTo(true);
    }

    @Then("response is incorrect")
    public void then_responseIsIncorrect() {
        val response = restRequests.getLastResponseAsMap();
        assertThat(response.get("isCorrect")).isEqualTo(false);
    }

    private List<String> getAnswer() {
        String questionJson = questionRepository
                .findById(questionId)
                .map(QuestionDao::getQuestion)
                .orElseThrow(AssertionError::new);
        val question = clefQuestionFactory.getQuestion(questionJson);
        return question.getAnswer();
    }

}
