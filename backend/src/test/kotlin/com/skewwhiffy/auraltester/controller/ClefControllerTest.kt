package com.skewwhiffy.auraltester.controller

import com.skewwhiffy.auraltester.notation.factory.InternalNotationFactory
import com.skewwhiffy.auraltester.service.AbcService
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.`when`

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
        clefString = TestData.random().string()
        clef = TestData.random().clef()
        abc = TestData.random().string()
    }

    @org.junit.jupiter.api.Test
    fun getsClefInformation() {
        val expected = ClefResponse(abc)
        `when`(internalNotationFactory.clef(clefString)).thenReturn(clef)
        `when`(abcService.getAbc(clef)).thenReturn { abc }
        val actual = clefController.get(clefString)
        assertThat(actual).isEqualTo(expected)
    }
}