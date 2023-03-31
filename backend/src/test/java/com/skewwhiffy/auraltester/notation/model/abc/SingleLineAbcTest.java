package com.skewwhiffy.auraltester.notation.model.abc;

import com.skewwhiffy.auraltester.notation.factory.ClefFactory;
import com.skewwhiffy.auraltester.notation.factory.NoteFactory;
import com.skewwhiffy.auraltester.notation.model.note.AbsoluteNote;
import com.skewwhiffy.auraltester.notation.model.clef.Clef;
import com.skewwhiffy.auraltester.notation.model.key.Key;
import com.skewwhiffy.auraltester.notation.model.note.NoteLength;
import com.skewwhiffy.auraltester.test.util.TestData;
import lombok.val;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;
import java.util.stream.IntStream;

class SingleLineAbcTest {
  private String title;
  private Clef clef;
  private NoteLength noteLength;
  private List<AbsoluteNote> notes;
  private List<String> notesAbc;

  @BeforeEach
    void setUp() {
    val noteFactory = new NoteFactory();
    val clefFactory = new ClefFactory(noteFactory);
    title = TestData.random().string();
    clef = TestData.random().oneOf(
            clefFactory.getTreble(),
            clefFactory.getAlto(),
            clefFactory.getTenor(),
            clefFactory.getBass()
    );
    noteLength = TestData.random().oneOf(
      NoteLength.getBreve(),
      NoteLength.getSemibreve(),
      NoteLength.getMinim(),
      NoteLength.getCrotchet(),
      NoteLength.getQuaver()
    );
    notes = IntStream.range(1, 10).mapToObj(it -> TestData.random().absoluteNote()).toList();
    notesAbc = notes.stream().map(it -> it.getAbc(Key.getCMajor())).toList();
  }

  @Test
    void populatesTitle() {
    val abc = new SingleLineAbc(title, clef, noteLength, Collections.singletonList(notes));

    assertThat(abc.getAbc()).contains("X:1");
    assertThat(abc.getAbc()).contains("T:" + title);
    assertThat(abc.getAbc()).contains("K:clef=" + clef.abc());
    assertThat(abc.getAbc()).contains("L:" + noteLength.getAbc());
    assertThat(abc.getAbc()).contains(String.join("", notesAbc));
  }

  @Test
    void notPopulateTitle() {
    val abc = new SingleLineAbc(clef, noteLength, Collections.singletonList(notes));

    assertThat(abc.getAbc()).doesNotContain("T:");
  }
}
