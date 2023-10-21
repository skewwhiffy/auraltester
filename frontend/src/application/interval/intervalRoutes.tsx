import {Outlet, RouteObject} from "react-router";
import IntervalNotes from "./IntervalNotes";
import IntervalTabs from "./IntervalTabs";
import IntervalExample from "./IntervalExample";

const clefRoutes: RouteObject[] = [
  {
    path: 'interval',
    element: <IntervalTabs><Outlet/></IntervalTabs>,
    children: [{
      path: '',
      element: <IntervalNotes />
    }, {
      path: 'example',
      element: <IntervalExample />
    }]
  }
]

export default clefRoutes