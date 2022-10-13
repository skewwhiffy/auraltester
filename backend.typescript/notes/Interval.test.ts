import Interval from './Interval'

const majorIntervalsMap: { [key: string]: number } = {
  second: 2,
  third: 3,
  sixth: 6,
  seventh: 7
}

const perfectIntervalsMap: { [key: string]: number } = {
  unison: 1,
  fourth: 4,
  fifth: 5,
  octave: 8
}

describe('Interval class', () => {
  describe('non-perfect', () => {
    it('can instantiate major second', () => {
      const expected = 'major second'

      const actual = Interval.Major(2)

      expect(actual.displayString).toEqual(expected)
    })
    
    it('can diminish major third to minor third', () => {
      const major = Interval.Major(3)
      const expected = 'minor third'

      const actual = major.diminished
      
      expect(actual.displayString).toEqual(expected)
    })
    
    it('can instantiate minor sixth', () => {
      const expected = 'minor sixth'

      const actual = Interval.Minor(6)

      expect(actual.displayString).toEqual(expected)
    })
    
    it('can diminish minor seventh to diminished seventh', () => {
      const expected = 'diminished seventh'
      const minor = Interval.Minor(7)

      const actual = minor.diminished
      
      expect(actual.displayString).toEqual(expected)
    })
    
    it('can diminish diminished second to doubly diminished second', () => {
      const expected = 'doubly diminished second'
      const diminished = Interval.Minor(2).diminished
      
      const actual = diminished.diminished
      
      expect(actual.displayString).toEqual(expected)
    })
    
    it('can diminish doubly diminished third to 3x diminished third', () => {
      const expected = '3x diminished third'
      const doublyDiminished = Interval.Minor(3).diminished.diminished
      
      const actual = doublyDiminished.diminished
      
      expect(actual.displayString).toEqual(expected)
    })
    
    it('can augment major sixth to augmented sixth', () => {
      const expected = 'augmented sixth'
      const major = Interval.Major(6)

      const actual = major.augmented

      expect(actual.displayString).toEqual(expected)
    })
    
    Object.keys(perfectIntervalsMap).forEach(degreeString => {
      it(`cannot instantiate major ${degreeString}`, () => {
        const degree = perfectIntervalsMap[degreeString]

        expect(() => Interval.Major(degree)).toThrow()
      })
    })
  })

  describe('perfect', () => {
    it('can instantiate perfect unison', () => {
      const expected = 'perfect unison'
      
      const actual = Interval.Perfect(1)

      expect(actual.displayString).toEqual(expected)
    })
    
    it('can diminish perfect fourth to diminished fourth', () => {
      const expected = 'diminished fourth'
      const perfect = Interval.Perfect(4)

      const actual = perfect.diminished 
      
      expect(actual.displayString).toEqual(expected)
    })
    
    it('can diminish diminished fifth to doubly diminished fifth', () => {
      const expected = 'doubly diminished fifth'
      const diminished = Interval.Perfect(5).diminished

      const actual = diminished.diminished
      
      expect(actual.displayString).toEqual(expected)
    })
    
    it('can diminish doubly diminished octave to 3x diminished octave', () => {
      const expected = '3x diminished octave'
      const doublyDiminished = Interval.Perfect(8).diminished.diminished

      const actual = doublyDiminished.diminished
      
      expect(actual.displayString).toEqual(expected)
    })
    
    it('can augment perfect unison to augmented unison', () => {
      const expected = 'augmented unison'
      const perfect = Interval.Perfect(1)

      const actual = perfect.augmented
      
      expect(actual.displayString).toEqual(expected)
    })

    Object.keys(majorIntervalsMap).forEach(degreeString => {
      it(`cannot instantiate perfect ${degreeString}`, () => {
        const degree = majorIntervalsMap[degreeString]

        expect(() => Interval.Perfect(degree)).toThrow()
      })
    })
  })
  
  it('can augment augmented second to doubly augmented second', () => {
    const expected = 'doubly augmented second'
    const augmented = Interval.Major(2).augmented

    const actual = augmented.augmented
    
    expect(actual.displayString).toEqual(expected)
  })
  
  it('can augment doubly augmented fourth to 3x augmented fourth', () => {
    const expected = '3x augmented fourth'
    const doublyAugmented = Interval.Perfect(4).augmented.augmented
    
    const actual = doublyAugmented.augmented
    
    expect(actual.displayString).toEqual(expected)
  })
  
})