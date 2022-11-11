import NoteSelector from './NoteSelector';
import { Props as RadioButtonProps } from '../../util/RadioButtons'
import renderer from 'react-test-renderer'

jest.mock('../../util/RadioButtons', () => (props: RadioButtonProps) => (
  <div>{JSON.stringify(props)}</div>
))

describe('NoteSelector snapshot', () => {
  it('matches snapshot', () => {
    const actual = renderer.create(
      <NoteSelector defaultValue='C' includeDoubleAccidentals onChange={() => { }} />
    )

    expect(actual).toMatchSnapshot()
  })

  it('matches snapshot with no includeDoubleAccidentals', () => {
    const actual = renderer.create(
      <NoteSelector defaultValue='D' onChange={() => { }} />
    )

    expect(actual).toMatchSnapshot()
  })
})