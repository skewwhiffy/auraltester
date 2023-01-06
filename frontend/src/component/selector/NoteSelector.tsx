import { useState } from "react"
import { Col, Container, Row } from "react-bootstrap"
import RadioButtons  from "../../util/RadioButtons"

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

export default NoteSelector