import React from 'react'
import { Container, Row } from 'react-bootstrap'
import { Notation } from 'react-abc'
import ClefSelector from './selector/ClefSelector'
import axios from 'axios'

interface State {
  abc: string
}

class Clef extends React.Component<{}, State> {
  constructor(props: {}) {
    super(props)
    this.state = {
      abc: ''
    }
  }
  
  clefSelected = async (clef: string) => {
    const response = await axios.get('api/clef', {
      params: {
        clef
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
          <ClefSelector onChange={this.clefSelected} />
        </Row>
      </Container>
    )
  }
}

export default Clef