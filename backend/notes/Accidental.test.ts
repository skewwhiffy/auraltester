import Accidental from "./Accidental"

describe('Accidental class', () => {
  describe('natural', () => {
    const natural = Accidental.NATURAL

    it('displays correctly', () => {
      const actual = natural.displayString

      expect(actual).toEqual('')
    })

    it('can be flattened', () => {
      const flat = natural.flat

      expect(flat.displayString).toEqual('b')
    })

    it('can be sharpened', () => {
      const sharp = natural.sharp

      expect(sharp.displayString).toEqual('#')
    })
  })

  describe('flat', () => {
    const flat = Accidental.FLAT

    it('displays correctly', () => {
      const actual = flat.displayString

      expect(actual).toEqual('b')
    })

    it('can be flattened', () => {
      const doubleFlat = flat.flat

      const actual = doubleFlat.displayString

      expect(actual).toEqual('bb')
    })

    it('can be sharpened', () => {
      const natural = flat.sharp

      const actual = natural.displayString

      expect(actual).toEqual('')
    })

    it('displays multiple flats correctly', () => {
      let expected = 'b'
      let current = flat
      Array(10).forEach(() => {
        expect(current.displayString).toEqual(expected)
        current = current.flat
        expected += 'b'
      })
    })
  })
  
  describe('sharp', () => {
    const sharp = Accidental.SHARP
    
    it('can be sharpened', () => {
      const doubleSharp = sharp.sharp
      
      expect(doubleSharp.displayString).toEqual('x')
    })
    
    it('can be flattened', () => {
      const natural = sharp.flat
      
      expect(natural.displayString).toEqual('')
    })
    
    it('displays even multiple sharps correctly', () => {
      let expected = 'x'
      let current = sharp.sharp
      for (let i = 0; i < 10; i++) {
        expect(current.displayString).toEqual(expected)
        expected += 'x'
        current = current.sharp.sharp
      }
    })
    
    it('displays odd multiple sharps correctly', () => {
      let expected = '#'
      let current = sharp
      for (let i = 0; i < 10; i++) {
        expect(current.displayString).toEqual(expected)
        expected = 'x' + expected
        current = current.sharp.sharp
      }
    })
  })
})