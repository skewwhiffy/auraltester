package com.skewwhiffy.auraltester.service;

import com.skewwhiffy.auraltester.test.util.TestData;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
class IntervalServiceTest {
  @InjectMocks
  private IntervalService intervalService;

          /*
  @Test
  fun `gets interval notes`() {
    val clef = TestData.random.clef
    val note = TestData.random.note
    val interval = TestData.random.interval
    val expectedBottomNote = clef.getNoteNearBottom(note)

    val actual = intervalService.getInterval(clef, note, interval)

    assertThat(actual.bottomNote).isEqualTo(expectedBottomNote)
  }

           */
}