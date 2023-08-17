package com.skewwhiffy.auraltester.model;

import com.skewwhiffy.auraltester.notation.factory.ClefFactory;
import com.skewwhiffy.auraltester.notation.model.note.AbsoluteNote;
import com.skewwhiffy.auraltester.service.AbcService;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ClefQuestionTest {
    @Mock
    private AbcService abcService;
    @Mock
    private ClefFactory clefFactory;
    @Mock
    private ClefType clefType;
    @Mock
    private AbsoluteNote absoluteNote;
}