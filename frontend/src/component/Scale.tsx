import axios from 'axios'
import { useEffect, useState } from 'react'
import { Notation } from 'react-abc'
import { Container, Row } from 'react-bootstrap'
import ScaleSelector from './selector/ScaleSelector'

interface State {
  clef: string
  note: string
  type: string
  direction: string
  withKeySignature: string
  withoutKeySignature: string
}

const Scale = () => {
  let initialized = false

  const [state, setState] = useState<State>({
    clef: 'treble',
    note: 'C',
    type: 'major',
    direction: 'ascending',
    withKeySignature: '',
    withoutKeySignature: ''
  })

  useEffect(() => {
    if (initialized) {
      return
    }
    initialized = true
    scaleSelected(state.clef, state.note, state.type, state.direction)
  }, [])

  const scaleSelected = async (
    clef: string,
    note: string,
    type: string,
    direction: string
  ) => {
    if ([clef, note, type, direction].includes('')) {
      setState({
        ...state,
        withKeySignature: '',
        withoutKeySignature: ''
      })
      return
    }

    const response = await axios.get('api/scale', {
      params: {
        clef,
        note,
        scaleType: type,
        direction
      }
    })
    const json = response.data
    setState({
      ...state,
      withKeySignature: json.withKeySignature,
      withoutKeySignature: json.withoutKeySignature
    })
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