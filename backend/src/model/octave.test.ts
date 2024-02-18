import {octaveFactory} from "./octave";

describe('octave', () => {
    it('returns higher octave when up is called', () => {
        const start = octaveFactory.get(70);

        const actual = start.up;

        expect(actual.offset).toEqual(start.offset + 1);
    })

    it('returns lower octave when down is called', () => {
        const start = octaveFactory.get(81);

        const actual = start.down;

        expect(actual.offset).toEqual(start.offset - 1);
    })

    it('equates same octaves', () => {
        const first = octaveFactory.get(52);
        const second = octaveFactory.get(52);

        expect(first.equals(second)).toBe(true);
    })
})
