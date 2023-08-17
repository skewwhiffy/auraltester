package com.skewwhiffy.auraltester.dto.question;

import java.util.List;
import java.util.UUID;

public record AnswerRequest(
        UUID id,
        List<String> answer
) {
}
