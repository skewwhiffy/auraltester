import React from 'react'
import Scale from './component/Scale'
import NavBar from './component/NavBar'
import { BrowserRouter, Route, Routes } from 'react-router-dom'
import 'bootstrap/dist/css/bootstrap.min.css';

class App extends React.Component {
  render() {
    return (
      <div className="App">
        <BrowserRouter>
          <NavBar />
          <Routes>
            <Route index element={<p>Hello</p>} />
            <Route path="scales" element={<Scale />} />
          </Routes>
        </BrowserRouter>
      </div>
    )
  }
}

export default App
