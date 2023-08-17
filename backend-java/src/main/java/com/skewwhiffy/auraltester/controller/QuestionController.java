package com.skewwhiffy.auraltester.controller;

import com.skewwhiffy.auraltester.dto.question.AnswerRequest;
import com.skewwhiffy.auraltester.dto.question.AnswerResponse;
import com.skewwhiffy.auraltester.dto.question.QuestionRequest;
import com.skewwhiffy.auraltester.dto.question.QuestionResponse;
import com.skewwhiffy.auraltester.service.QuestionService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/question")
@AllArgsConstructor
public class QuestionController {
    private final QuestionService questionService;

    @PostMapping
    public QuestionResponse newQuestion(
            @RequestBody QuestionRequest request
    ) {
        return questionService.get(request);
    }

    @PostMapping("answer")
    public AnswerResponse answerQuestion(
            @RequestBody AnswerRequest request
    ) {
        return questionService.answer(request.id(), request.answer());
    }
}
