import Scale from './component/scale'
import Interval from './component/interval'
import NavBar from './component/NavBar'
import { BrowserRouter, Route, Routes } from 'react-router-dom'
import 'bootstrap/dist/css/bootstrap.min.css'
import Clef from './component/clef'
import { FunctionComponent } from 'react'
import KeySignature from './component/keySignature'
import { RouterProvider } from 'react-router-dom'
import { createBrowserRouter } from 'react-router-dom'
import {routesConfig} from "./application/routesConfig";

export default () => {
  const router = createBrowserRouter(routesConfig);
  return (
    <RouterProvider router={router} />
  )
}

const AppOld: FunctionComponent = () => {
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
