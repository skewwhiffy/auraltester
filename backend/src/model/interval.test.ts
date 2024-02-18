import {intervalFactory} from "./interval";

describe('interval', () => {
    it('instantiates major second', () => {
        const expected = 'major second';

        const actual = intervalFactory.major(2);

        expect(actual.displayString).toBe(expected);
    })

    it('returns minor third when diminishing major third', () => {
        const expected = 'minor third';
        const major = intervalFactory.major(3);

        const actual = major.diminished

        expect(actual.displayString).toBe(expected);
    })
    /*
    @Test
    fun returnsMinorThirdWhenDiminishingMajorThird() {
        val expected = "minor third"
        val major = Interval.major(3)
        val actual = major.diminished
        assertThat(actual.displayString).isEqualTo(expected)
    }

    @Test
    fun instantiatesMinorSixth() {
        val expected = "minor sixth"
        val actual = Interval.minor(6)
        assertThat(actual.displayString).isEqualTo(expected)
    }

    @Test
    fun returnsDiminishedSeventhWhenDiminishingMinorSeventh() {
        val expected = "diminished seventh"
        val minor = Interval.minor(7)
        val actual = minor.diminished
        assertThat(actual.displayString).isEqualTo(expected)
    }

    @Test
    fun returnsDoublyDiminishedSecondWhenDiminishingDiminishedSecond() {
        val expected = "doubly diminished second"
        val diminished = Interval.minor(2).diminished
        val actual = diminished.diminished
        assertThat(actual.displayString).isEqualTo(expected)
    }

    @Test
    fun returns3xDiminishedThirdWhenDiminishingDoublyDiminishedThird() {
        val expected = "3x diminished third"
        val doublyDiminished = Interval.minor(3).diminished.diminished
        val actual = doublyDiminished.diminished
        assertThat(actual.displayString).isEqualTo(expected)
    }

    @Test
    fun returnsAugmentedSixthWhenAugmentingMajorSixth() {
        val expected = "augmented sixth"
        val major = Interval.major(6)
        val actual = major.augmented
        assertThat(actual.displayString).isEqualTo(expected)
    }

    @ParameterizedTest
    @ValueSource(ints = [1, 4, 5, 8])
    fun doesNotInstantiateMajorInterval(degree: Int) {
        assertThatThrownBy { Interval.major(degree) }.isInstanceOf(
            IllegalArgumentException::class.java
        )
    }

    @Test
    fun instantiatesPerfectUnison() {
        val expected = "perfect unison"
        val actual = Interval.perfect(1)
        assertThat(actual.displayString).isEqualTo(expected)
    }

    @Test
    fun returnsDiminishedFourthWhenDiminishingPerfectFourth() {
        val expected = "diminished fourth"
        val perfect = Interval.perfect(4)
        val actual = perfect.diminished
        assertThat(actual.displayString).isEqualTo(expected)
    }

    @Test
    fun returnsDoublyDiminishedFifthWhenDiminishingDiminishedFifth() {
        val expected = "doubly diminished fifth"
        val diminished = Interval.perfect(5).diminished
        val actual = diminished.diminished
        assertThat(actual.displayString).isEqualTo(expected)
    }

    @Test
    fun returns3xDiminishedOctaveWhenDiminishingDoublyDiminishedOctave() {
        val expected = "3x diminished octave"
        val doublyDiminished = Interval.perfect(8).diminished.diminished
        val actual = doublyDiminished.diminished
        assertThat(actual.displayString).isEqualTo(expected)
    }

    @Test
    fun returnsAugmentedUnisonWhenAugmentingPerfectUnison() {
        val expected = "augmented unison"
        val perfect = Interval.perfect(1)
        val actual = perfect.augmented
        assertThat(actual.displayString).isEqualTo(expected)
    }

    @ParameterizedTest
    @ValueSource(ints = [2, 3, 6, 7])
    fun doesNotInstantiatePerfectInterval(degree: Int) {
        assertThatThrownBy { Interval.perfect(degree) }.isInstanceOf(
            IllegalArgumentException::class.java
        )
    }

    @Test
    fun returnsDoublyAugmentedSecondWhenAugmentingAugmentedSecond() {
        val expected = "doubly augmented second"
        val augmented = Interval.major(2).augmented
        val actual = augmented.augmented
        assertThat(actual.displayString).isEqualTo(expected)
    }

    @Test
    fun returns3xAugmentedFourthWhenAugmentingAugmentedFourth() {
        val expected = "3x augmented fourth"
        val doublyAugmented = Interval.perfect(4).augmented.augmented
        val actual = doublyAugmented.augmented
        assertThat(actual.displayString).isEqualTo(expected)
    }

    @Test
    fun returnsEqualWhenEquivalent() {
        val getInterval = { Interval(6, 69) }
        val first = getInterval()
        val second = getInterval()
        assertThat(first).isEqualTo(second)
    }
     */
})