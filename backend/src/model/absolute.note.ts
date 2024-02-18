import {Note, noteFactory} from "./note";
import {Octave, octaveFactory} from "./octave";
import {Key} from "./key";

export interface AbsoluteNote {
    readonly note: Note,
    readonly octave: Octave,
    readonly lyric?: string

    getAbc(key: Key): string
}

interface AbsoluteNoteImplProperties {
    note: Note,
    octave: Octave
}

class AbsoluteNoteImpl implements AbsoluteNote {
    readonly note: Note;
    readonly octave: Octave;

    constructor({note, octave}: AbsoluteNoteImplProperties) {
        this.note = note;
        this.octave = octave;
    }

    getAbc(key: Key) {
        return 'C'
    }
}

export const absoluteNoteFactory = {
    middleC: new AbsoluteNoteImpl({note: noteFactory.c, octave: octaveFactory.default}) as AbsoluteNote,
    get(source: string): AbsoluteNote {
        return new AbsoluteNoteImpl({
            note: noteFactory.c,
            octave: octaveFactory.default
        })
    }
}