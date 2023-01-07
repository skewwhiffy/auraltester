import { useState } from 'react'
import { Form, Container, Row, Col } from 'react-bootstrap'
import RadioButtons from '../../util/RadioButtons'
import ClefSelector from './ClefSelector'
import { capitalizeFirstCharacter } from '../../util'
import NoteSelector from './NoteSelector'

type OnChangeHandler = (
  clef: string,
  note: string,
  type: string,
  direction: string
) => void

interface Props {
  clef?: string
  note?: string
  type?: string
  direction?: string
  onChange: OnChangeHandler
}

interface State {
  clef?: string
  note?: string
  type?: string
  direction?: string
}

const ScaleSelector = (props: Props): JSX.Element => {
  const [state, setState] = useState<State>({
    clef: props.clef,
    note: props.note,
    type: props.type,
    direction: props.direction
  })

  const onFormChange = (newState: State): void => {
    setState(newState)
    onStateChange(newState)
  }

  const onStateChange = (state: State): void => {
    if (state.clef === undefined ||
      state.note === undefined ||
      state.type === undefined ||
      state.direction === undefined) {
      return
    }
    props.onChange(
      state.clef,
      state.note,
      state.type,
      state.direction
    )
  }

  return (
    <Container>
      <Form>
        <Row>
          <Col>
            <NoteSelector
              value={state.note}
              onChange={note => { onFormChange({ ...state, note }) }}
            />
          </Col>
          <Col>
            <ClefSelector
              value={state.clef}
              onChange={clef => { onFormChange({ ...state, clef }) }}
            />
          </Col>
          <Col>
            <RadioButtons
              values={['major', 'minor-harmonic', 'minor-melodic']
                .map(it => ({
                  value: it,
                  label: it.split('-').map(capitalizeFirstCharacter).join(' ')
                }))}
              name='type'
              value={state.type}
              onChange={type => { onFormChange({ ...state, type }) }}
            />
          </Col>
          <Col>
            <RadioButtons
              values={['ascending', 'descending']
                .map(it => ({
                  value: it,
                  label: capitalizeFirstCharacter(it)
                }))}
              name='direction'
              value={state.direction}
              onChange={direction => { onFormChange({ ...state, direction }) }}
            />
          </Col>
        </Row>
      </Form>
    </Container>
  )
}

export default ScaleSelector
