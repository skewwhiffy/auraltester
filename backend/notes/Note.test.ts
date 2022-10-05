import Accidental from './Accidental'
import Note from './Note'

describe('Note class', () => {
  it('can be instantiated', () => {
    const original = Note.A

    expect(original.accidental).toEqual(Accidental.NATURAL)
  })

  describe('flat', () => {
    it('can flatten a natural', () => {
      const original = Note.B

      const result = original.flat

      expect(result.accidental).toEqual(Accidental.FLAT)
    })

    it('can flatten a sharp', () => {
      const original = Note.C.sharp

      const result = original.flat

      expect(result.accidental).toEqual(Accidental.NATURAL)
    })

    it('can flatten a double sharp', () => {
      const original = Note.C.sharp.sharp

      const result = original.flat
      
      expect(result.accidental).toEqual(Accidental.SHARP)
    })

    it('can flatten a flat', () => {
      const original = Note.C.flat

      const result = original.flat

      expect(result.accidental).toEqual(Accidental.DOUBLE_FLAT)
    })

    it('cannot flatten a double flat', () => {
      const original = Note.D.flat.flat

      expect(() => original.flat).toThrow()
    })

  })

  describe('sharp', () => {
    it('can sharpen a natural', () => {
      const original = Note.B

      const result = original.sharp

      expect(result.accidental).toEqual(Accidental.SHARP)
    })

    it('can sharpen a sharp', () => {
      const original = Note.C.sharp

      const result = original.sharp

      expect(result.accidental).toEqual(Accidental.DOUBLE_SHARP)
    })

    it('cannot sharpen a double sharp', () => {
      const original = Note.C.sharp.sharp

      expect(() => original.sharp).toThrow()
    })

    it('can sharpen a flat', () => {
      const original = Note.C.flat

      const result = original.sharp

      expect(result.accidental).toEqual(Accidental.NATURAL)
    })

    it('can sharpen a double flat', () => {
      const original = Note.D.flat.flat

      const result = original.sharp

      expect(result.accidental).toEqual(Accidental.FLAT)
    })
  })

})
