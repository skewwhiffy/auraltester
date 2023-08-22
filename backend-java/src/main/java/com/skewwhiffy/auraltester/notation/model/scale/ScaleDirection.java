package com.skewwhiffy.auraltester.notation.model.scale;

import lombok.Getter;

@Getter
public enum ScaleDirection {
    ASCENDING("ascending"),
    DESCENDING("descending");

    private final String displayString;

    ScaleDirection(String displayString) {
        this.displayString = displayString;
    }
}
