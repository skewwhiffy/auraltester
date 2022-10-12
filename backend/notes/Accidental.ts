class Accidental {
  private readonly offset: number

  private constructor(offset: number) {
    this.offset = offset
  }

  get displayString() {
    if (this.offset === 0) {
      return ''
    }
    if (this.offset < 0) {
      return 'b'.repeat(-this.offset)
    }
    if (this.offset % 2 === 0) {
      return 'x'.repeat(this.offset / 2)
    }
    return 'x'.repeat((this.offset - 1) / 2) + '#'
  }

  get sharp() {
    return new Accidental(this.offset + 1)
  }
  
  get flat() {
    return new Accidental(this.offset - 1)
  }

  static NATURAL = new Accidental(0)
  static SHARP = new Accidental(1)
  static DOUBLE_SHARP = new Accidental(2)
  static FLAT = new Accidental(-1)
  static DOUBLE_FLAT = new Accidental(-2)

}

export default Accidental