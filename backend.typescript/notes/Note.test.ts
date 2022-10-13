import Note from './Note'

describe('Note class', () => {
  it('can be instantiated', () => {
    const original = Note.A

    expect(original.displayString).toEqual('A')
  })

  describe('flat', () => {
    it('can flatten a natural', () => {
      const original = Note.B

      const result = original.flat

      expect(result.displayString).toEqual('Bb')
    })

    it('can flatten a sharp', () => {
      const original = Note.C.sharp

      const result = original.flat

      expect(result.displayString).toEqual('C')
    })

    it('can flatten a double sharp', () => {
      const original = Note.C.sharp.sharp

      const result = original.flat
      
      expect(result.displayString).toEqual('C#')
    })

    it('can flatten a flat', () => {
      const original = Note.C.flat

      const result = original.flat

      expect(result.displayString).toEqual('Cbb')
    })

    it('can flatten a double flat', () => {
      const original = Note.D.flat.flat

      const result = original.flat
      
      expect(result.displayString).toEqual('Dbbb')
    })

  })

  describe('sharp', () => {
    it('can sharpen a natural', () => {
      const original = Note.B

      const result = original.sharp

      expect(result.displayString).toEqual('B#')
    })

    it('can sharpen a sharp', () => {
      const original = Note.C.sharp

      const result = original.sharp

      expect(result.displayString).toEqual('Cx')
    })

    it('can sharpen a double sharp', () => {
      const original = Note.C.sharp.sharp

      const result = original.sharp
      
      expect(result.displayString).toEqual('Cx#')
    })

    it('can sharpen a flat', () => {
      const original = Note.C.flat

      const result = original.sharp

      expect(result.displayString).toEqual('C')
    })

    it('can sharpen a double flat', () => {
      const original = Note.D.flat.flat

      const result = original.sharp

      expect(result.displayString).toEqual('Db')
    })
  })

})
