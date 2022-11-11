import ClefSelector from './ClefSelector'
import { render, screen } from '@testing-library/react';

describe('ClefSelector', () => {
  it('triggers onChange function supplied', () => {
    const toTrigger = ['Alto Clef', 'Bass Clef', 'Tenor Clef', 'Treble Clef']
    const expected = ['alto', 'bass', 'tenor', 'treble']
    const triggered: Array<String> = []
    render(
      <ClefSelector onChange={it => triggered.push(it)} />
    )

    toTrigger.map(it => screen.getByLabelText(it)).forEach(it => it.click())

    expect(triggered).toStrictEqual(expected)
  })
})