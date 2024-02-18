import {AbsoluteNote, absoluteNoteFactory} from "./absolute.note";

export type ClefType = 'treble' | 'alto' | 'tenor' | 'bass'

interface ClefObject {
    readonly abc: string,
    readonly clefType: ClefType,
    readonly displayName: string,
    readonly lowLedgerNote: AbsoluteNote,
    readonly highLedgerNote: AbsoluteNote,
}

interface ClefParameters {
    readonly abc: string,
    readonly clefType: ClefType,
    readonly lowLedgerNote: AbsoluteNote,
    readonly highLedgerNote: AbsoluteNote,
}

class ClefImpl implements ClefObject {
    readonly abc: string;
    readonly clefType: ClefType;
    readonly lowLedgerNote: AbsoluteNote;
    readonly highLedgerNote: AbsoluteNote;

    constructor({abc, clefType, lowLedgerNote, highLedgerNote}: ClefParameters) {
        this.abc = abc;
        this.clefType = clefType;
        this.lowLedgerNote = lowLedgerNote;
        this.highLedgerNote = highLedgerNote;
    }

    get displayName() {
        return ''
    }
}

export const clefFactory: { [key in ClefType]: ClefObject } = {
    treble: new ClefImpl({
        clefType: 'treble',
        abc: 'treble',
        lowLedgerNote: absoluteNoteFactory.middleC,
        highLedgerNote: absoluteNoteFactory.get('a')
    }),
    alto: new ClefImpl({
        clefType: 'alto',
        abc: 'alto',
        lowLedgerNote: absoluteNoteFactory.get('D,'),
        highLedgerNote: absoluteNoteFactory.get('B')
    }),
    tenor: new ClefImpl({
        clefType: 'tenor',
        abc: 'tenor',
        lowLedgerNote: absoluteNoteFactory.get('B,,'),
        highLedgerNote: absoluteNoteFactory.get('G')
    }),
    bass: new ClefImpl({
        clefType: 'bass',
        abc: 'bass',
        lowLedgerNote: absoluteNoteFactory.get('E,,'),
        highLedgerNote: absoluteNoteFactory.middleC
    }),
}
