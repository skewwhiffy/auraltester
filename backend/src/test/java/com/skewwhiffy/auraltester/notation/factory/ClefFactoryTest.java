package com.skewwhiffy.auraltester.notation.factory;

import com.skewwhiffy.auraltester.model.ClefType;
import com.skewwhiffy.auraltester.notation.model.clef.Clef;
import com.skewwhiffy.auraltester.notation.model.note.AbsoluteNote;
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

import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ClefFactoryTest {
    @Mock
    private NoteFactory noteFactory;
    @InjectMocks
    private ClefFactory clefFactory;

    private AbsoluteNote highLedgerNote;
    private AbsoluteNote lowLedgerNote;

    @BeforeEach
    void setUp() {
        highLedgerNote = TestData.random().absoluteNote();
        lowLedgerNote = TestData.random().absoluteNote();
    }

    @ParameterizedTest
    @MethodSource("clefTypeTestCases")
    void getFromClefTypeWorks(ClefType clefType, Function<ClefFactory, Clef> getExpected) {
        val expected = getExpected.apply(clefFactory);

        val actual = clefFactory.get(clefType);

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void initializedTrebleClefCorrectly() {
        when(noteFactory.getAbsoluteNote("a")).thenReturn(highLedgerNote);

        val actual = clefFactory.getTreble();

        assertThat(actual.abc()).isEqualTo("treble");
        assertThat(actual.lowLedgerNote()).isEqualTo(AbsoluteNote.getMiddleC());
        assertThat(actual.highLedgerNote()).isEqualTo(highLedgerNote);
    }

    @Test
    void initializesAltoClefCorrectly() {
        testGeneric("alto", "D,", "B", () -> clefFactory.getAlto());
    }

    @Test
    void initializesTenorClefCorrectly() {
        testGeneric("tenor", "B,,", "G", () -> clefFactory.getTenor());
    }

    @Test
    void initializesBassClefCorrectly() {
        when(noteFactory.getAbsoluteNote("E,,")).thenReturn(lowLedgerNote);

        val actual = clefFactory.getBass();

        assertThat(actual.abc()).isEqualTo("bass");
        assertThat(actual.lowLedgerNote()).isEqualTo(lowLedgerNote);
        assertThat(actual.highLedgerNote()).isEqualTo(AbsoluteNote.getMiddleC());
    }

    public static Stream<Arguments> clefTypeTestCases() {
        return Stream.of(
                Arguments.of(ClefType.TREBLE, (Function<ClefFactory, Clef>) ClefFactory::getTreble),
                Arguments.of(ClefType.ALTO, (Function<ClefFactory, Clef>) ClefFactory::getAlto),
                Arguments.of(ClefType.TENOR, (Function<ClefFactory, Clef>) ClefFactory::getTenor),
                Arguments.of(ClefType.BASS, (Function<ClefFactory, Clef>) ClefFactory::getBass)
        );
    }

    private void testGeneric(
            String abc,
            String expectedLowLedgerNote,
            String expectedHighLedgerNote,
            Supplier<Clef> getClef
    ) {
        when(noteFactory.getAbsoluteNote(expectedLowLedgerNote)).thenReturn(lowLedgerNote);
        when(noteFactory.getAbsoluteNote(expectedHighLedgerNote)).thenReturn(highLedgerNote);

        val actual = getClef.get();

        assertThat(actual.abc()).isEqualTo(abc);
        assertThat(actual.lowLedgerNote()).isEqualTo(lowLedgerNote);
        assertThat(actual.highLedgerNote()).isEqualTo(highLedgerNote);
    }
}