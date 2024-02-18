import {Accidental, accidentalFactory} from "./accidental";

type SmallNoteName = 'a' | 'b' | 'c' | 'd' | 'e' | 'f' | 'g'
type NoteName = 'A' | 'B' | 'C' | 'D' | 'E' | 'F' | 'G'

export interface Note {
    readonly noteName: NoteName,
    readonly accidental: Accidental,
    readonly displayString: string
    readonly sharp: Note
    readonly flat: Note
    equals(other: Note): boolean;
}

interface NoteImplParameters {
    readonly noteName: NoteName;
    readonly accidental: Accidental;
}

class NoteImpl implements Note {
    readonly noteName: NoteName;
    readonly accidental: Accidental;

    constructor({noteName, accidental}: NoteImplParameters) {
        this.noteName = noteName;
        this.accidental = accidental
    }

    get displayString() {
        return `${this.noteName}${this.accidental.displayString}`
    }

    get sharp() {
        return new NoteImpl({...this, accidental: this.accidental.sharp})
    }

    get flat() {
        return new NoteImpl({...this, accidental: this.accidental.flat})
    }

    equals(other: Note) {
        return this.noteName === other.noteName && this.accidental.equals(other.accidental);
    }
}

export const noteFactory: { [key in SmallNoteName]: Note } = {
    a: new NoteImpl({noteName: 'A', accidental: accidentalFactory.natural}),
    b: new NoteImpl({noteName: 'B', accidental: accidentalFactory.natural}),
    c: new NoteImpl({noteName: 'C', accidental: accidentalFactory.natural}),
    d: new NoteImpl({noteName: 'D', accidental: accidentalFactory.natural}),
    e: new NoteImpl({noteName: 'E', accidental: accidentalFactory.natural}),
    f: new NoteImpl({noteName: 'F', accidental: accidentalFactory.natural}),
    g: new NoteImpl({noteName: 'G', accidental: accidentalFactory.natural}),
}