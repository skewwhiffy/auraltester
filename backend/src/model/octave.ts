export interface Octave {
    readonly offsetFromDefault: number
}

export const octaveFactory: { [key: string]: Octave } = {
    default: {offsetFromDefault: 0}
}