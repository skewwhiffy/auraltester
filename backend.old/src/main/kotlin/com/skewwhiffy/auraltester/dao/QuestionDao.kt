package com.skewwhiffy.auraltester.dao

import com.skewwhiffy.auraltester.dto.question.QuestionType
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id

import java.util.UUID

@Entity
class QuestionDao(
    val questionType: QuestionType,
    @Column(columnDefinition = "TEXT")
    val question: String,
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    val id: UUID? = null,
)
