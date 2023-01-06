import axios from 'axios'
import { useState } from 'react'
import { Notation } from 'react-abc'
import { Container, Row, Col } from 'react-bootstrap'
import ClefSelector from './selector/ClefSelector'
import KeySignatureSelector from './selector/KeySignatureSelector'

interface State {
  clef: string,
  keySignature: string,
  abc: string
}

const KeySignature = () => {
  const [state, setState] = useState<State>({
    clef: 'treble',
    keySignature: 'C',
    abc: ''
  })
  
  const stateChanged = async (newState: State) => {
    let abc = ''
    if (newState.clef && newState.keySignature) {
      const response = await axios.get('api/keySignature', {
        params: {
          clef: newState.clef,
          keySignature: newState.keySignature
        }
      })
      const json = response.data
      abc = json.abc
    }
    setState({ ...newState, abc })
  }

  const clefSelected = async (clef: string) => {
    stateChanged({ ...state, clef })
  }

  const keySignatureSelected = async (keySignature: string) => {
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