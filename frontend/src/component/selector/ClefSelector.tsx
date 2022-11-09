import React from 'react'
import RadioButtons, { RadioButtonDefinition } from '../../util/RadioButtons'
import { capitalizeFirstCharacter } from '../../util'
import { Col, Container, Row } from 'react-bootstrap'

type OnChangeHandler = (value: string) => void

interface Props {
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
  }

  render() {
    return (
      <Container>
        <Row><Col>Clef</Col></Row>
        <Row><Col>
          <RadioButtons
            values={this.values}
            name='clef'
            defaultValue={this.props.defaultValue || 'treble'}
            onChange={this.props.onChange}
          />
        </Col></Row>
      </Container>
    )
  }
}

export default ClefSelector