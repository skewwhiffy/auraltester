package com.skewwhiffy.controller

import com.skewwhiffy.notation.factory.InternalNotationFactory
import com.skewwhiffy.notation.model.abc.AbcProvider
import com.skewwhiffy.notation.model.clef.Clef
import com.skewwhiffy.notation.model.key.Key
import com.skewwhiffy.notation.model.note.AbsoluteNote
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
  fun `set up`() {
    clef = TestData.random.clef
    clefString = TestData.random.string
    keySignature = TestData.random.string
    keySignatureNote = TestData.random.absoluteNote
    abc = TestData.random.string
  }

  @Test
  fun `responds correctly`() {
    `when`(internalNotationFactory.clef(clefString)).thenReturn(clef)
    `when`(internalNotationFactory.getNote(keySignature)).thenReturn(keySignatureNote)
    `when`(abcService.getAbc(clef, Key(keySignatureNote.note))).thenReturn(abc.let {
      object : AbcProvider {
        override val abc: String = it
      }
    })

    val actual = keySignatureController.get(clefString, keySignature)

    assertThat(actual.abc).isEqualTo(abc)
  }
}