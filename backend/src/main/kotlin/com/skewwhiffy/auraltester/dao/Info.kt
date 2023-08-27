package com.skewwhiffy.auraltester.dao

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id

import java.util.UUID

@Entity
class Info (
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    val id: UUID?,
    val version: String
)
