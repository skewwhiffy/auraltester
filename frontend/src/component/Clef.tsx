import { useState } from 'react'
import { Container, Row } from 'react-bootstrap'
import { Notation } from 'react-abc'
import ClefSelector from './selector/ClefSelector'
import axios from 'axios'

interface State {
  clef: string
  abc: string
}

const Clef = (): JSX.Element => {
  const [state, setState] = useState<State>({
    clef: '',
    abc: ''
  })

  const clefSelected = (clef: string): void => {
    (async () => {
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
    })()
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
