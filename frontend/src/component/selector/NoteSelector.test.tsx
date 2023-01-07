import NoteSelector from './NoteSelector';
import { render, screen } from '@testing-library/react';
import { act } from 'react-dom/test-utils';

describe('NoteSelector', () => {
  let triggered: Array<string>
  const onChange = (note: string) => triggered.push(note)

  beforeEach(() => {
    triggered = []
  })
  
  it('triggers accidentals', () => {
    render(<NoteSelector value='C' onChange={onChange} includeDoubleAccidentals />)
    
    act(() => screen.getByLabelText('double sharp').click())
    
    expect(triggered).toHaveLength(1)
    expect(triggered[0][triggered[0].length - 1]).toBe('x')
  })
  
  it('triggers note names', () => {
    const noteName = 'E'
    render(<NoteSelector value='D' onChange={onChange} />)
    
    act(() => screen.getByLabelText(noteName).click())
    
    expect(triggered).toHaveLength(1)
    expect(triggered[0][triggered[0].length - 1]).toBe('E')
  })
})