package com.skewwhiffy.auraltester.dao;

import com.skewwhiffy.auraltester.annotation.EntityConstructor;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.util.UUID;

@Entity
data class Info(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: UUID? = null,
    var version: String = ""
)
