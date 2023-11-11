export type Clef = 'bass' | 'tenor' | 'alto' | 'treble'

export type IntervalQuality = 'diminished' | 'minor' | 'major' | 'perfect' | 'augmented'

export type IntervalSize = 1 | 2 | 3 | 4 | 5 | 6 | 7 | 8

export type Interval = {
    size: IntervalSize,
    quality: IntervalQuality
}

export type Note = {
    name: NoteName,
    accidental: Accidental
}

export type NoteName = 'A' | 'B' | 'C' | 'D' | 'E' | 'F' | 'G'
export type Accidental = 'x' | '#' | '' | 'b' | 'bb'