import React from 'react'
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
      clef: 'treble',
      note: 'C',
      accidental: '',
      type: 'major'
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
          </Row>
        </Form>
      </Container>
    )
  }

  private renderTypeRadioButtons() {
    return ['major', 'minor-harmonic', 'minor-melodic-ascending', 'minor-melodic-descending']
      .map(it => {
        const label = it.split('-').map(this.capitaliseFirstCharacter).join(' ')
        return this.renderCheckbox('type', it, label, this.state.type)
      })
  }

  private renderClefRadioButtons() {
    return ['treble', 'alto', 'tenor', 'bass']
      .map(it => {
        const label = `${this.capitaliseFirstCharacter(it)} Clef`
        return this.renderCheckbox('clef', it, label, this.state.clef)
      })
  }

  private renderNoteRadioButtons() {
    return 'ABCDEFG'
      .split('')
      .map(it => this.renderCheckbox('note', it, it, this.state.note))
  }

  private renderAccidentalRadioButtons() {
    return [
      ['#', '#'],
      ['', 'natural'],
      ['b', 'b']
    ]
      .map(it => this.renderCheckbox('accidental', it[0], it[1], this.state.accidental))
  }

  private renderCheckbox(name: string, value: string, label: string, selectedValue: string) {
    const key = `${name}${value}`
    return (<Form.Check key={key} name={name} value={value} type='radio' label={label} defaultChecked={value == selectedValue}/>)
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
      state.type
    )
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