import {Outlet, RouteObject} from "react-router";
import ClefExample from "./ClefExample";

const clefRoutes: RouteObject[] = [
  {
    path: 'clef',
    element: <Outlet/>,
    children: [{
      path: '',
      element: <ClefExample />
    }]
  }
]

export default clefRoutes
