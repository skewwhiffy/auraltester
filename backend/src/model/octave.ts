export interface Octave {
    readonly offset: number
    readonly up: Octave
    readonly down: Octave
    equals(other: Octave): boolean
}

class OctaveImpl implements Octave {
    readonly offset: number

    constructor(offset: number) {
        this.offset = offset;
    }

    get up() {
        return new OctaveImpl(this.offset + 1);
    }

    get down() {
        return new OctaveImpl(this.offset - 1);
    }

    equals(other: Octave) {
        return this.offset === other.offset;
    }
}

export const octaveFactory = {
    default: new OctaveImpl(0) as Octave,
    get: (offset: number): Octave => {
        return new OctaveImpl(offset);
    }
}