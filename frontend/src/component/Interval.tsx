import React from 'react'
import { Container, Row } from 'react-bootstrap'
import { Notation } from 'react-abc'
import IntervalSelector from './selector/IntervalSelector'
import axios from 'axios'

interface State {
  abc: string
}

class Interval extends React.Component<{}, State> {
  constructor(props: {}) {
    super(props)
    this.state = {
      abc: ''
    }
  }

  intervalSelected = async (
    clef: string,
    bottomNote: string,
    intervalQuality: string,
    intervalSize: string,
    keySignature: string
  ) => {
    if ([clef, bottomNote, intervalQuality, intervalSize, keySignature].includes('')) {
      this.setState({
        ...this.state,
        abc: ''
      })
      return
    }

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
    this.setState({
      ...this.state,
      abc: json.abc
    })
  }

  render() {
    return (
      <Container>
        <Row>
          <Notation notation={this.state.abc} />
        </Row>
        <Row>
          <IntervalSelector onChange={this.intervalSelected} />
        </Row>
      </Container>
    )
  }
}
export default Interval