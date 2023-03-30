package com.skewwhiffy.auraltester.controller;

import com.skewwhiffy.auraltester.notation.factory.InternalNotationFactory;
import com.skewwhiffy.auraltester.notation.model.abc.AbcProvider;
import com.skewwhiffy.auraltester.notation.model.clef.Clef;
import com.skewwhiffy.auraltester.notation.model.interval.Interval;
import com.skewwhiffy.auraltester.notation.model.key.Key;
import com.skewwhiffy.auraltester.notation.model.note.AbsoluteNote;
import com.skewwhiffy.auraltester.notation.model.note.IntervalNotes;
import com.skewwhiffy.auraltester.notation.model.note.Note;
import com.skewwhiffy.auraltester.notation.model.note.Octave;
import com.skewwhiffy.auraltester.service.AbcService;
import com.skewwhiffy.auraltester.service.IntervalService;
import com.skewwhiffy.auraltester.test.util.TestData;
import lombok.val;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class IntervalControllerTest {
    @Mock
    private AbcService abcService;

    @Mock
    private InternalNotationFactory internalNotationFactory;

    @Mock
    private IntervalService intervalService;

    @InjectMocks
    private IntervalController intervalController;

    @Mock
    private AbcProvider result;

    private String clefString;
    private Clef clef;
    private String bottomNoteString;
    private Note bottomNote;
    private String keySignature;
    private Note keyNote;
    private IntervalNotes intervalNotes;
    private Interval interval;
    private String abc;

    @BeforeEach
    void setUp() {
        clefString = TestData.random().string();
        clef = TestData.random().clef();
        bottomNoteString = TestData.random().string();
        bottomNote = TestData.random().note();
        keySignature = TestData.random().string();
        keyNote = TestData.random().note();
        intervalNotes = TestData.random().intervalNotes();
        interval = TestData.random().interval();
        abc = TestData.random().string();
    }

    @ParameterizedTest
    @MethodSource("intervalQualityTestCases")
    void respondsCorrectly(
            String intervalQuality,
            int intervalSize,
            String expectedDirectedIntervalString
    ) {
        when(internalNotationFactory.clef(clefString)).thenReturn(clef);
        when(internalNotationFactory.getNote(bottomNoteString))
                .thenReturn(new AbsoluteNote(bottomNote, Octave.getDefault(), Optional.empty()));
        when(internalNotationFactory.getNote(keySignature))
                .thenReturn(new AbsoluteNote(keyNote, Octave.getDefault(), Optional.empty()));
        when(intervalService.getInterval(clef, bottomNote, interval)).thenReturn(intervalNotes);
        when(result.getAbc()).thenReturn(abc);
        when(internalNotationFactory.getDirectedInterval(expectedDirectedIntervalString))
                .thenReturn(interval.up());
        when(abcService.getAbc(clef, intervalNotes, Key.major(keyNote)))
                .thenReturn(result);

        val actual = intervalController
                .get(clefString, bottomNoteString, intervalQuality, intervalSize, Optional.of(keySignature));

        assertThat(actual.abc()).isEqualTo(abc);
    }

    @Test
    void when_intervalQualityNotValid_then_throws() {
        when(internalNotationFactory.clef(clefString)).thenReturn(clef);
        when(internalNotationFactory.getNote(bottomNoteString))
                .thenReturn(new AbsoluteNote(bottomNote, Octave.getDefault(), Optional.empty()));

        assertThatThrownBy(() -> intervalController.get(clefString, bottomNoteString, "demented", 5, Optional.of(keySignature)))
                .isInstanceOf(IllegalArgumentException.class);

    }

    static Stream<Arguments> intervalQualityTestCases() {
        return Stream.of(
                Arguments.of("perfect", 5, "+5"),
                Arguments.of("major", 7, "+7"),
                Arguments.of("minor", 3, "+3-"),
                Arguments.of("diminished", 4, "+4-"),
                Arguments.of("diminished", 2, "+2--"),
                Arguments.of("augmented", 6, "+6+")
        );
    }
}