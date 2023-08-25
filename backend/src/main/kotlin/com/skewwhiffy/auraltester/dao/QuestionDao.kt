package com.skewwhiffy.auraltester.dao

import com.skewwhiffy.auraltester.dto.question.QuestionType
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Lob

import java.util.UUID

@Entity(name = "question")
class QuestionDao(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    val id: UUID?,
    val questionType: QuestionType,
    @Column(length = 500)
    val question: String
)
