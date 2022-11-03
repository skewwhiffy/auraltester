import renderer from 'react-test-renderer'
import RadioButtons from './RadioButtons'

describe('RadioButtons', () => {
  it('should match snapshot', () => {
    const values = Array.from(Array(5).keys())
    .map(it => ({
      value: `${it}-value`,
      key: `${it}-key`,
      label: `${it}-label`
    }))
    const onChange = (value: string) => {}

    const actual = renderer.create(
      <RadioButtons
        values={values}
        name='radioButtonsTest'
        onChange={onChange}
      />
    )

    expect(actual).toMatchSnapshot()
  })
})