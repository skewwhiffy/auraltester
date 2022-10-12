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

  constructor(degree: number) {
    this.degree = degree
    this.deviation = 0
  }

  get displayString() {
    return `${this.quality} ${displayStrings[this.degree - 1]}`
  }
  
  get quality() {
    if (this.deviation === 0) {
      return perfectDegrees.includes(this.degree) ? 'perfect' : 'major'
    }
  }

}

function Major(degree: number) {
  if (![2, 3, 6, 7].includes(degree)) {
    throw `Cannot instantiate major interval of degree ${degree}`
  }
  return new Interval(degree)
}

function Perfect(degree: number) {
  if (![1, 4, 5, 8].includes(degree)) {
    throw `Cannot instantiate perfect interval of degree ${degree}`
  }
  return new Interval(degree)
}

export default {
  Major,
  Perfect,
}
