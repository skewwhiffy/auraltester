import {Outlet, RouteObject} from "react-router";
import IntervalNotes from "./IntervalNotes";
import IntervalTabs from "./IntervalTabs";

const clefRoutes: RouteObject[] = [
  {
    path: 'interval',
    element: <IntervalTabs><Outlet/></IntervalTabs>,
    children: [{
      path: '',
      element: <IntervalNotes />
    }]
  }
]

export default clefRoutes