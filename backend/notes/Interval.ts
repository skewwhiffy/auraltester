import Accidental from './Accidental'

class Interval {
  get displayString() {
    return 'major second'
  }

}

function Major(degree: number) {
  return new Interval()
}

function Minor(degree: number) {
  return new Interval()
}

export default {
  Major,
  Minor,
}
