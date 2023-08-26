package com.skewwhiffy.auraltester.notation.factory

/*
import com.skewwhiffy.auraltester.test.util.TestData
import lombok.`val`
import org.junit.jupiter.api.Test

@ExtendWith(MockitoExtension::class)
internal class KeyFactoryTest {
    @Mock
    private val noteFactory: NoteFactory? = null

    @InjectMocks
    private val keyFactory: KeyFactory? = null
    @Test
    fun instantiatesMajorKey() {
        val rawKey: `val` = "a#"
        val expectedNote: `val` = TestData.random().note()
        `when`(noteFactory!!.getNote("A#")).thenReturn(expectedNote)
        val actual: `val` = keyFactory.getKey(rawKey)
        assertThat(actual.note()).isEqualTo(expectedNote)
        assertThat(actual.isMinor()).isFalse()
    }

    @Test
    fun recognizesMSuffixForMinor() {
        val rawKey: `val` = "abm"
        val expectedNote: `val` = TestData.random().note()
        `when`(noteFactory!!.getNote("Ab")).thenReturn(expectedNote)
        val actual: `val` = keyFactory.getKey(rawKey)
        assertThat(actual.note()).isEqualTo(expectedNote)
        assertThat(actual.isMinor()).isTrue()
    }

    @Test
    fun recognizesMinorSuffixForMinor() {
        val rawKey: `val` = "bx minor"
        val expectedNote: `val` = TestData.random().note()
        `when`(noteFactory!!.getNote("Bx")).thenReturn(expectedNote)
        val actual: `val` = keyFactory.getKey(rawKey)
        assertThat(actual.note()).isEqualTo(expectedNote)
        assertThat(actual.isMinor()).isTrue()
    }
}

 */