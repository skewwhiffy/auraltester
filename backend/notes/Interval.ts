const displayStrings = [
  'unison',
  'second',
  'third',
  'fourth',
  'fifth',
  'sixth',
  'seventh',
  'octave'
]

const perfectDegrees = [1, 4, 5, 8]

class Interval {
  private readonly degree: number
  private readonly deviation: number

  constructor(degree: number, deviation: number = 0) {
    this.degree = degree
    this.deviation = deviation
  }
  
  get diminished() {
    return new Interval(this.degree, this.deviation - 1)
  }
  
  get augmented() {
    return new Interval(this.degree, this.deviation + 1)
  }

  get displayString() {
    return `${this.quality} ${displayStrings[this.degree - 1]}`
  }
  
  get quality() {
    if (this.deviation === 0) {
      return perfectDegrees.includes(this.degree) ? 'perfect' : 'major'
    }
    if (this.deviation === - 1 && !perfectDegrees.includes(this.degree)) {
      return 'minor'
    }
    if (this.deviation < 0) {
      const diminishedDegree = perfectDegrees.includes(this.degree) ? -this.deviation : -this.deviation - 1
      if (diminishedDegree === 1) {
        return 'diminished'
      }
      if (diminishedDegree === 2) {
        return 'doubly diminished'
      }
      return `${diminishedDegree}x diminished`
    }
    if (this.deviation === 1) {
      return 'augmented'
    }
    if (this.deviation === 2) {
      return 'doubly augmented'
    }
    return `${this.deviation}x augmented`
  }

}

function Major(degree: number) {
  if (![2, 3, 6, 7].includes(degree)) {
    throw `Cannot instantiate major interval of degree ${degree}`
  }
  return new Interval(degree)
}

function Minor(degree: number) {
  return Major(degree).diminished
}

function Perfect(degree: number) {
  if (![1, 4, 5, 8].includes(degree)) {
    throw `Cannot instantiate perfect interval of degree ${degree}`
  }
  return new Interval(degree)
}

export default {
  Major,
  Minor,
  Perfect,
}
