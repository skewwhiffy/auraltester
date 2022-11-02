import axios from 'axios'
import React from 'react'
import { Notation } from 'react-abc'
import { Container, Row } from 'react-bootstrap'
import ScaleSelector from './ScaleSelector'

interface Props { }

interface State {
  withKeySignature: string,
  withoutKeySignature: string
}

class Scale extends React.Component<Props, State> {
  constructor(props: Props) {
    super(props)
    this.state = {
      withKeySignature: '',
      withoutKeySignature: ''
    }
  }

  scaleSelected = async (
    clef: string,
    note: string,
    type: string,
    direction: string
  ) => {
    if ([clef, note, type, direction].includes('')) {
      this.setState({
        ...this.state,
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
    this.setState({
      ...this.state,
      withKeySignature: json.withKeySignature,
      withoutKeySignature: json.withoutKeySignature
    })
  }

  render() {
    return (
      <Container>
        <Row>
          <Notation notation={this.state.withoutKeySignature} />
        </Row>
        <Row>
          <Notation notation={this.state.withKeySignature} />
        </Row>
        <Row>
          <ScaleSelector onChange={this.scaleSelected} />
        </Row>
      </Container>
    )
  }
}

export default Scale