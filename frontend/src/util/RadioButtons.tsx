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

interface State {
  value: string
}

class RadioButtons extends React.Component<Props, State> {
  constructor(props: Props) {
    if (props.values.length === 0) {
      throw Error('Need at least one radio button')
    }
    super(props);
  }

  private onChange = (ev: any) => {
    (this.props.onChange || (_ => { }))(ev.target.value)
  }

  private renderRadioButton = (radioButton: RadioButtonDefinition) => {
    const value = radioButton.value
    const key = radioButton.key || value
    const label = radioButton.label || value
    const defaultValue = this.props.defaultValue === undefined
      ? this.props.values[0].value
      : this.props.defaultValue
    return (
      <Form.Check
        id={`${this.props.name}-${key}`}
        key={key}
        name={this.props.name}
        value={value}
        label={label}
        type='radio'
        defaultChecked={value === defaultValue}
      />
    )
  }

  render() {
    return (
      <Form.Group onChange={this.onChange}>
        {this.props.values.map(this.renderRadioButton)}
      </Form.Group>
    )
  }
}

export default RadioButtons