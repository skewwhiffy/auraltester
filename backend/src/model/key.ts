import {Note, noteFactory} from "./note";

export interface Key {
    readonly note: Note,
    readonly isMinor: boolean
}

class KeyImpl {
    readonly note: Note;
    readonly isMinor: boolean;

    constructor(note: Note, isMinor: boolean) {
        this.note = note;
        this.isMinor = isMinor;
    }

    static major(note: Note) {
        return new KeyImpl(note, false)
    }
}

export const keyFactory = {
    cMajor: KeyImpl.major(noteFactory.c)
}