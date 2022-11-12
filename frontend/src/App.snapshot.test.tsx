import renderer from 'react-test-renderer'
import App from './App'

jest.mock('./component/Scale', () => () => (<div>SCALE</div>))
jest.mock('./component/Interval', () => () => (<div>INTERVAL</div>))
jest.mock('./component/NavBar', () => () => (<div>NAVBAR</div>))
describe('App snapshot', () => {
  it('should match snapshot', () => {
    const actual = renderer.create(
      <App/>
    )
    
    expect(actual).toMatchSnapshot()
  })
})