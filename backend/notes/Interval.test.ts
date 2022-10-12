import Interval from './Interval'

describe('Note class', () => {
  describe('major', () => {
    it('can instantiate major second', () => {
      const interval = Interval.Major(2)

      expect(interval.displayString).toEqual('major second')
    })
  })
})