import Interval from './Interval'
import renderer from 'react-test-renderer'
import { Props as IntervalSelectorProps } from './selector/IntervalSelector'

jest.mock('react-abc', () => ({
  Notation: (props: any) => (<div>{JSON.stringify(props)}</div>)
}))
jest.mock('axios', () => ({ }))
jest.mock('./selector/IntervalSelector', () => (props: IntervalSelectorProps) => (
  <div>INTERVAL SELECTOR {JSON.stringify(props)}</div>
))

describe('Interval snapshot', () => {
  it('matches snapshot', () => {
    const actual = renderer.create(
      <Interval />
    )

    expect(actual).toMatchSnapshot()
  })
})