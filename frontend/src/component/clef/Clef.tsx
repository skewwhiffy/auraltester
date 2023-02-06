import { useState, useEffect } from 'react'
import { Container, Row } from 'react-bootstrap'
import { Notation } from 'react-abc'
import { Route, Routes } from 'react-router-dom'
import ClefSelector from '../selector/ClefSelector'
import axios from 'axios'
import Example from './Example'

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
    if (!clef) return
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
    <Routes>
      <Route index element={ <p>NOTES</p> } />
      <Route path="example/" element={<Example/>} />
    </Routes>
  )
}

export default Clef
