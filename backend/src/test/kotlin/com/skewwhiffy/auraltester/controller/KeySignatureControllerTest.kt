package com.skewwhiffy.auraltester.controller

import com.skewwhiffy.auraltester.notation.factory.InternalNotationFactory
import com.skewwhiffy.auraltester.notation.model.abc.AbcProvider
import com.skewwhiffy.auraltester.notation.model.clef.Clef
import com.skewwhiffy.auraltester.notation.model.key.Key
import com.skewwhiffy.auraltester.notation.model.note.AbsoluteNote
import com.skewwhiffy.auraltester.service.AbcService
import com.skewwhiffy.auraltester.test.util.TestData
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.jupiter.MockitoExtension


@ExtendWith(MockitoExtension::class)
class KeySignatureControllerTest {
    @Mock
    private lateinit var internalNotationFactory: InternalNotationFactory

    @Mock
    private lateinit var abcService: AbcService

    @InjectMocks
    private lateinit var keySignatureController: KeySignatureController
    private lateinit var clef: Clef
    private lateinit var clefString: String
    private lateinit var keySignature: String
    private lateinit var keySignatureNote: AbsoluteNote
    private lateinit var abc: String

    @BeforeEach
    fun setUp() {
        clef = TestData.random.clef
        clefString = TestData.random.string
        keySignature = TestData.random.string
        keySignatureNote = TestData.random.absoluteNote
        abc = TestData.random.string
    }

    @Test
    fun respondsCorrectly() {
        `when`(internalNotationFactory.clef(clefString)).thenReturn(clef)
        `when`(internalNotationFactory.getNote(keySignature)).thenReturn(keySignatureNote)
        val abcResult = abc
        `when`(abcService.getAbc(clef, Key.major(keySignatureNote.note)))
            .thenReturn(object : AbcProvider {
                override val abc = abcResult
            })
        val actual = keySignatureController.get(clefString, keySignature)

        assertThat(actual.abc).isEqualTo(abc)
    }
}
