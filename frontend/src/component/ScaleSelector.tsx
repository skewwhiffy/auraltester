import React from 'react'
import { Form, Container, Row, Col } from 'react-bootstrap'

type OnChangeHandler = (
  clef: string,
  note: string,
  type: string,
  direction: string,
  withKeySignature: boolean
) => void

interface Props {
  onChange: OnChangeHandler
}

interface State {
  clef: string,
  note: string,
  accidental: string,
  type: string,
  direction: string,
  withKeySignature: boolean
}

class ScaleSelector extends React.Component<Props, State> {
  constructor(props: Props) {
    super(props)
    this.state = {
      clef: 'treble',
      note: 'C',
      accidental: '',
      type: 'major',
      direction: 'ascending',
      withKeySignature: true
    }
    this.onStateChange(this.state)
  }

  render() {
    return (
      <Container>
        <Form>
          <Row>
            <Col>
              <Form.Group onChange={e => this.onNoteChange(e.target)}>
                {this.renderNoteRadioButtons()}
              </Form.Group>
            </Col>
            <Col>
              <Form.Group onChange={e => this.onAccidentalChange(e.target)}>
                {this.renderAccidentalRadioButtons()}
              </Form.Group>
            </Col>
            <Col>
              <Form.Group onChange={e => this.onClefChange(e.target)}>
                {this.renderClefRadioButtons()}
              </Form.Group>
            </Col>
            <Col>
              <Form.Group onChange={e => this.onTypeChange(e.target)}>
                {this.renderTypeRadioButtons()}
              </Form.Group>
            </Col>
            <Col>
              <Form.Group onChange={e => this.onDirectionChange(e.target)}>
                {this.renderDirectionRadioButtons()}
              </Form.Group>
            </Col>
            <Col>
              <Form.Check 
                type='checkbox'
                onChange={e => this.onWithKeySignatureChange(e.target)}
                label='Use key signature'
                defaultChecked={this.state.withKeySignature}
              />
            </Col>
          </Row>
        </Form>
      </Container>
    )
  }

  private renderDirectionRadioButtons() {
    return ['ascending', 'descending']
      .map(it => {
        return this.renderRadioButton('direction', it, this.capitaliseFirstCharacter(it), this.state.direction)
      })
  }

  private renderTypeRadioButtons() {
    return ['major', 'minor-harmonic', 'minor-melodic']
      .map(it => {
        const label = it.split('-').map(this.capitaliseFirstCharacter).join(' ')
        return this.renderRadioButton('type', it, label, this.state.type)
      })
  }

  private renderClefRadioButtons() {
    return ['treble', 'alto', 'tenor', 'bass']
      .map(it => {
        const label = `${this.capitaliseFirstCharacter(it)} Clef`
        return this.renderRadioButton('clef', it, label, this.state.clef)
      })
  }

  private renderNoteRadioButtons() {
    return 'ABCDEFG'
      .split('')
      .map(it => this.renderRadioButton('note', it, it, this.state.note))
  }

  private renderAccidentalRadioButtons() {
    return [
      ['#', '#'],
      ['', 'natural'],
      ['b', 'b']
    ]
      .map(it => this.renderRadioButton('accidental', it[0], it[1], this.state.accidental))
  }

  private renderRadioButton(name: string, value: string, label: string, selectedValue: string) {
    const key = `${name}${value}`
    return (<Form.Check key={key} name={name} value={value} type='radio' label={label} defaultChecked={value === selectedValue} />)
  }

  private capitaliseFirstCharacter(source: string) {
    return `${source[0].toUpperCase()}${source.substring(1)}`
  }

  private onFormChange = (newState: State) => {
    this.setState(newState)
    this.onStateChange(newState)
  }

  private onStateChange(state: State) {
    this.props.onChange(
      state.clef,
      `${state.note}${state.accidental}`,
      state.type,
      state.direction,
      state.withKeySignature
    )
  }

  onWithKeySignatureChange = (e: any) => {
    this.onFormChange({
      ...this.state,
      withKeySignature: e.checked
    })
  }

  onDirectionChange = (e: any) => {
    this.onFormChange({
      ...this.state,
      direction: e.value
    })
  }

  onTypeChange = (e: any) => {
    this.onFormChange({
      ...this.state,
      type: e.value
    })
  }

  onAccidentalChange = (e: any) => {
    this.onFormChange({
      ...this.state,
      accidental: e.value
    })
  }

  onNoteChange = (e: any) => {
    console.log(e.value)
    this.onFormChange({
      ...this.state,
      note: e.value
    })
  }

  onClefChange = (e: any) => {
    this.onFormChange({
      ...this.state,
      clef: e.value
    })
  }

}

export default ScaleSelector