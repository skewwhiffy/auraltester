import IntervalSelector from './IntervalSelector'
import { Props as ClefSelectorProps } from '../../component/selector/ClefSelector'
import { Props as NoteSelectorProps } from '../../component/selector/NoteSelector'
import { Props as RadioButtonsProps } from '../../util/RadioButtons'
import renderer from 'react-test-renderer'

jest.mock('../../component/selector/ClefSelector', () => (props: ClefSelectorProps) => (
  <div>{JSON.stringify(props)}</div>
))

jest.mock('../../component/selector/NoteSelector', () => (props: NoteSelectorProps) => (
  <div>{JSON.stringify(props)}</div>
))

jest.mock('../../util/RadioButtons', () => (props: RadioButtonsProps) => (
  <div>{JSON.stringify(props)}</div>
))

describe('IntervalSelector snapshot', () => {
  it('matches snapshot', () => {
    const actual = renderer.create(
      <IntervalSelector onChange={() => { }} />
    )

    expect(actual).toMatchSnapshot()
  })
})