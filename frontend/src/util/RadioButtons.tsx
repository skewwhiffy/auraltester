import React from 'react'
import { Form } from "react-bootstrap";

type OnChangeHandler = (value: string) => void

export interface RadioButtonDefinition {
  key?: string,
  value: string,
  label?: string
}

export interface Props {
  values: Array<RadioButtonDefinition>,
  name: string,
  defaultValue?: string,
  onChange: OnChangeHandler
}

const RadioButtons = (props: Props) => {
  if (props.values.length === 0) {
    throw Error('Need at least one radio button')
  }

  const onChange = (ev: any) => {
    (props.onChange ?? (_ => { }))(ev.target.value)
  }

  const renderRadioButton = (radioButton: RadioButtonDefinition) => {
    const value = radioButton.value
    const key = radioButton.key || value
    const label = radioButton.label || value
    const defaultValue = props.defaultValue === undefined
      ? props.values[0].value
      : props.defaultValue
    return (
      <Form.Check
        id={`${props.name}-${key}`}
        key={key}
        name={props.name}
        value={value}
        label={label}
        type='radio'
        defaultChecked={value === defaultValue}
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