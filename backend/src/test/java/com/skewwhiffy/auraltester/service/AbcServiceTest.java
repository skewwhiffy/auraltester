package com.skewwhiffy.auraltester.service;

import com.skewwhiffy.auraltester.helper.StringHelper;
import com.skewwhiffy.auraltester.notation.model.abc.SingleLineAbc;
import com.skewwhiffy.auraltester.notation.model.note.NoteLength;
import com.skewwhiffy.auraltester.notation.model.note.NoteSequence;
import com.skewwhiffy.auraltester.test.util.TestData;
import lombok.val;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.text.MessageFormat;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
class AbcServiceTest {
    @InjectMocks
    private AbcService abcService;

    @Test
    void getsCorrectAbcForScale() {
        val clef = TestData.random().clef();
        val scale = TestData.random().scale();
        val expected = new SingleLineAbc(
                StringHelper.getTitleCase(
                        MessageFormat.format(
                                "{0} {1}",
                                scale.getDisplayName(),
                                scale.direction().getDisplayString()
                        )
                ),
                clef,
                NoteLength.getSemibreve(),
                List.of(scale.getNotes())
        );

        val actual = abcService.getAbc(clef, scale);

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void getsCorrectAbcForScaleWithKeySignature() {
        val clef = TestData.random().clef();
        val scale = TestData.random().scale();
        val key = TestData.random().key();
        val expected = new SingleLineAbc(
                MessageFormat.format(
                        "{0} {1} {2}",
                        scale.getDisplayName(),
                        scale.direction().getDisplayString(),
                        "(with key signature)"
                ),
                clef,
                NoteLength.getSemibreve(),
                key,
                List.of(scale.getNotes())
        );

        val actual = abcService.getAbc(clef, scale, key);

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void getsCorrectAbcForClef() {
        val clef = TestData.random().clef();
        val expected = new SingleLineAbc(
                clef.getDisplayName() + " Clef Notes",
                clef,
                NoteLength.getSemibreve(),
                clef.getNotes().stream().map(NoteSequence::getNotes).toList()
        );

        val actual = abcService.getAbc(clef);

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void getsCorrectAbcForInterval() {
        val clef = TestData.random().clef();
        val intervalNotes = TestData.random().intervalNotes();
        val key = TestData.random().key();
        val expected = new SingleLineAbc(
                StringHelper.getTitleCase(intervalNotes.interval().getDisplayString()),
                clef,
                NoteLength.getSemibreve(),
                key,
                List.of(intervalNotes.getNotes())
        );

        val actual = abcService.getAbc(clef, intervalNotes, key);

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void getsCorrectAbcForKey() {
        val clef = TestData.random().clef();
        val key = TestData.random().key();
        val expected = new SingleLineAbc(
                StringHelper.getTitleCase(key.getDisplayString(), "/", key.getRelative().getDisplayString()),
                clef,
                NoteLength.getSemibreve(),
                key,
                List.of(List.of())
        );

        val actual = abcService.getAbc(clef, key);

        assertThat(actual).isEqualTo(expected);
    }
}

