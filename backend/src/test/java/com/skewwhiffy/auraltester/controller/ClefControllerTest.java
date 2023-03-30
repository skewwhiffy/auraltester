package com.skewwhiffy.auraltester.controller;

import com.skewwhiffy.auraltester.dto.ClefResponse;
import com.skewwhiffy.auraltester.notation.factory.InternalNotationFactory;
import com.skewwhiffy.auraltester.notation.model.clef.Clef;
import com.skewwhiffy.auraltester.service.AbcService;
import com.skewwhiffy.auraltester.test.util.TestData;
import lombok.val;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ClefControllerTest {
    @Mock
    private InternalNotationFactory internalNotationFactory;

    @Mock
    private AbcService abcService;

    @InjectMocks
    private ClefController clefController;
    private String clefString;
    private Clef clef;
    private String abc;

    @BeforeEach
    void setUp() {
        clefString = TestData.random().string();
        clef = TestData.random().clef();
        abc = TestData.random().string();
    }

    @Test
    void getsClefInformation() {
        val expected = new ClefResponse(abc);
        when(internalNotationFactory.clef(clefString)).thenReturn(clef);
        when(abcService.getAbc(clef)).thenReturn(() -> abc);

        val actual = clefController.get(clefString);

        assertThat(actual).isEqualTo(expected);
    }
}