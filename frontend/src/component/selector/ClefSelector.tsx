import React from 'react'
import RadioButtons, { RadioButtonDefinition } from '../../util/RadioButtons'
import { capitalizeFirstCharacter } from '../../util'
import { Col, Container, Row } from 'react-bootstrap'

type OnChangeHandler = (value: string) => void

export interface Props {
  defaultValue?: string,
  onChange: OnChangeHandler
}

interface State { }

class ClefSelector extends React.Component<Props, State> {
  private values: RadioButtonDefinition[]

  constructor(props: Props) {
    super(props);
    this.values = ['treble', 'alto', 'tenor', 'bass']
      .map(it => ({
        value: it,
        label: `${capitalizeFirstCharacter(it)} Clef`
      }))
    if (!props.defaultValue) {
      this.props.onChange(this.values[0].value)
    }
  }

  render() {
    return (
      <Container>
        <Row><Col>Clef</Col></Row>
        <Row><Col>
          <RadioButtons
            values={this.values}
            name='clef'
            defaultValue={this.props.defaultValue || this.values[0].value}
            onChange={this.props.onChange}
          />
        </Col></Row>
      </Container>
    )
  }
}

export default ClefSelector