import {BrowserRouter} from "react-router-dom";
import NavBar from "./component-old/NavBar";
import {Route, Routes} from "react-router";
import Clef from "./component-old/clef";
import KeySignature from "./component-old/scale";
import React from "react";
import Interval from "./component-old/interval";
import Scale from "./component-old/scale";
import 'bootstrap/dist/css/bootstrap.min.css'

export default () => {
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
