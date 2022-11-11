import { ReactElement } from 'react'
import renderer from 'react-test-renderer'
import RadioButtons, { RadioButtonDefinition } from './RadioButtons'

describe('RadioButtons', () => {
  let values: Array<RadioButtonDefinition>
  let onChange: (_: string) => void
  let getElement: () => ReactElement

  beforeEach(() => {
    values = Array.from(Array(5).keys())
      .map(it => ({
        value: `${it}-value`,
        key: `${it}-key`,
        label: `${it}-label`
      }))
    onChange = (_: string) => { }
    getElement = () => {
      return (
        <RadioButtons
          values={values}
          name='radioButtonsTest'
          onChange={onChange}
        />
      )
    }
  })

  it('should match snapshot', () => {
    const actual = renderer.create(getElement())

    expect(actual).toMatchSnapshot()
  })
})