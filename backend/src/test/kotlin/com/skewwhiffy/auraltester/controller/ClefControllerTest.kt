package com.skewwhiffy.auraltester.controller

import com.skewwhiffy.auraltester.dto.ClefResponse
import com.skewwhiffy.auraltester.notation.factory.InternalNotationFactory
import com.skewwhiffy.auraltester.notation.model.abc.AbcProvider
import com.skewwhiffy.auraltester.notation.model.clef.Clef
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
class ClefControllerTest {
    @Mock
    private lateinit var internalNotationFactory: InternalNotationFactory

    @Mock
    private lateinit var abcService: AbcService

    @InjectMocks
    private lateinit var clefController: ClefController

    private lateinit var clefString: String
    private lateinit var clef: Clef
    private lateinit var abc: String

    @BeforeEach
    fun setUp() {
        clefString = TestData.random.string
        clef = TestData.random.clef
        abc = TestData.random.string
    }

    @Test
    fun getsClefInformation() {
        val expected = ClefResponse(abc)
        `when`(internalNotationFactory.clef(clefString)).thenReturn(clef)
        val returnValue = abc
        `when`(abcService.getAbc(clef)).thenReturn(object : AbcProvider {
            override val abc = returnValue
        })
        val actual = clefController.get(clefString)
        assertThat(actual).isEqualTo(expected)
    }
}