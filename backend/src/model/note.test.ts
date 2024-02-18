import {noteFactory} from './note';

describe('hello', () => {
    it('returns display string when natural', () => {
        const note = noteFactory.a;

        const actual = note.displayString

        expect(actual).toEqual('A')
    })

    it('returns display string when sharp', () => {
        const note = noteFactory.b.sharp;

        const actual = note.displayString;

        expect(actual).toEqual('B#')
    })

    it('returns display string when flat', () => {
        const note = noteFactory.d.flat;

        const actual = note.displayString;

        expect(actual).toEqual('Db');
    })

    it('equates equivalent notes', () => {
        const first = noteFactory.a.sharp;
        const second = noteFactory.a.sharp;

        expect(first.equals(second)).toEqual(true);
    })
})