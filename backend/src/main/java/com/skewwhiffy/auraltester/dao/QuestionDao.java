package com.skewwhiffy.auraltester.dao;

import com.skewwhiffy.auraltester.dto.question.QuestionType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;

import java.util.UUID;

@Entity(name = "question")
public class QuestionDao {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Getter
    private UUID id;
    @Getter
    private QuestionType questionType;
    @Getter
    private String question;

    QuestionDao() {
    }

    public QuestionDao(QuestionType questionType, String question) {
        this.questionType = questionType;
        this.question = question;
    }
}
