package com.skewwhiffy.auraltester.dto.question;

import java.util.UUID

data class AnswerRequest(val id: UUID, val answer: List<String>)
