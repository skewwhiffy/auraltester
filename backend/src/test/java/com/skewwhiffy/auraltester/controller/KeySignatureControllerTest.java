package com.skewwhiffy.auraltester.controller;

import com.skewwhiffy.auraltester.notation.factory.InternalNotationFactory;
import com.skewwhiffy.auraltester.notation.model.clef.Clef;
import com.skewwhiffy.auraltester.notation.model.key.Key;
import com.skewwhiffy.auraltester.notation.model.note.AbsoluteNote;
import com.skewwhiffy.auraltester.service.AbcService;
import com.skewwhiffy.auraltester.test.util.TestData;
import lombok.val;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class KeySignatureControllerTest {
    @Mock
    private InternalNotationFactory internalNotationFactory;

    @Mock
    private AbcService abcService;

    @InjectMocks
    private KeySignatureController keySignatureController;
    private Clef clef;
    private String clefString;
    private String keySignature;
    private AbsoluteNote keySignatureNote;
    private String abc;

    @BeforeEach
    void setUp() {
        clef = TestData.random().clef();
        clefString = TestData.random().string();
        keySignature = TestData.random().string();
        keySignatureNote = TestData.random().absoluteNote();
        abc = TestData.random().string();
    }

    @Test
    void respondsCorrectly() {
        when(internalNotationFactory.clef(clefString)).thenReturn(clef);
        when(internalNotationFactory.getNote(keySignature)).thenReturn(keySignatureNote);
        when(abcService.getAbc(clef, Key.major(keySignatureNote.note())))
                .thenReturn(() -> abc);

        val actual = keySignatureController.get(clefString, Optional.of(keySignature));

        assertThat(actual.abc()).isEqualTo(abc);
    }
}