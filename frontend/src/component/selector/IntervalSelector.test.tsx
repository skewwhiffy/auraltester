import IntervalSelector from './IntervalSelector'
import { render, screen } from '@testing-library/react'
import { act } from 'react-dom/test-utils'

interface Triggered {
  clef: string,
  bottomNote: string,
  intervalQuality: string,
  intervalSize: string,
  keySignature: string
}

describe('IntervalSelector', () => {
  let triggered: Array<Triggered>

  beforeEach(() => {
    triggered = []
    render(<IntervalSelector onChange={(
      clef: string,
      bottomNote: string,
      intervalQuality: string,
      intervalSize: string,
      keySignature: string
    ) => triggered.push({
      clef, bottomNote, intervalQuality, intervalSize, keySignature
    })} />)
  })

  it.only('triggers clef changes', () => {
    act(() => screen.getByLabelText('Alto Clef').click())

    expect(triggered).toHaveLength(1)
    expect(triggered[0].clef).toEqual('alto')
  })

  it('triggers bottom note changes', () => {
    act(() => {
      ['D', '#']
        .map(it => screen.getByLabelText(it))
        .forEach(it => it.click())
    })
    
    const candidates = triggered.filter(it => it.bottomNote === 'D#')
    expect(candidates).toHaveLength(1)
  })
  
  it('triggers interval quality', () => {
    const intervalQuality = 'diminished'

    act(() => screen.getByLabelText(intervalQuality).click())
    
    expect(triggered).toHaveLength(1)
    expect(triggered[0].intervalQuality).toBe(intervalQuality)
  })
  
  it('triggers interval size', () => {
    const intervalSize = '7'
    
    act(() => screen.getByLabelText(intervalSize).click())
    
    expect(triggered).toHaveLength(1)
    expect(triggered[0].intervalSize).toBe(intervalSize)
  })
  
  it('triggers key signature', () => {
    const keySignatureLabel = 'B / G# minor'
    const expectedKeySignature = 'B'
    
    act(() => screen.getByLabelText(keySignatureLabel).click())
    
    expect(triggered).toHaveLength(1)
    expect(triggered[0].keySignature).toBe(expectedKeySignature)
  })
})