const perfectDegrees = [1, 4, 5, 8]

const displayStrings = [
    "unison",
    "second",
    "third",
    "fourth",
    "fifth",
    "sixth",
    "seventh",
    "octave"
]

interface Interval {
    readonly degree: number;
    readonly deviation: number;
    readonly displayString: string;
    readonly diminished: Interval;
    readonly augmented: Interval;
    equals(other: Interval): boolean;
}

interface IntervalImplProperties {
    degree: number,
    deviation: number,
}

class IntervalImpl implements Interval {
    readonly degree: number;
    readonly deviation: number;

    constructor({degree, deviation}: IntervalImplProperties) {
        this.degree = degree;
        this.deviation = deviation;
    }

    get quality() {
        if (this.deviation === 0) {
            return perfectDegrees.includes(this.degree) ? 'perfect' : 'major';
        }
        const diminishedQuality = perfectDegrees.includes(this.degree) ? -this.deviation : -this.deviation - 1;
        if (this.deviation < 0) {
            return (() => {
                if (!perfectDegrees.includes(this.degree) && this.deviation === -1) {
                    return 'minor';
                }
                if (diminishedQuality === 1) {
                    return 'diminished';
                }
                if (diminishedQuality === 2) {
                    return 'doubly diminished'
                }
                return `${diminishedQuality}x diminished`
            })()
        }
        return (() => {
            if (this.deviation === 1) {
                return '';
            }
            if (this.deviation === 2) {
                return 'doubly '
            }
            return `${this.deviation}x `;
        })() + 'augmented'
    }

    get displayString() {
        return `${this.quality} ${displayStrings[this.degree - 1]}`
    }

    get diminished() {
        return new IntervalImpl({...this, deviation: this.deviation - 1})
    }

    get augmented() {
        return new IntervalImpl({...this, deviation: this.deviation + 1})
    }

    equals(other: Interval) {
        return this.degree === other.degree && this.deviation === other.deviation;
    }
}

export const intervalFactory = {
    perfect(degree: number): Interval {
        if (!perfectDegrees.includes(degree)) {
            throw Error(`Cannot instantiate perfect interval of degree '${degree}'`)
        }
        return new IntervalImpl({degree, deviation: 0})
    },
    major(degree: number): Interval {
        if (perfectDegrees.includes(degree)) {
            throw Error(`Cannot instantiate major interval of degree '${degree}'`);
        }
        return new IntervalImpl({degree, deviation: 0});
    },
    minor(degree: number): Interval {
        return this.major(degree).diminished;
    }
}