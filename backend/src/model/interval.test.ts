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

    it('instantiates minor sixth', () => {
        const expected = 'minor sixth';

        const actual = intervalFactory.minor(6);

        expect(actual.displayString).toBe(expected);
    })

    it('returns diminished seventh when diminishing minor seventh', () => {
        const expected = 'diminished seventh';
        const minor = intervalFactory.minor(7);

        const actual = minor.diminished;

        expect(actual.displayString).toBe(expected);
    })

    it('returns doubly diminished second when diminishing diminished second', () => {
        const expected = 'doubly diminished second';
        const diminished = intervalFactory.minor(2).diminished;

        const actual = diminished.diminished;

        expect(actual.displayString).toBe(expected);
    });

    it('returns 3x diminished third when diminishing double diminished third', () => {
        const expected = '3x diminished third';
        const doublyDiminished = intervalFactory.minor(3).diminished.diminished;

        const actual = doublyDiminished.diminished

        expect(actual.displayString).toBe(expected);
    });

    it('returns augmented sixth when augmenting major sixth', () => {
        const expected = 'augmented sixth';
        const major = intervalFactory.major(6)

        const actual = major.augmented;

        expect(actual.displayString).toBe(expected);
    });

    [1, 4, 5, 8].forEach(degree => {
        it(`does not instantiate major ${degree} interval`, () => {
            expect(() => intervalFactory.major(degree)).toThrowError();
        })
    })

    it('instantiated perfect unison', () => {
        const expected = 'perfect unison';

        const actual = intervalFactory.perfect(1);

        expect(actual.displayString).toBe(expected);
    });

    it('returns diminished fourth when diminishing perfect fourth', () => {
        const expected = 'diminished fourth';
        const perfect = intervalFactory.perfect(4);

        const actual = perfect.diminished;

        expect(actual.displayString).toBe(expected);
    })
    /*
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