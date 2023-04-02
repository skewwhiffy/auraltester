package com.skewwhiffy.auraltester.controller;

import com.skewwhiffy.auraltester.dto.question.QuestionRequest;
import com.skewwhiffy.auraltester.dto.question.QuestionResponse;
import com.skewwhiffy.auraltester.service.QuestionService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class QuestionController {
    private final QuestionService questionService;

    @PostMapping("/api/question")
    public QuestionResponse newQuestion(
            @RequestBody QuestionRequest request
    ) {
        return questionService.get(request);
    }
}
