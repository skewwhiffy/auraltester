import { Form } from 'react-bootstrap'

type OnChangeHandler = (value: string) => void

export interface RadioButtonDefinition {
  key?: string
  value: string
  label?: string
}

export interface Props {
  values: RadioButtonDefinition[]
  name: string
  value?: string
  onChange: OnChangeHandler
}

const RadioButtons = (props: Props): JSX.Element => {
  if (props.values.length === 0) {
    throw Error('Need at least one radio button')
  }

  const onChange = (ev: any): void => {
    (props.onChange ?? (_ => { }))(ev.target.value)
  }

  const renderRadioButton = (radioButton: RadioButtonDefinition): JSX.Element => {
    const value = radioButton.value
    const key = radioButton.key ?? value
    const label = radioButton.label ?? value
    return (
      <Form.Check
        id={`${props.name}-${key}`}
        key={key}
        name={props.name}
        value={value}
        label={label}
        type='radio'
        defaultChecked={value === props.value}
      />
    )
  }
  return (
    <Form.Group onChange={onChange}>
      {props.values.map(renderRadioButton)}
    </Form.Group>
  )
}

export default RadioButtons
