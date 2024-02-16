interface Accidental {
    readonly offset: number
    readonly abc: string,
    readonly displayString: string
    readonly flatten: Accidental
    equals: (other: Accidental) => boolean
}

class AccidentalImpl implements Accidental {
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
        return ''
    }

    get flatten(): Accidental {
        return new AccidentalImpl(this.offset - 1);
    }

    equals(other: Accidental) {
        return other.offset === this.offset
    }
}

type AccidentalTypes = 'natural' | 'flat' | 'sharp'

type AccidentalTypeMap = {
    [key in AccidentalTypes]: Accidental
}

const accidental: AccidentalTypeMap = {
    natural: new AccidentalImpl(0),
    flat: new AccidentalImpl(-1),
    sharp: new AccidentalImpl(1),
}

export default accidental;