import { useState, useEffect } from 'react'
import { Container, Row } from 'react-bootstrap'
import { Notation } from 'react-abc'
import ClefSelector from './selector/ClefSelector'
import axios from 'axios'

interface State {
  clef: string
  abc: string
}

const Clef = (): JSX.Element => {
  let initialized = false
  const [state, setState] = useState<State>({
    clef: '',
    abc: '',
  })
  
  useEffect(() => {
    if (initialized) return
    initialized = true
    clefSelected(state.clef)
  }, [])

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
        <ClefSelector value={state.clef} onChange={clefSelected} />
      </Row>
    </Container>
  )
}

export default Clef
