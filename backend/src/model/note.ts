import {Accidental, accidentalFactory, AccidentalImpl} from "./accidental";

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

const getNoteLetter = (rawNote: String) => rawNote.substring(0, 1)

const getNoteName = (source: string): NoteName => {
    const noteName = getNoteLetter(source).toUpperCase()
    if (noteName < "A" || noteName > "G") {
        throw Error(`'${noteName}' is not a valid note name`)
    }
    return noteName as NoteName;
}

const getRawAccidentalFromAccidentalMarks = (accidentalMarks: string): string => {
    if (accidentalMarks.endsWith("'") || accidentalMarks.endsWith(","))
        return getRawAccidentalFromAccidentalMarks(accidentalMarks.substring(0, accidentalMarks.length - 1))
    return accidentalMarks
}
const getRawAccidental = (rawNote: string) => {
    const accidentalString = rawNote.substring(1)
    return getRawAccidentalFromAccidentalMarks(accidentalString);
}

const getAccidental = (rawNote: string): Accidental => {
    const getOffset = (rawNote: string): number => {
        switch (rawNote) {
            case "x":
                return 2
            case "#":
                return 1
            case "b":
                return -1
            default:
                throw Error(`Not valid accidental '${rawNote}'`)
        }
    }

    const offset = getRawAccidental(rawNote)
        .split('')
        .map(it => getOffset(it))
        .reduce((current, previous) => current + previous)
    return new AccidentalImpl(offset)
}

export const get = (source: string): Note => {
    return new NoteImpl({noteName: getNoteName(source), accidental: getAccidental(source)})
}

export const noteFactory: { [key in SmallNoteName]: Note } = {
    a: new NoteImpl({noteName: 'A', accidental: accidentalFactory.natural}),
    b: new NoteImpl({noteName: 'B', accidental: accidentalFactory.natural}),
    c: new NoteImpl({noteName: 'C', accidental: accidentalFactory.natural}),
    d: new NoteImpl({noteName: 'D', accidental: accidentalFactory.natural}),
    e: new NoteImpl({noteName: 'E', accidental: accidentalFactory.natural}),
    f: new NoteImpl({noteName: 'F', accidental: accidentalFactory.natural}),
    g: new NoteImpl({noteName: 'G', accidental: accidentalFactory.natural}),
    // fun getNote(rawNote: String) = Note(getNoteName(rawNote), getAccidental(rawNote))
}