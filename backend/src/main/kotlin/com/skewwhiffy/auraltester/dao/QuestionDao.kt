package com.skewwhiffy.auraltester.dao

import com.skewwhiffy.auraltester.dto.question.QuestionType
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id

import java.util.UUID

@Entity
class QuestionDao {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: UUID? = null

    var questionType: QuestionType? = null

    @Column(columnDefinition = "TEXT")
    var question: String? = null
}
