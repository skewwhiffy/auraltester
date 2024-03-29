package com.skewwhiffy.auraltester.controller

import com.ninjasquad.springmockk.MockkBean
import com.skewwhiffy.auraltester.dto.ClefResponse
import com.skewwhiffy.auraltester.notation.factory.InternalNotationFactory
import com.skewwhiffy.auraltester.notation.model.abc.AbcProvider
import com.skewwhiffy.auraltester.notation.model.clef.Clef
import com.skewwhiffy.auraltester.service.AbcService
import com.skewwhiffy.auraltester.test.util.TestData
import io.mockk.every
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class ClefControllerTest {
    @MockkBean
    private lateinit var internalNotationFactory: InternalNotationFactory

    @MockkBean
    private lateinit var abcService: AbcService

    @Autowired
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
        every {internalNotationFactory.clef(clefString)} returns clef
        val returnValue = abc
        every { abcService.getAbc(clef) } returns object : AbcProvider {
            override val abc = returnValue
        }
        val actual = clefController.get(clefString)
        assertThat(actual).isEqualTo(expected)
    }
}