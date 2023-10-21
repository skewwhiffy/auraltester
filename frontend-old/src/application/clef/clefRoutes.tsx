import {Outlet, RouteObject} from "react-router";
import ClefExample from "./ClefExample";
import ClefNotes from "./ClefNotes";

const clefRoutes: RouteObject[] = [
  {
    path: 'clef',
    element: <Outlet/>,
    children: [{
      path: '',
      element: <ClefNotes />
    }, {
      path: 'example',
      element: <ClefExample />
    }]
  }
]

export default clefRoutes
