import { render, screen } from '@testing-library/react'
import { ReactElement } from 'react'
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

  it('populates label from value if not provided', () => {
    const value = 'the-value'
    values = [{ key: 'the-key', value }]

    render(getElement())

    const label = screen.getByLabelText(value)
    expect(label).not.toBeNull()
  })

  it('can render without key', () => {
    const value = 'another-value'
    values = [{ value }]

    expect(() => render(getElement())).not.toThrowError()
  })

  it('triggers on change function', () => {
    let triggered: Array<string> = []
    const buttonToClick = values[1]
    const value = buttonToClick.value
    const label = buttonToClick.label || ''
    onChange = (value: string) => { triggered.push(value) }
    render(getElement())

    screen.getByLabelText(label).click()

    expect(triggered).toStrictEqual([value])
  })
})