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
  
  static A = new Note('A')
  static B = new Note('B')
  static C = new Note('C')
  static D = new Note('D')
  static E = new Note('E')
  static F = new Note('F')
  static G = new Note('G')

}

export default Note
