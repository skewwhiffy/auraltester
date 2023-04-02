package com.skewwhiffy.auraltester.model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class ClefAnswer {
    private final String noteName;
}
