import ScaleSelector from './ScaleSelector'
import { Props as RadioButtonProps } from '../../util/RadioButtons'
import { Props as ClefSelectorProps } from './ClefSelector'
import { Props as NoteSelectorProps } from './NoteSelector'
import renderer from 'react-test-renderer'

jest.mock('../../util/RadioButtons', () => (props: RadioButtonProps) => (
  <div>RADIO BUTTONS {JSON.stringify(props)}</div>
))

jest.mock('./ClefSelector', () => (props: ClefSelectorProps) => {
  <div>CLEF SELECTOR {JSON.stringify(props)}</div>
})

jest.mock('./NoteSelector', () => (props: NoteSelectorProps) => (
  <div>NOTE SELECTOR {JSON.stringify(props)}</div>
))

describe('ScaleSelector snapshot', () => {
  it('matches snapshot', () => {
    const actual = renderer.create(<ScaleSelector onChange={() => { }} />)
    
    expect(actual).toMatchSnapshot()
  })
})