package com.skewwhiffy.auraltester.notation.model.scale;

import lombok.Getter;

public enum ScaleDirection {
    ASCENDING("ascending"),
    DESCENDING("descending");

    @Getter
    private final String displayString;

    ScaleDirection(String displayString) {
        this.displayString = displayString;
    }
}
