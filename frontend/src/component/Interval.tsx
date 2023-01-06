import { useState } from 'react'
import { Container, Row } from 'react-bootstrap'
import { Notation } from 'react-abc'
import IntervalSelector from './selector/IntervalSelector'
import axios from 'axios'

interface State {
  abc: string
}

const Interval = (): JSX.Element => {
  const [state, setState] = useState<State>({ abc: '' })

  const intervalSelected = (
    clef: string,
    bottomNote: string,
    intervalQuality: string,
    intervalSize: string,
    keySignature: string
  ): void => {
    if ([clef, bottomNote, intervalQuality, intervalSize, keySignature].includes('')) {
      setState({
        ...state,
        abc: ''
      })
      return
    }

    (async () => {
      const response = await axios.get('api/interval', {
        params: {
          clef,
          bottomNote,
          intervalQuality,
          intervalSize,
          keySignature
        }
      })
      const json = response.data
      setState({
        ...state,
        abc: json.abc
      })
    })()
  }
  return (
    <Container>
      <Row>
        <Notation notation={state.abc} />
      </Row>
      <Row>
        <IntervalSelector onChange={intervalSelected} />
      </Row>
    </Container>
  )
}

export default Interval
