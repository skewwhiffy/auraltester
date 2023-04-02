package com.skewwhiffy.auraltester.controller;

import com.skewwhiffy.auraltester.notation.factory.InternalNotationFactory;
import com.skewwhiffy.auraltester.notation.factory.ScaleTypeFactory;
import com.skewwhiffy.auraltester.notation.model.abc.AbcProvider;
import com.skewwhiffy.auraltester.notation.model.key.Key;
import com.skewwhiffy.auraltester.notation.model.scale.ScaleDirection;
import com.skewwhiffy.auraltester.notation.model.scale.ScaleType;
import com.skewwhiffy.auraltester.service.AbcService;
import com.skewwhiffy.auraltester.service.ScaleService;
import com.skewwhiffy.auraltester.test.util.TestData;
import lombok.val;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ScaleControllerTest {
    @Mock
    private AbcService abcService;

    @Mock
    private InternalNotationFactory internalNotationFactory;

    @Mock
    private ScaleService scaleService;

    @Mock
    private ScaleTypeFactory scaleTypeFactory;

    @InjectMocks
    private ScaleController scaleController;


    @ParameterizedTest
    @MethodSource("scaleTestCases")
    void respondsCorrectly(
            String scaleTypeString,
            Function<ScaleTypeFactory, ScaleType> getScale,
            String direction,
            ScaleDirection directionObject
    ) {
        val clefString = TestData.random().string();
        val noteString = TestData.random().string();
        val abcWithoutKeySignature = TestData.random().string();
        val abcWithKeySignature = TestData.random().string();
        val scaleLowestNote = TestData.random().absoluteNote();
        val scale = TestData.random().scale();
        val key = scaleTypeString.equals("major") ? Key.major(scale.lowestNote().note()) : Key.minor(scale.lowestNote().note());
        val clefObject = TestData.random().clef();
        val scaleType = TestData.random().scaleType();
        val keyCaptor = ArgumentCaptor.forClass(Key.class);
        when(internalNotationFactory.clef(clefString)).thenReturn(clefObject);
        when(internalNotationFactory.getNote(noteString)).thenReturn(scaleLowestNote);
        when(getScale.apply(scaleTypeFactory)).thenReturn(scaleType);
        when(
                scaleService.getScale(
                        clefObject,
                        scaleLowestNote.note(),
                        scaleType,
                        directionObject
                )
        ).thenReturn(scale);
        when(abcService.getAbc(clefObject, scale))
                .thenReturn(() -> abcWithoutKeySignature);
        when(abcService.getAbc(eq(clefObject), eq(scale), keyCaptor.capture()))
                .then(it -> {
                            val suppliedClef = it.getArgument(0);
                            val suppliedScale = it.getArgument(1);
                            val suppliedKey = it.getArgument(2);

                            assertThat(suppliedClef).isEqualTo(clefObject);
                            assertThat(suppliedScale).isEqualTo(scale);
                            return (AbcProvider) () -> suppliedKey == null ? abcWithoutKeySignature : abcWithKeySignature;
                        }
                );

        val actual = scaleController.get(
                clefString,
                noteString,
                scaleTypeString,
                direction
        );

        val allValues = keyCaptor.getAllValues();
        assertThat(allValues).hasSameElementsAs(List.of(key));
        verify(abcService).getAbc(clefObject, scale);
        assertThat(actual.withKeySignature()).isEqualTo(abcWithKeySignature);
        assertThat(actual.withoutKeySignature()).isEqualTo(abcWithoutKeySignature);
    }

    static Stream<Arguments> scaleTestCases() {
        return Map.of(
                        "major",
                        (Function<ScaleTypeFactory, ScaleType>) ScaleTypeFactory::getMajor,
                        "minor-harmonic",
                        ScaleTypeFactory::getMinorHarmonic,
                        "minor-melodic",
                        ScaleTypeFactory::getMinorMelodic
                )
                .entrySet()
                .stream()
                .flatMap(scaleType -> Map.of(
                                "ascending",
                                ScaleDirection.ASCENDING,
                                "descending",
                                ScaleDirection.DESCENDING
                        )
                        .entrySet()
                        .stream()
                        .map(it -> Arguments.of(
                                scaleType.getKey(),
                                scaleType.getValue(),
                                it.getKey(),
                                it.getValue()
                        )));
    }
}

/*
  "throw when an invalid scale type is requested" in {
    val note = TestData.random.string
    when(internalNotationFactory.getNote(note)).thenReturn(TestData.random.absoluteNote)

    assertThrows[IllegalArgumentException] {
      scaleController.index("alto", note, "not-a-scale", "ascending").apply(getRequest)
    }
  }

  "throw when an invalid direction is requested" in {
    val note = TestData.random.string
    when(internalNotationFactory.getNote(note)).thenReturn(TestData.random.absoluteNote)
    when(scaleTypeFactory.major).thenReturn(TestData.random.scaleType)

    assertThrows[IllegalArgumentException] {
      scaleController.index("bass", note, "major", "stationary").apply(getRequest)
    }
  }
}
*/
