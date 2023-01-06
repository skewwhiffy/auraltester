import axios from 'axios'
import React from 'react'
import { Notation } from 'react-abc'
import { Container, Row, Col } from 'react-bootstrap'
import ClefSelector from './selector/ClefSelector'
import KeySignatureSelector from './selector/KeySignatureSelector'

interface Props { }

interface State {
  clef: string,
  keySignature: string,
  abc: string
}

class KeySignature extends React.Component<Props, State> {
  constructor(props: Props) {
    super(props)
    this.state = {
      clef: 'treble',
      keySignature: 'C',
      abc: ''
    }
  }

  render() {
    return (
      <Container>
        <Row>
          <Notation notation={this.state.abc} />
        </Row>
        <Row>
          <Col>
            <ClefSelector onChange={this.clefSelected} />
          </Col>
          <Col>
            <KeySignatureSelector onChange={this.keySignatureSelected} />
          </Col>
        </Row>
      </Container>
    )
  }

  private clefSelected = async (clef: string) => {
    this.stateChanged({ ...this.state, clef })
  }

  private keySignatureSelected = async (keySignature: string) => {
    this.stateChanged({ ...this.state, keySignature })
  }

  private stateChanged = async (newState: State) => {
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
    this.setState({ ...newState, abc })
  }
}

export default KeySignature