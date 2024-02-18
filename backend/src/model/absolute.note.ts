import {Note, noteFactory} from "./note";
import {Octave, octaveFactory} from "./octave";

export interface AbsoluteNote {
    readonly note: Note,
    readonly octave: Octave,
    readonly lyric?: string
}

export const absoluteNoteFactory = {
    middleC: {note: noteFactory.c, octave: octaveFactory.default},
    get: (source: string) => {
        return {
            note: noteFactory.c,
            octave: octaveFactory.default
        }
    }
}