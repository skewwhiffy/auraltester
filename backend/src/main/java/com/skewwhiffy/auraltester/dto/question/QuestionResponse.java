package com.skewwhiffy.auraltester.dto.question;

import java.util.UUID;

public record QuestionResponse(
        UUID questionId,
        QuestionResponseElement[] elements
) {
}
