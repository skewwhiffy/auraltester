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

describe('Note class', () => {
  describe('major', () => {
    Object.keys(majorIntervalsMap).forEach(degreeString => {
      it(`can instantiate major ${degreeString}`, () => {
        const degree = majorIntervalsMap[degreeString]
        const interval = Interval.Major(degree)

        expect(interval.displayString).toEqual(`major ${degreeString}`)
      })
    })
    
    Object.keys(perfectIntervalsMap).forEach(degreeString => {
      it(`cannot instantiate major ${degreeString}`, () => {
        const degree = perfectIntervalsMap[degreeString]
        
        expect(() => Interval.Major(degree)).toThrow()
      })
    })
  })
  
  describe('perfect', () => {
    Object.keys(perfectIntervalsMap).forEach(degreeString => {
      it(`can instantiate perfect ${degreeString}`, () => {
        const degree = perfectIntervalsMap[degreeString]
        const interval = Interval.Perfect(degree)
        
        expect(interval.displayString).toEqual(`perfect ${degreeString}`)
      })
    })
    
    Object.keys(majorIntervalsMap).forEach(degreeString => {
      it(`cannot instantiate perfect ${degreeString}`, () => {
        const degree = majorIntervalsMap[degreeString]

        expect(() => Interval.Perfect(degree)).toThrow()
      })
    })
  })
})