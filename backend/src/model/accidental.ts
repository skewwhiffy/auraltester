export interface Accidental {
    readonly offset: number
    readonly abc: string,
    readonly displayString: string
    readonly flat: Accidental
    readonly sharp: Accidental
    equals: (other: Accidental) => boolean
}

export class AccidentalImpl implements Accidental {
    readonly offset: number

    constructor(offset: number) {
        this.offset = offset;
    }

    get abc() {
        if (this.offset < 0) {
            return '_'.repeat(-this.offset);
        }
        return '^'.repeat(this.offset);
    }

    get displayString() {
        if (this.offset < 0) {
            return "b".repeat(-this.offset);
        }
        if (this.offset % 2 == 0) {
            return "x".repeat(this.offset / 2);
        }
        return "x".repeat((this.offset - 1) / 2) + "#";
    }

    get flat(): Accidental {
        return new AccidentalImpl(this.offset - 1);
    }

    get sharp(): Accidental {
        return new AccidentalImpl(this.offset + 1);
    }

    equals(other: Accidental) {
        return other.offset === this.offset
    }
}

type AccidentalTypes = 'natural' | 'flat' | 'sharp'

type AccidentalTypeMap = {
    [key in AccidentalTypes]: Accidental
}

export const accidentalFactory: AccidentalTypeMap = {
    natural: new AccidentalImpl(0),
    flat: new AccidentalImpl(-1),
    sharp: new AccidentalImpl(1),
}
