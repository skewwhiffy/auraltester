package com.skewwhiffy.auraltester.dao

import com.skewwhiffy.auraltester.dto.question.QuestionType
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import java.util.UUID

@Entity(name = "question")
data class QuestionDao(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: UUID? = null,
    var questionType: QuestionType,
    var question: String
)
