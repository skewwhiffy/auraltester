package com.skewwhiffy.auraltester.notation.model.clef;

import com.skewwhiffy.auraltester.notation.model.note.AbsoluteNote;
import com.skewwhiffy.auraltester.test.util.TestData;
import lombok.val;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ClefTest {
  private String abc ;
  private AbsoluteNote lowLedgerNote ;
  private AbsoluteNote highLedgerNote;

  @BeforeEach
  void setUp() {
      abc = TestData.random().string();
      lowLedgerNote = TestData.random().absoluteNote();
      highLedgerNote = TestData.random().absoluteNote();
  }

  @Test
    void retainAbc_lowLedgerNote_and_highLedgerNote() {
    val actual = new Clef(abc, lowLedgerNote, highLedgerNote);

    assertThat(actual.abc()).isEqualTo(abc);
    assertThat(actual.lowLedgerNote()).isEqualTo(lowLedgerNote);
    assertThat(actual.highLedgerNote()).isEqualTo(highLedgerNote);
  }

  @Test
    void equatesSameClefs(){
    val first = new Clef(abc, lowLedgerNote, highLedgerNote);
    val second = new Clef(abc, lowLedgerNote, highLedgerNote);

    assertThat(first).isEqualTo(second);
  }
}