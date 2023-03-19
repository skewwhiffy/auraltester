import Scale from './component/Scale'
import Interval from './component/Interval'
import NavBar from './component/NavBar'
import { BrowserRouter, Route, Routes } from 'react-router-dom'
import 'bootstrap/dist/css/bootstrap.min.css'
import Clef from './component/clef'
import { FunctionComponent } from 'react'
import KeySignature from './component/keySignature'

const App: FunctionComponent = () => {
  return (
    <div className="App">
      <BrowserRouter>
        <NavBar />
        <Routes>
          <Route index element={<p>Hello</p>} />
          <Route path="clefs/*" element={<Clef />} />
          <Route path="key-signatures/*" element={<KeySignature />} />
          <Route path="scales/*" element={<Scale />} />
          <Route path="intervals/*" element={<Interval />} />
        </Routes>
      </BrowserRouter>
    </div>
  )
}

export default App
