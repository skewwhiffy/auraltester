package com.skewwhiffy.auraltester.controller

import com.skewwhiffy.auraltester.dto.question.AnswerRequest
import com.skewwhiffy.auraltester.dto.question.QuestionRequest
import com.skewwhiffy.auraltester.dto.question.QuestionResponse
import com.skewwhiffy.auraltester.service.QuestionService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/question")
class QuestionController(private val questionService: QuestionService) {
    @PostMapping
    fun newQuestion(@RequestBody request:QuestionRequest): QuestionResponse = questionService.get(request)

    @PostMapping("answer")
    fun answerQuestion(@RequestBody request: AnswerRequest) = questionService.answer(request.id, request.answer)
}
