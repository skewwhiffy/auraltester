import axios from 'axios'
import React from 'react'
import { Notation } from 'react-abc'
import { Container, Row } from 'react-bootstrap'
import ScaleSelector from './ScaleSelector'

interface Props { }

interface State {
  abc: string
}

class Scale extends React.Component<Props, State> {
  constructor(props: Props) {
    super(props)
    this.state = {
      abc: ''
    }
  }

  scaleSelected = async (
    clef: string,
    note: string,
    type: string,
    direction: string,
    withKeySignature: boolean
  ) => {
    if ([clef, note, type, direction].includes('')) {
      this.setState({
        ...this.state,
        abc: ''
      })
      return
    }

    const response = await axios.get('api/scale', {
      params: {
        clef,
        note,
        scaleType: type,
        direction,
        withKeySignature
      }
    })
    const json = response.data
    const newAbc = json.abc
    this.setState({
      ...this.state,
      abc: newAbc
    })
  }

  render() {
    return (
      <Container>
        <Row>
          <Notation notation={this.state.abc} />
        </Row>
        <Row>
          <ScaleSelector onChange={this.scaleSelected} />
        </Row>
      </Container>
    )
  }
}

export default Scale