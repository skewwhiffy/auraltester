import 'bootstrap/dist/css/bootstrap.min.css'
import {RouterProvider} from 'react-router-dom'
import {createBrowserRouter} from 'react-router-dom'
import {routesConfig} from "./application/routesConfig";
import './index.css'

export default () => {
  const router = createBrowserRouter(routesConfig);
  return (
    <RouterProvider router={router}/>
  )
}
