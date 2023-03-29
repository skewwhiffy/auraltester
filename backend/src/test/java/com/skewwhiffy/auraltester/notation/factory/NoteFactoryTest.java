package com.skewwhiffy.auraltester.notation.factory;

import com.skewwhiffy.auraltester.notation.model.key.Key;
import com.skewwhiffy.auraltester.notation.model.note.AbsoluteNote;
import lombok.val;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
public class NoteFactoryTest {
    @InjectMocks
    private NoteFactory noteFactory;

    @Test
    void instantiatesMiddleC() {
        val expected = AbsoluteNote.getMiddleC();

        val actual = noteFactory.getAbsoluteNote("C");

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void instantiatesNoteAboveMiddleC() {
        val expected = "c''";

        val actual = noteFactory.getAbsoluteNote(expected);

        assertThat(actual.getAbc(Key.getCMajor())).isEqualTo(expected);
    }
    /*

  @Test
  fun `instantiate note below middle C`() {
    val internalNotation = "Dx#,,,"
    val expected = "^^^D,,,"

    val actual = noteFactory.getAbsoluteNote(internalNotation)

    assertThat(actual.abc(Key.cMajor)).isEqualTo(expected)
  }

  @Test
  fun `throw when note name invalid`() {
    assertThrows<IllegalArgumentException> {
      noteFactory.getAbsoluteNote("H")
    }
  }
}
     */
}
