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
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import io.mockk.mockk
import io.mockk.slot
import io.mockk.verify
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.extension.ExtendWith
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource

@ExtendWith(MockKExtension::class)
class ScaleControllerTest {
    companion object {
        @JvmStatic
        private fun scaleTestCases() = mapOf(
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

    @MockK
    private lateinit var abcService: AbcService

    @MockK
    private lateinit var internalNotationFactory: InternalNotationFactory

    @MockK
    private lateinit var scaleService: ScaleService

    @MockK
    private lateinit var scaleTypeFactory: ScaleTypeFactory

    @InjectMockKs
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
        val keyCaptor = slot<Key>()
        every { internalNotationFactory.clef(clefString) } returns clefObject
        every { internalNotationFactory.getNote(noteString) } returns scaleLowestNote
        every { getScale(scaleTypeFactory) } returns scaleType
        every {
            scaleService.getScale(
                clefObject,
                scaleLowestNote.note,
                scaleType,
                directionObject
            )
        } returns scale
        val singleLineAbc = mockk<SingleLineAbc>()
        every { singleLineAbc.abc } returns abcWithKeySignature
        every { abcService.getAbc(clefObject, scale) } answers {
            object : AbcProvider {
                override val abc = abcWithoutKeySignature
            }
        }
        every {
            abcService.getAbc(
                clefObject,
                scale,
                capture(keyCaptor)
            )
        } answers {
            val suppliedClef = it.invocation.args[0] as Clef
            val suppliedScale = it.invocation.args[1] as Scale
            val suppliedKey = it.invocation.args[2] as Key?

            assertThat(suppliedClef).isEqualTo(clefObject)
            assertThat(suppliedScale).isEqualTo(scale)
            object : AbcProvider {
                override val abc = if (suppliedKey == null) abcWithoutKeySignature else abcWithKeySignature
            }
        }

        val actual = scaleController.get(
            clefString,
            noteString,
            scaleTypeString,
            direction
        )

        val capturedKey = keyCaptor.captured
        assertThat(capturedKey).isEqualTo(key)
        verify { abcService.getAbc(clefObject, scale) }
        assertThat(actual.withKeySignature).isEqualTo(abcWithKeySignature)
        assertThat(actual.withoutKeySignature).isEqualTo(abcWithoutKeySignature)
    }

}
