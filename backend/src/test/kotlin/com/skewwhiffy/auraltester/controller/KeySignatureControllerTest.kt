package com.skewwhiffy.auraltester.controller

import com.ninjasquad.springmockk.MockkBean
import com.skewwhiffy.auraltester.notation.factory.InternalNotationFactory
import com.skewwhiffy.auraltester.notation.model.abc.AbcProvider
import com.skewwhiffy.auraltester.notation.model.clef.Clef
import com.skewwhiffy.auraltester.notation.model.key.Key
import com.skewwhiffy.auraltester.notation.model.note.AbsoluteNote
import com.skewwhiffy.auraltester.service.AbcService
import com.skewwhiffy.auraltester.test.util.TestData
import io.mockk.every
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest


@SpringBootTest
class KeySignatureControllerTest {
    @MockkBean
    private lateinit var internalNotationFactory: InternalNotationFactory

    @MockkBean
    private lateinit var abcService: AbcService

    @Autowired
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
        every { internalNotationFactory.clef(clefString) } returns clef
        every { internalNotationFactory.getNote(keySignature) } returns keySignatureNote
        val abcResult = abc
        every { abcService.getAbc(clef, Key.major(keySignatureNote.note)) } returns object : AbcProvider {
            override val abc = abcResult
        }
        val actual = keySignatureController.get(clefString, keySignature)

        assertThat(actual.abc).isEqualTo(abc)
    }
}
