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
    });

    it('returns doubly diminished fifth when diminishing diminished fifth', () => {
        const expected = 'doubly diminished fifth';
        const diminished = intervalFactory.perfect(5).diminished;

        const actual = diminished.diminished;

        expect(actual.displayString).toBe(expected);
    })

    it('returns 3x diminished octave when diminishing doubly diminished octave', () => {
        const expected = '3x diminished octave';
        const doublyDiminished = intervalFactory.perfect(8).diminished.diminished;

        const actual = doublyDiminished.diminished;

        expect(actual.displayString).toBe(expected);
    })

    it('returns augmented unison when augmenting perfect unison', () => {
        const expected = 'augmented unison'
        const perfect = intervalFactory.perfect(1)

        const actual = perfect.augmented

        expect(actual.displayString).toBe(expected)
    });

    [2, 3, 6, 7].forEach(degree => {
        it(`does not instantiate perfect ${degree}`, () => {
            expect(() => intervalFactory.perfect(degree)).toThrowError();
        })
    })

    it('returns doubly augmented second when augmenting augmented second', () => {
        const expected = 'doubly augmented second'
        const augmented = intervalFactory.major(2).augmented

        const actual = augmented.augmented

        expect(actual.displayString).toBe(expected)
    })

    it('returns 3x augmented fourth when augmenting augmented fourth', () => {
        const expected = '3x augmented fourth';
        const doublyAugmented = intervalFactory.perfect(4).augmented.augmented;

        const actual = doublyAugmented.augmented;

        expect(actual.displayString).toBe(expected)       ;
    })

    it('returns equal when equivalent', () => {
        const first = intervalFactory.perfect(4);
        const second = intervalFactory.perfect(4);

        expect(first.equals(second)).toBe(true);
    })
})