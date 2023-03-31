package com.skewwhiffy.auraltester.notation.factory;

import com.skewwhiffy.auraltester.test.util.TestData;

import static org.assertj.core.api.Assertions.assertThat;

import lombok.val;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.mockito.Mockito.when;

import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class KeyFactoryTest {
    @Mock
    private NoteFactory noteFactory;
    @InjectMocks
    private KeyFactory keyFactory;

    @Test
    void instantiatesMajorKey() {
        val rawKey = "a#";
        val expectedNote = TestData.random().note();
        when(noteFactory.getNote("A#")).thenReturn(expectedNote);

        val actual = keyFactory.getKey(rawKey);

        assertThat(actual.note()).isEqualTo(expectedNote);
        assertThat(actual.isMinor()).isFalse();
    }

    @Test
    void recognizesMSuffixForMinor() {
        val rawKey = "abm";
        val expectedNote = TestData.random().note();
        when(noteFactory.getNote("Ab")).thenReturn(expectedNote);

        val actual = keyFactory.getKey(rawKey);

        assertThat(actual.note()).isEqualTo(expectedNote);
        assertThat(actual.isMinor()).isTrue();
    }

    @Test
    void recognizesMinorSuffixForMinor() {
        val rawKey = "bx minor";
        val expectedNote = TestData.random().note();
        when(noteFactory.getNote("Bx")).thenReturn(expectedNote);

        val actual = keyFactory.getKey(rawKey);

        assertThat(actual.note()).isEqualTo(expectedNote);
        assertThat(actual.isMinor()).isTrue();
    }
}