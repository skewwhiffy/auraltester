package com.skewwhiffy.auraltester.notation.factory

/*
import com.skewwhiffy.auraltester.notation.model.key.Key
import lombok.`val`
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test

@ExtendWith(MockitoExtension::class)
class NoteFactoryTest {
    @InjectMocks
    private val noteFactory: NoteFactory? = null
    @Test
    fun instantiatesMiddleC() {
        val expected: `val` = AbsoluteNote.getMiddleC()
        val actual: `val` = noteFactory!!.getAbsoluteNote("C")
        Assertions.assertThat<`val`>(actual).isEqualTo(expected)
    }

    @Test
    fun instantiatesNoteAboveMiddleC() {
        val expected: `val` = "c''"
        val actual: `val` = noteFactory!!.getAbsoluteNote(expected)
        assertThat(actual.getAbc(Key.getCMajor())).isEqualTo(expected)
    }

    @Test
    fun instantiatesNoteBelowMiddleC() {
        val internalNotation: `val` = "Dx#,,,"
        val expected: `val` = "^^^D,,,"
        val actual: `val` = noteFactory!!.getAbsoluteNote(internalNotation)
        assertThat(actual.getAbc(Key.getCMajor())).isEqualTo(expected)
    }

    @Test
    fun when_noteNameInvalid_then_throws() {
        Assertions.assertThatThrownBy(ThrowingCallable { noteFactory!!.getAbsoluteNote("H") })
            .isInstanceOf(IllegalArgumentException::class.java)
    }
}


 */