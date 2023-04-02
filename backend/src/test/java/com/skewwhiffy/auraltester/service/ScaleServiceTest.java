package com.skewwhiffy.auraltester.service;

import com.skewwhiffy.auraltester.test.util.TestData;
import lombok.val;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
class ScaleServiceTest {
  @InjectMocks
  private ScaleService scaleService;

  @Test
  void instantiatesScale() {
    val clef = TestData.random().clef();
    val note = TestData.random().note();
    val scaleType = TestData.random().scaleType();
    val direction = TestData.random().scaleDirection();
    val expectedBottomNote = clef.getNoteNearBottom(note);

    val actual = scaleService.getScale(clef, note, scaleType, direction);

    assertThat(actual.lowestNote()).isEqualTo(expectedBottomNote);
  }
}