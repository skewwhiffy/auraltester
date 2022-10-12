import Interval from './Interval'

describe('Note class', () => {
  describe('major', () => {
    const ordinals: { [key:string]: number} = {
      second: 2,
      third: 3,
      sixth: 6,
      seventh: 7
    }
    Object.keys(ordinals).forEach(degreeString => {
      it(`can instantiate major ${degreeString}`, () => {
        const degree: number = ordinals[degreeString]
        const interval = Interval.Major(degree)
        
        expect(interval.displayString).toEqual(`major ${degreeString}`)
      })
    })
  })
})