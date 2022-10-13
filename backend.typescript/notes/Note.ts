import Accidental from './Accidental'

class Note {
  readonly noteName: String
  readonly accidental: Accidental

  constructor(noteName: String, accidental = Accidental.NATURAL) {
    this.noteName = noteName
    this.accidental = accidental
  }

  get displayString() {
    return `${this.noteName}${this.accidental.displayString}`
  }

  get sharp() {
    return new Note(this.noteName, this.accidental.sharp)
  }

  get flat() {
    return new Note(this.noteName, this.accidental.flat)
  }

}

const A = new Note('A')
const B = new Note('B')
const C = new Note('C')
const D = new Note('D')
const E = new Note('E')
const F = new Note('F')
const G = new Note('G')

export default { A, B, C, D, E, F, G }
