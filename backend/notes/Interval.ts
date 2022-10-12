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

class Interval {
  private readonly degree: number

  constructor(degree: number) {
    this.degree = degree
  }

  get displayString() {
    return `major ${displayStrings[this.degree - 1]}`
  }

}

function Major(degree: number) {
  return new Interval(degree)
}

function Minor(degree: number) {
  return new Interval(degree)
}

export default {
  Major,
  Minor,
}
