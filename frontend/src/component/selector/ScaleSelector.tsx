import React, { useState } from 'react'
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
  onChange: OnChangeHandler
}

interface State {
  clef: string,
  note: string,
  type: string,
  direction: string
}

const ScaleSelector = (props: Props) => {
  const [state, setState] = useState<State>({
    clef: 'treble',
    note: 'C',
    type: 'major',
    direction: 'ascending'
  })

  const onFormChange = (newState: State) => {
    setState(newState)
    onStateChange(newState)
  }

  const onStateChange = (state: State) =>
    props.onChange(
      state.clef,
      state.note,
      state.type,
      state.direction
    )

  return (
    <Container>
      <Form>
        <Row>
          <Col>
            <NoteSelector
              defaultValue={state.note}
              onChange={note => onFormChange({ ...state, note })}
            />
          </Col>
          <Col>
            <ClefSelector
              defaultValue={state.clef}
              onChange={clef => onFormChange({ ...state, clef })}
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
              defaultValue={state.type}
              onChange={type => onFormChange({ ...state, type })}
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
              defaultValue={state.direction}
              onChange={direction => onFormChange({ ...state, direction })}
            />
          </Col>
        </Row>
      </Form>
    </Container>
  )
}

export default ScaleSelector