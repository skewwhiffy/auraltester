export type ClefType = 'treble' | 'alto' | 'tenor' | 'bass'

interface ClefObject {
    clefType: ClefType,
    displayName: string,
}

export const Clef: {[key: string]: ClefObject} = {
    treble: {
        clefType: 'treble',
        displayName: 'treble',
    },
    alto: {
        clefType: 'alto',
        displayName: 'alto',
    },
    tenor: {
        clefType: 'tenor',
        displayName: 'tenor',
    },
    bass: {
        clefType: 'bass',
        displayName: 'bass',
    },
}
