import accidentalFactory from "./accidentalFactory";
import chai from 'chai'

chai.should();

describe('accidental', () => {
    it('has correct abc when natural', () => {
        const natural = accidentalFactory.natural;

        const actual = natural.abc;

        actual.should.equal('')
    })

    it('has correct abc when flat', () => {
        const flat = accidentalFactory.flat;

        const actual = flat.abc;

        actual.should.equal('_');
    })

    it('has correct abc when sharp', () => {
        const sharp = accidentalFactory.sharp;

        const actual = sharp.abc;

        actual.should.equal('^');
    })

    it('displays correctly when natural', () => {
        const natural = accidentalFactory.natural;

        const actual = natural.displayString;

        actual.should.equal('');
    })

    it('when flattening natural then is flat', () => {
        const natural = accidentalFactory.natural;

        const actual = natural.flatten

        actual.equals(accidentalFactory.flat).should.be.true
    })

    it('when sharpening natural then is sharp', () => {
        const natural = accidentalFactory.natural;

        const actual = natural.sharpen

        actual.equals(accidentalFactory.sharp).should.be.true;
    })

    it('when flat then displays flat', () => {
        const flat = accidentalFactory.flat;

        const actual = flat.displayString;

        actual.should.equal('b');
    })

    it('when flattening flat then displays double flat', () => {
        const flat = accidentalFactory.flat;

        const actual = flat.flatten;

        actual.displayString.should.equal('bb');
    })

    it('when sharpening flat then is natural', () => {
        const flat = accidentalFactory.flat;

        const actual = flat.sharpen;

        actual.equals(accidentalFactory.natural).should.be.true;
    });


    [3, 7].forEach(flats => {
        it(`displays correctly with ${flats} flats`, () => {
            const expected = 'b'.repeat(flats);
            const accidental = [...new Array(flats)].reduce((prev) => prev.flatten, accidentalFactory.natural);

            const actual = accidental.displayString;

            actual.should.equal(expected);
        })
    })

    it('when sharp displays sharp', () => {
        const sharp = accidentalFactory.sharp;

        const actual = sharp.displayString;

        actual.should.equal('#');
    })

    it('when sharpening sharp then displays double sharp', () => {
        const sharp = accidentalFactory.sharp;

        const actual = sharp.sharpen.displayString;

        actual.should.equal('x');
    })

    it('when flattening sharp then is natural', () => {
        const sharp = accidentalFactory.sharp;

        const actual = sharp.flatten;

        actual.equals(accidentalFactory.natural).should.be.true;
    });

    [6, 10].forEach(sharps => {
        it(`displays sharps correctly for ${sharps} sharps`, () => {
            const expected = 'x'.repeat(sharps / 2);
            const accidental = [...new Array(sharps)].reduce(prev => prev.sharpen, accidentalFactory.natural);

            const actual = accidental.displayString;

            actual.should.equal(expected);
        })
    });

    [7, 13].forEach(sharps => {
        it(`displays sharps correctly for ${sharps} sharps`, () => {
            const expected = 'x'.repeat((sharps - 1) / 2) + '#';
            const accidental = [...new Array(sharps)].reduce(prev => prev.sharpen, accidentalFactory.natural);

            const actual = accidental.displayString;

            actual.should.equal(expected);
        })
    })
});