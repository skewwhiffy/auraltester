package com.skewwhiffy.controller

import com.skewwhiffy.dto.ClefResponse
import com.skewwhiffy.notation.factory.InternalNotationFactory
import com.skewwhiffy.notation.model.abc.AbcProvider
import com.skewwhiffy.notation.model.clef.Clef
import com.skewwhiffy.service.AbcService
import com.skewwhiffy.test.util.TestData
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
  fun `set up`() {
    clefString = TestData.random.string
    clef = TestData.random.clef
    abc = TestData.random.string
  }

  @Test
  fun `gets clef information`() {
    val expected = ClefResponse(abc)
    `when`(internalNotationFactory.clef(clefString)).thenReturn(clef)
    `when`(abcService.getAbc(clef)).thenReturn(abc.let {
      object : AbcProvider {
        override val abc = it
      }
    })

    val actual = clefController.get(clefString)

    assertThat(actual).isEqualTo(expected)
  }
}