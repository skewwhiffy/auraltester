import React, { FormEventHandler } from 'react'
import { RadioGroup, RadioButton } from 'react-radio-buttons'
import { Form, Container, Row, Col } from 'react-bootstrap'

type OnChangeHandler = (clef: string, note: string, type: string) => void

interface Props {
  onChange: OnChangeHandler
}

interface State {
  clef: string,
  note: string,
  accidental: string,
  type: string
}

class ScaleSelector extends React.Component<Props, State> {
  constructor(props: Props) {
    super(props)
    this.state = {
      clef: '',
      note: '',
      accidental: '',
      type: ''
    }
  }

  render() {
    return (
      <Container>
        <Form>
          <Row>
            <Col>
              <Form.Group onChange={e => this.onNoteChange(e.target)}>
                <Form.Check name='note' value='A' type='radio' label='A'></Form.Check>
                <Form.Check name='note' value='B' type='radio' label='B'></Form.Check>
                <Form.Check name='note' value='C' type='radio' label='C'></Form.Check>
                <Form.Check name='note' value='D' type='radio' label='D'></Form.Check>
                <Form.Check name='note' value='E' type='radio' label='E'></Form.Check>
                <Form.Check name='note' value='F' type='radio' label='F'></Form.Check>
                <Form.Check name='note' value='G' type='radio' label='G'></Form.Check>
              </Form.Group>
            </Col>
            <Col>
              <RadioGroup onChange={this.onAccidentalChange}>
                <RadioButton value='x'>x</RadioButton>
                <RadioButton value='#'>#</RadioButton>
                <RadioButton value=''>natural</RadioButton>
                <RadioButton value='b'>b</RadioButton>
                <RadioButton value='bb'>bb</RadioButton>
              </RadioGroup>
            </Col>
            <Col>
              <RadioGroup onChange={this.onClefChange}>
                <RadioButton value='treble'>Treble Clef</RadioButton>
                <RadioButton value='alto'>Alto Clef</RadioButton>
                <RadioButton value='tenor'>Tenor Clef</RadioButton>
                <RadioButton value='bass'>Bass Clef</RadioButton>
              </RadioGroup>
            </Col>
            <Col>
              <RadioGroup onChange={this.onTypeChange}>
                <RadioButton value='major'>Major</RadioButton>
                <RadioButton value='minor-harmonic'>Minor Harmonic</RadioButton>
                <RadioButton value='minor-melodic-ascending'>Minor Melodic Ascending</RadioButton>
                <RadioButton value='minor-melodic-descending'>Minor Melodic Descending</RadioButton>
              </RadioGroup>
            </Col>
          </Row>
        </Form>
      </Container>
    )
  }

  onFormChange = (newState: State) => {
    this.setState(newState)
    this.props.onChange(
      newState.clef,
      `${newState.note}${newState.accidental}`,
      newState.type
    )
  }

  onTypeChange = (newType: string) => {
    this.onFormChange({
      ...this.state,
      type: newType
    })
  }

  onAccidentalChange = (newAccidental: string) => {
    this.onFormChange({
      ...this.state,
      accidental: newAccidental
    })
  }

  onNoteChange = (e: any) => {
    this.onFormChange({
      ...this.state,
      note: e.value
    })
  }

  onClefChange = (newClef: string) => {
    this.onFormChange({
      ...this.state,
      clef: newClef
    })
  }

}

export default ScaleSelector