import axios from 'axios'
import { useState } from 'react'
import { Notation } from 'react-abc'
import { Container, Row, Col } from 'react-bootstrap'
import ClefSelector from './selector/ClefSelector'
import KeySignatureSelector from './selector/KeySignatureSelector'

interface State {
  clef: string
  keySignature: string
  abc: string
}

const KeySignature = (): JSX.Element => {
  const [state, setState] = useState<State>({
    clef: 'treble',
    keySignature: 'C',
    abc: ''
  })

  const stateChanged = (newState: State): void => {
    if (newState.clef && newState.keySignature) {
      (async () => {
        const response = await axios.get('api/keySignature', {
          params: {
            clef: newState.clef,
            keySignature: newState.keySignature
          }
        })
        setState({ ...newState, abc: response.data.abc })
      })()
      return
    }
    setState({ ...newState, abc: '' })
  }

  const clefSelected = (clef: string): void => {
    stateChanged({ ...state, clef })
  }

  const keySignatureSelected = (keySignature: string): void => {
    stateChanged({ ...state, keySignature })
  }

  return (
    <Container>
      <Row>
        <Notation notation={state.abc} />
      </Row>
      <Row>
        <Col>
          <ClefSelector onChange={clefSelected} />
        </Col>
        <Col>
          <KeySignatureSelector onChange={keySignatureSelected} />
        </Col>
      </Row>
    </Container>
  )
}

export default KeySignature
