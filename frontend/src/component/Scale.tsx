import { useEffect, useState } from 'react'
import { Notation } from 'react-abc'
import { Container, Row } from 'react-bootstrap'
import { api } from '../util'
import ScaleSelector from './selector/ScaleSelector'

interface State {
  clef: string
  note: string
  type: string
  direction: string
  withKeySignature: string
  withoutKeySignature: string
}

const Scale = (): JSX.Element => {
  const [state, setState] = useState<State>({
    clef: 'treble',
    note: 'C',
    type: 'major',
    direction: 'ascending',
    withKeySignature: '',
    withoutKeySignature: ''
  })
  let initialized = false

  useEffect(() => {
    if (initialized) {
      return
    }
    initialized = true
    scaleSelected(state.clef, state.note, state.type, state.direction)
  }, [])

  const scaleSelected = (
    clef: string,
    note: string,
    type: string,
    direction: string
  ): void => {
    (async () => {
      const response = await api.getScale({
        clef,
        note,
        scaleType: type,
        direction
      })
      setState({
        ...state,
        withKeySignature: response.withKeySignature,
        withoutKeySignature: response.withoutKeySignature
      })
    })()
  }

  return (
    <Container>
      <Row>
        <Notation notation={state.withoutKeySignature} />
      </Row>
      <Row>
        <Notation notation={state.withKeySignature} />
      </Row>
      <Row>
        <ScaleSelector
          clef={state.clef}
          note={state.note}
          type={state.type}
          direction={state.direction}
          onChange={scaleSelected}
        />
      </Row>
    </Container>
  )
}

export default Scale
