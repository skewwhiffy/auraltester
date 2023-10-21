import { useState } from 'react'
import { Col, Container, Form, Row } from 'react-bootstrap'
import ClefSelector from './ClefSelector'
import KeySignatureSelector from './KeySignatureSelector'
import NoteSelector from './NoteSelector'
import { IntervalSizeSelector } from './IntervalSizeSelector'

type OnChangeHandler = (
  clef: string,
  bottomNote: string,
  intervalQuality: string,
  intervalSize: string,
  keySignature: string
) => void

export interface Props {
  onChange: OnChangeHandler
}

interface State {
  clef?: string
  bottomNote?: string
  intervalQuality?: string
  intervalSize?: string
  keySignature?: string
}

const IntervalSelector = (props: Props): JSX.Element => {
  const [state, setState] = useState<State>({})

  const onStateChange = (state: State): void => {
    if (state.clef === undefined ||
      state.bottomNote === undefined ||
      state.intervalQuality === undefined ||
      state.intervalSize === undefined ||
      state.keySignature === undefined) {
      return
    }
    props.onChange(
      state.clef,
      state.bottomNote,
      state.intervalQuality,
      state.intervalSize,
      state.keySignature
    )
  }

  const onFormChange = (newState: State): void => {
    setState(newState)
    onStateChange(newState)
  }

  return (
    <Container>
      <Form>
        <Row>
          <Col>
            <ClefSelector
              value={state.clef}
              onChange={clef => onFormChange({ ...state, clef })}
            />
          </Col>
          <Col>
            <NoteSelector
              value={state.bottomNote}
              includeDoubleAccidentals={true}
              onChange={bottomNote => { onFormChange({ ...state, bottomNote }) }}
            />
          </Col>
          <Col>
            <IntervalSizeSelector onChange={(intervalQuality, intervalSize) =>
              onFormChange({
                ...state,
                intervalQuality,
                intervalSize
              })
            }
            />
          </Col>
          <Col>
            <KeySignatureSelector
              value={state.keySignature}
              onChange={keySignature => onFormChange({ ...state, keySignature })}
            />
          </Col>
        </Row>
      </Form>
    </Container>
  )
}

export default IntervalSelector
