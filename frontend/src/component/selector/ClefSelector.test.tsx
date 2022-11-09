import ClefSelector from './ClefSelector'
import { Props } from '../../util/RadioButtons'
import renderer from 'react-test-renderer'

jest.mock('../../util/RadioButtons', () => (props: Props) => (
  <div>
    { `'${props.defaultValue}' '${props.name}' '${props.onChange}' '${JSON.stringify(props.values)}'` }
  </div>
));
describe('ClefSelector', () => {
  it('matches snapshot when default value set', () => {
    const actual = renderer.create(
      <ClefSelector defaultValue='default_value' onChange={() => {}} />
    )
    
    expect(actual).toMatchSnapshot()
  })
})