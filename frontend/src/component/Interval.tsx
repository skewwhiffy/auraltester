import React from 'react'
import { Container, Row } from 'react-bootstrap'
import { Notation } from 'react-abc'
import IntervalSelector from './selector/IntervalSelector'

interface Props { }

interface State {
  abc: string
}

class Interval extends React.Component<Props, State> {
  constructor(props: Props) {
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
    console.log("Hello all")
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