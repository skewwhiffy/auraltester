package com.skewwhiffy.auraltester.controller

import com.skewwhiffy.auraltester.notation.factory.InternalNotationFactory
import com.skewwhiffy.auraltester.notation.factory.ScaleTypeFactory
import com.skewwhiffy.auraltester.notation.model.abc.AbcProvider
import com.skewwhiffy.auraltester.notation.model.abc.SingleLineAbc
import com.skewwhiffy.auraltester.notation.model.clef.Clef
import com.skewwhiffy.auraltester.notation.model.key.Key
import com.skewwhiffy.auraltester.notation.model.scale.Scale
import com.skewwhiffy.auraltester.notation.model.scale.ScaleDirection
import com.skewwhiffy.auraltester.notation.model.scale.ScaleType
import com.skewwhiffy.auraltester.service.AbcService
import com.skewwhiffy.auraltester.service.ScaleService
import com.skewwhiffy.auraltester.test.util.TestData
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.extension.ExtendWith
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import org.mockito.ArgumentCaptor
import org.mockito.ArgumentMatchers.eq
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.junit.jupiter.MockitoExtension

@ExtendWith(MockitoExtension::class)
class ScaleControllerTest {
    companion object {
        @JvmStatic
        private fun scaleTestCases(): List<Arguments> {
            return mapOf(
                "major" to ScaleTypeFactory::major,
                "minor-harmonic" to ScaleTypeFactory::minorHarmonic,
                "minor-melodic" to ScaleTypeFactory::minorMelodic
            )
                .flatMap { scaleType ->
                    mapOf(
                        "ascending" to ScaleDirection.ASCENDING,
                        "descending" to ScaleDirection.DESCENDING
                    ).map { Arguments.of(scaleType.key, scaleType.value, it.key, it.value) }
                }
        }
    }

    @Mock
    private lateinit var abcService: AbcService

    @Mock
    private lateinit var internalNotationFactory: InternalNotationFactory

    @Mock
    private lateinit var scaleService: ScaleService

    @Mock
    private lateinit var scaleTypeFactory: ScaleTypeFactory

    @InjectMocks
    private lateinit var scaleController: ScaleController


    @ParameterizedTest
    @MethodSource("scaleTestCases")
    fun respondsCorrectly(
        scaleTypeString: String,
        getScale: (ScaleTypeFactory) -> ScaleType,
        direction: String,
        directionObject: ScaleDirection
    ) {
        val clefString = TestData.random.string
        val noteString = TestData.random.string
        val abcWithoutKeySignature = TestData.random.string
        val abcWithKeySignature = TestData.random.string
        val scaleLowestNote = TestData.random.absoluteNote
        val scale = TestData.random.scale
        val key = scale.lowestNote.note.let { if (scaleTypeString == "major") Key.major(it) else Key.minor(it) }
        val clefObject = TestData.random.clef
        val scaleType = TestData.random.scaleType
        val keyCaptor = ArgumentCaptor.forClass(Key::class.java)
        `when`(internalNotationFactory.clef(clefString)).thenReturn(clefObject)
        `when`(internalNotationFactory.getNote(noteString)).thenReturn(scaleLowestNote)
        `when`(getScale(scaleTypeFactory)).thenReturn(scaleType)
        `when`(
            scaleService.getScale(
                clefObject,
                scaleLowestNote.note,
                scaleType,
                directionObject
            )
        ).thenReturn(scale)
        val singleLineAbc = mock(SingleLineAbc::class.java)
        `when`(singleLineAbc.abc).thenReturn(abcWithKeySignature)
        `when`(abcService.getAbc(eq(clefObject), eq(scale), keyCaptor.capture()))
            .then {
                val suppliedClef: Clef = it.getArgument(0)
                val suppliedScale: Scale = it.getArgument(1)
                val suppliedKey: Key? = it.getArgument(2)

                assertThat(suppliedClef).isEqualTo(clefObject)
                assertThat(suppliedScale).isEqualTo(scale)
                val abcProvider = mock(AbcProvider::class.java)
                `when`(abcProvider.abc).thenReturn(if (suppliedKey == null) abcWithoutKeySignature else abcWithKeySignature)
            }

        val actual = scaleController.get(
            clefString,
            noteString,
            scaleTypeString,
            direction
        )

        val allValues = keyCaptor.allValues
        assertThat(allValues).hasSameElementsAs(listOf(key))
        verify(abcService).getAbc(clefObject, scale)
        assertThat(actual.withKeySignature).isEqualTo(abcWithKeySignature)
        assertThat(actual.withoutKeySignature).isEqualTo(abcWithoutKeySignature)
    }

}
