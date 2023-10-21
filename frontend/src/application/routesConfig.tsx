import {Outlet, RouteObject} from "react-router";
import Layout from "./Layout";
import clefRoutes from "./clef/clefRoutes";

const routesConfig: RouteObject[] = [
  {
    path: '',
    element: <Layout><Outlet/></Layout>,
    errorElement: <h1>Oh no!</h1>,
    children: [
      ...clefRoutes
    ],
  },
  {
    path: '*',
    element: <h1>404</h1>,
  },
];

export default routesConfig