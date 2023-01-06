import axios from 'axios'
import { useState } from 'react'
import { Notation } from 'react-abc'
import { Container, Row } from 'react-bootstrap'
import ScaleSelector from './selector/ScaleSelector'

interface State {
  withKeySignature: string,
  withoutKeySignature: string
}

const Scale = () => {
  const [state, setState] = useState<State>({
    withKeySignature: '',
    withoutKeySignature: ''
  })
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
        <ScaleSelector onChange={scaleSelected} />
      </Row>
    </Container>
  )
}

export default Scale