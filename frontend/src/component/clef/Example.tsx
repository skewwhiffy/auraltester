import { Container, Row } from "react-bootstrap"
import { Notation } from 'react-abc'
import ClefSelector from "../selector/ClefSelector"
import { useEffect, useState } from "react"
import { api } from "../../util"
import axios from "axios"

interface State {
  clef: string
  abc: string
}

const Example = (): JSX.Element => {
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
    if (!clef) return
    const clefResponse = await api.getClef(clef)
    setState({
      ...state,
      ...clefResponse
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

export default Example