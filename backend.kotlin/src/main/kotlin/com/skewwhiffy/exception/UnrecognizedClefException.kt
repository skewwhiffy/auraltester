package com.skewwhiffy.exception

import org.springframework.http.HttpStatus
import org.springframework.web.server.ResponseStatusException

class UnrecognizedClefException(clef: String) :
    ResponseStatusException(HttpStatus.BAD_REQUEST, "Unrecognized clef type: '$clef'")