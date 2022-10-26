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

  scaleSelected = async (clef: string, note: string, type: string) => {
    if (clef === '' || note === '' || type === '') {
      this.setState({
        ...this.state,
        abc: ''
      })
      return
    }
    console.log(encodeURI(`/api/scale/${clef}/${note}/${type}`))
    const response = await fetch(`/api/scale/${clef}/${encodeURIComponent(note)}/${type}`)
    const json = await response.json()
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