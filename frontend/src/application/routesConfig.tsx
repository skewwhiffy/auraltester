import {Outlet, RouteObject} from "react-router";
import Layout from "./Layout";
import clefRoutes from "./clef/clefRoutes";
import intervalRoutes from "./interval/intervalRoutes";

const routesConfig: RouteObject[] = [
  {
    path: '',
    element: <Layout><Outlet/></Layout>,
    errorElement: <h1>Oh no!</h1>,
    children: [
      ...clefRoutes,
      ...intervalRoutes,
    ],
  },
  {
    path: '*',
    element: <h1>404</h1>,
  },
];

export default routesConfig