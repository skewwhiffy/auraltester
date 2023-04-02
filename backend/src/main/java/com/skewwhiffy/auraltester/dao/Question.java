package com.skewwhiffy.auraltester.dao;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;

import java.util.UUID;

@Entity
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Getter
    private UUID id;
    @Getter
    private String questionType;
    @Getter
    private String question;

    Question() {
    }

    public Question(String questionType, String question) {
        this.questionType = questionType;
        this.question = question;
    }
}
