import React, { useState } from 'react'
import { Container, Row } from 'react-bootstrap'
import { Notation } from 'react-abc'
import ClefSelector from './selector/ClefSelector'
import axios from 'axios'

interface State {
  abc: string
}

const Clef = () => {
  const [state, setState] = useState<State>({ abc: '' })

  const clefSelected = async (clef: string) => {
    const response = await axios.get('api/clef', {
      params: {
        clef
      }
    })
    const json = response.data
    setState({
      ...state,
      abc: json.abc
    })
  }

  return (
    <Container>
      <Row>
        <Notation notation={state.abc} />
      </Row>
      <Row>
        <ClefSelector onChange={clefSelected} />
      </Row>
    </Container>
  )
}

class ClefOld extends React.Component<{}, State> {

}

export default Clef