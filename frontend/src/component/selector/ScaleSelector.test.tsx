import ScaleSelector from './ScaleSelector'
import { render, screen } from '@testing-library/react'
import { act } from 'react-dom/test-utils'

interface Triggered {
  clef: string,
  note: string,
  type: string,
  direction: string
}

describe('ScaleSelector', () => {
  let triggered: Array<Triggered>

  beforeEach(() => {
    triggered = []
    render(<ScaleSelector onChange={(
      clef: string,
      note: string,
      type: string,
      direction: string
    ) => triggered.push({
      clef, note, type, direction
    })} />)
  })

  it('triggers clef changes', () => {
    act(() => screen.getByLabelText('Alto Clef').click())

    const candidates = triggered.filter(it => it.clef === 'alto')
    expect(candidates).toHaveLength(1)
  })

  it('triggers note changes', () => {
    act(() => {
      ['D', '#']
        .map(it => screen.getByLabelText(it))
        .forEach(it => it.click())
    })

    const candidates = triggered.filter(it => it.note === 'D#')
    expect(candidates).toHaveLength(1)
  })

  it('triggers type changes', () => {
    act(() => screen.getByLabelText('Minor Harmonic').click())
    
    const candidates = triggered.filter(it => it.type === 'minor-harmonic')
    expect(candidates).toHaveLength(1)
  })
  
  it('triggers direction changes', () => {
    act(() => screen.getByLabelText('Descending').click())
    
    const candidates = triggered.filter(it => it.direction === 'descending')
    expect(candidates).toHaveLength(1)
  })
})