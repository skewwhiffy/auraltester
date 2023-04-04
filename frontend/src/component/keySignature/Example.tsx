import { useEffect, useState } from 'react'
import { Notation } from 'react-abc'
import { Container, Row, Col } from 'react-bootstrap'
import api from '../../util/api'
import ClefSelector from '../selector/ClefSelector'
import KeySignatureSelector from '../selector/KeySignatureSelector'

interface State {
  clef: string
  keySignature: string
  abc: string
}

const Example = (): JSX.Element => {
  let initialized = false
  const [state, setState] = useState<State>({
    clef: 'treble',
    keySignature: 'C',
    abc: ''
  })

  useEffect(() => {
    if (initialized) {
      return
    }
    initialized = true
    stateChanged(state)
  }, [])

  const stateChanged = async (newState: State): Promise<void> => {
    if (newState.clef && newState.keySignature) {
      const response = await api.getKeySignature(newState)
      setState({ ...newState, abc: response.abc })
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
          <ClefSelector value={state.clef} onChange={clefSelected} />
        </Col>
        <Col>
          <KeySignatureSelector value={state.keySignature} onChange={keySignatureSelected} />
        </Col>
      </Row>
    </Container>
  )
}

export default Example
