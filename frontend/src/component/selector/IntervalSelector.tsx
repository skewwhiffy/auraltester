import { useState } from 'react'
import { Col, Container, Form, Row } from 'react-bootstrap'
import RadioButtons from '../../util/RadioButtons'
import ClefSelector from './ClefSelector'
import KeySignatureSelector from './KeySignatureSelector'
import NoteSelector from './NoteSelector'

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

  const onFormChange = (newState: State): void => {
    setState(newState)
    onStateChange(newState)
  }

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

  return (
    <Container>
      <Form>
        <Row>
          <Col>
            <ClefSelector
              value={state.clef}
              onChange={clef => { onFormChange({ ...state, clef }) }}
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
            <Container>
              <Row><Col>Interval</Col></Row>
              <Row>
                <Col>
                  <RadioButtons
                    values={['diminished', 'minor', 'major', 'perfect', 'augmented'].map(value => ({ value }))}
                    name='intervalQuality'
                    value={state.intervalQuality}
                    onChange={intervalQuality => { onFormChange({ ...state, intervalQuality }) }}
                  />
                </Col>
                <Col>
                  <RadioButtons
                    values={Array.from(Array(8).keys()).map(it => it + 1).map(it => ({ value: `${it}` }))}
                    name='intervalSize'
                    value={state.intervalQuality}
                    onChange={intervalSize => { onFormChange({ ...state, intervalSize }) }}
                  />
                </Col>
              </Row>
            </Container>
          </Col>
          <Col>
            <KeySignatureSelector
              defaultValue={state.keySignature}
              onChange={keySignature => { onFormChange({ ...state, keySignature }) }}
            />
          </Col>
        </Row>
      </Form>
    </Container>
  )
}

export default IntervalSelector
