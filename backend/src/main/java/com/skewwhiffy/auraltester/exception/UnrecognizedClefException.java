package com.skewwhiffy.auraltester.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.text.MessageFormat;

public class UnrecognizedClefException extends ResponseStatusException {
    public UnrecognizedClefException(String clef) {
        super(HttpStatus.BAD_REQUEST, MessageFormat.format("Unrecognized clef type: '{0}'", clef));
    }

}
