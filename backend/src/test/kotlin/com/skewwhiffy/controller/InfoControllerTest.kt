package com.skewwhiffy.controller

import com.skewwhiffy.dto.InfoResponse
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.junit.jupiter.MockitoExtension

@ExtendWith(MockitoExtension::class)
class InfoControllerTest {
  @InjectMocks
  private lateinit var infoController: InfoController

  @Test
  fun `gets information`() {
    val expected = InfoResponse("0.0.1-kotlin")

    val actual = infoController.get()

    assertThat(actual).isEqualTo(expected)
  }
}