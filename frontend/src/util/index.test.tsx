import { capitalizeFirstCharacter } from "."

describe('capitalizeFirstCharacter', () => {
  it('capitalize first character of string', () => {
    const source = 'this'
    const expected = 'This'

    const actual = capitalizeFirstCharacter(source)

    expect(actual).toEqual(expected)
  })
})