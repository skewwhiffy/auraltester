const displayStrings = {
  NATURAL: '',
  SHARP: '#',
  DOUBLE_SHARP: 'x',
  FLAT: 'b',
  DOUBLE_FLAT: 'bb',
}

enum AccidentalEnum {
  NATURAL, SHARP, DOUBLE_SHARP, FLAT, DOUBLE_FLAT,
}

const ordered = [
  AccidentalEnum.DOUBLE_FLAT, AccidentalEnum.FLAT, AccidentalEnum.NATURAL, AccidentalEnum.SHARP, AccidentalEnum.DOUBLE_SHARP,
]

class Accidental {
  private readonly accidentalEnum: AccidentalEnum

  private constructor(accidentalEnum: AccidentalEnum) {
    this.accidentalEnum = accidentalEnum
  }

  get displayString() {
    switch (this.accidentalEnum) {
      case AccidentalEnum.NATURAL:
        return ''
      case AccidentalEnum.SHARP:
        return '#'
      case AccidentalEnum.DOUBLE_SHARP:
        return 'x'
      case AccidentalEnum.FLAT:
        return 'b'
      case AccidentalEnum.DOUBLE_FLAT:
        return 'bb'
      default:
        throw `I do not recognise '${this.accidentalEnum}'`
    }
  }

  get sharp() {
    const indexOfEnum = ordered.indexOf(this.accidentalEnum)
    const newEnum = ordered[indexOfEnum + 1]
    if (newEnum === undefined) {
      throw `Cannot sharpen '${this.displayString}'`
    }
    return new Accidental(newEnum)
  }
  
  get flat() {
    const indexOfEnum = ordered.indexOf(this.accidentalEnum)
    const newEnum = ordered[indexOfEnum - 1]
    if (newEnum === undefined) {
      throw `Cannot flatten '${this.displayString}'`
    }
    return new Accidental(newEnum)
  }

  static NATURAL = new Accidental(AccidentalEnum.NATURAL)
  static SHARP = new Accidental(AccidentalEnum.SHARP)
  static DOUBLE_SHARP = new Accidental(AccidentalEnum.DOUBLE_SHARP)
  static FLAT = new Accidental(AccidentalEnum.FLAT)
  static DOUBLE_FLAT = new Accidental(AccidentalEnum.DOUBLE_FLAT)

}

export default Accidental