import React, { useState } from "react"
import { Col, Container, Row } from "react-bootstrap"
import RadioButtons, { RadioButtonDefinition } from "../../util/RadioButtons"

type OnChangeHandler = (note: string) => void

export interface Props {
  defaultValue: string,
  includeDoubleAccidentals?: boolean,
  onChange: OnChangeHandler
}

interface State {
  noteName: string,
  accidental: string
}


const NoteSelector = (props: Props) => {
  const accidentals = [
    { value: '#' },
    { value: '', label: 'natural' },
    { value: 'b' }
  ]

  const extractNoteName = (note: string) => {
    return note.substring(0, 1)
  }

  const extractAccidental = (note: string) => {
    return note.substring(1)
  }

  const onFormChange = (newState: State) => {
    setState(newState)
    onStateChange(newState)
  }

  const onStateChange = (state: State) => {
    props.onChange(`${state.noteName}${state.accidental}`)
  }

  const [state, setState] = useState<State>({
    noteName: extractNoteName(props.defaultValue),
    accidental: extractAccidental(props.defaultValue)
  })

  if (props.includeDoubleAccidentals) {
    accidentals.unshift({ value: 'x', label: 'double sharp' })
    accidentals.push({ value: 'bb', label: 'double flat' })
  }

  return (
    <Container>
      <Row><Col>Note</Col></Row>
      <Row>
        <Col>
          <RadioButtons
            values={'ABCDEFG'.split('').map(it => ({ value: it }))}
            name='note'
            defaultValue={state.noteName}
            onChange={note => onFormChange({ ...state, noteName: note })}
          />
        </Col>
        <Col>
          <RadioButtons
            values={accidentals}
            name='accidental'
            defaultValue={state.accidental}
            onChange={accidental => onFormChange({ ...state, accidental })}
          />
        </Col>
      </Row>
    </Container>
  )
}

class NoteSelectorOld extends React.Component<Props, State> {
  private accidentals: Array<RadioButtonDefinition>

  constructor(props: Props) {
    super(props);
    this.state = {
      noteName: this.extractNoteName(props.defaultValue),
      accidental: this.extractAccidental(props.defaultValue)
    }
    this.accidentals = [
      { value: '#' },
      { value: '', label: 'natural' },
      { value: 'b' }
    ]
    if (props.includeDoubleAccidentals) {
      this.accidentals.unshift({ value: 'x', label: 'double sharp' })
      this.accidentals.push({ value: 'bb', label: 'double flat' })
    }
  }

  render() {
    return (
      <Container>
        <Row><Col>Note</Col></Row>
        <Row>
          <Col>
            <RadioButtons
              values={'ABCDEFG'.split('').map(it => ({ value: it }))}
              name='note'
              defaultValue={this.state.noteName}
              onChange={note => this.onFormChange({ ...this.state, noteName: note })}
            />
          </Col>
          <Col>
            <RadioButtons
              values={this.accidentals}
              name='accidental'
              defaultValue={this.state.accidental}
              onChange={accidental => this.onFormChange({ ...this.state, accidental })}
            />
          </Col>
        </Row>
      </Container>
    )
  }

  private extractNoteName = (note: string) => {
    return note.substring(0, 1)
  }

  private extractAccidental = (note: string) => {
    return note.substring(1)
  }

  private onFormChange = (newState: State) => {
    this.setState(newState)
    this.onStateChange(newState)
  }

  private onStateChange(state: State) {
    this.props.onChange(`${state.noteName}${state.accidental}`)
  }

}

export default NoteSelector