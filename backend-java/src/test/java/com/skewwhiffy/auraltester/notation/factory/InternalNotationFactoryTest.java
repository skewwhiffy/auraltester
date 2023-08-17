package com.skewwhiffy.auraltester.notation.factory;

import com.codepoetics.protonpack.StreamUtils;
import com.skewwhiffy.auraltester.exception.UnrecognizedClefException;
import com.skewwhiffy.auraltester.test.util.TestData;
import lombok.val;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.util.Pair;

import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.Collections;
import java.util.Locale;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class InternalNotationFactoryTest {
    @Mock
    private ClefFactory clefFactory;

    @Mock
    private IntervalFactory intervalFactory;

    @Mock
    private KeyFactory keyFactory;

    @Mock
    private NoteFactory noteFactory;

    @InjectMocks
    private InternalNotationFactory internalNotationFactory;

    @ParameterizedTest
    @ValueSource(strings = {"treble", "alto", "tenor", "bass"})
    void when_clefIsRequested_then_proxiesToClefFactory(String clef) throws InvocationTargetException, IllegalAccessException {
        val getter = "get" + clef.substring(0, 1).toUpperCase(Locale.UK) + clef.substring(1);
        val expected = TestData.random().clef();
        val method = Arrays.stream(ClefFactory.class.getMethods())
                .filter(it -> it.getName().equals(getter))
                .findFirst()
                .orElseThrow();
        when(method.invoke(clefFactory)).thenReturn(expected);

        val actual = internalNotationFactory.clef(clef);

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void when_clefNameInvalid_then_throws() {
        assertThatThrownBy(() -> internalNotationFactory.clef("not a clef name"))
                .isInstanceOf(UnrecognizedClefException.class);
    }

    @Test
    void when_noteIsRequested_then_proxiesToNoteFactory() {
        val rawNote = TestData.random().string();
        val expected = TestData.random().absoluteNote();
        when(noteFactory.getAbsoluteNote(rawNote)).thenReturn(expected);

        val actual = internalNotationFactory.getNote(rawNote);

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void when_notesAreRequested_then_proxiesToNoteFactory() {
        val rawNotes = IntStream
                .range(1, 10)
                .mapToObj(it -> TestData.random().string())
                .toList();
        val expected = rawNotes.stream().map(it -> TestData.random().absoluteNote()).toList();
        StreamUtils.zip(rawNotes.stream(), expected.stream(), Pair::of)
                .forEach(it -> when(noteFactory.getAbsoluteNote(it.getFirst())).thenReturn(it.getSecond()));
        val notesRaw = String.join(" ", rawNotes);

        val actual = internalNotationFactory.getNotes(notesRaw);

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void when_directedIntervalIsRequested_then_proxiesToIntervalFactory() {
        val rawInterval = TestData.random().string();
        val expected = TestData.random().directedInterval();
        when(intervalFactory.getDirectedInterval(rawInterval)).thenReturn(expected);

        val actual = internalNotationFactory.getDirectedInterval(rawInterval);

        assertThat(actual).isEqualTo(expected);
    }


    @Test
    void when_directedIntervalsAreRequested_then_proxiesToIntervalFactory() {
        val rawInterval = TestData.random().string();
        val expected = Collections.singletonList(TestData.random().directedInterval());
        when(intervalFactory.getDirectedIntervals(rawInterval)).thenReturn(expected);

        val actual = internalNotationFactory.getDirectedIntervals(rawInterval);

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void when_keyIsRequested_then_proxiesToKeyFactory() {
        val rawKey = TestData.random().string();
        val expected = TestData.random().key();
        when(keyFactory.getKey(rawKey)).thenReturn(expected);

        val actual = internalNotationFactory.getKey(rawKey);

        assertThat(actual).isEqualTo(expected);
    }
}