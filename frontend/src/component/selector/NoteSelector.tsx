import { useState } from 'react'
import { Col, Container, Row } from 'react-bootstrap'
import RadioButtons from '../../util/RadioButtons'

type OnChangeHandler = (note: string) => void

export interface Props {
  value?: string
  includeDoubleAccidentals?: boolean
  onChange: OnChangeHandler
}

interface State {
  noteName?: string
  accidental?: string
}

const NoteSelector = (props: Props): JSX.Element => {
  const accidentals = [
    { value: '#' },
    { value: '', label: 'natural' },
    { value: 'b' }
  ]

  const extractNoteName = (note?: string): string | undefined =>
    (note === undefined || note === '')
      ? undefined
      : note.substring(0, 1)

  const extractAccidental = (note?: string): string | undefined =>
    (note === undefined || note === '')
      ? undefined
      : note.substring(1)

  const onFormChange = (newState: State): void => {
    setState(newState)
    onStateChange(newState)
  }

  const onStateChange = (state: State): void => {
    if (state.noteName === undefined ||
      state.accidental === undefined) {
      return
    }
    props.onChange(`${state.noteName}${state.accidental}`)
  }

  const [state, setState] = useState<State>({
    noteName: props.value === undefined ? '' : extractNoteName(props.value),
    accidental: props.value == undefined ? '' : extractAccidental(props.value)
  })

  if (props.includeDoubleAccidentals ?? false) {
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
            value={state.noteName}
            onChange={note => { onFormChange({ ...state, noteName: note }) }}
          />
        </Col>
        <Col>
          <RadioButtons
            values={accidentals}
            name='accidental'
            value={state.accidental}
            onChange={accidental => { onFormChange({ ...state, accidental }) }}
          />
        </Col>
      </Row>
    </Container>
  )
}

export default NoteSelector
