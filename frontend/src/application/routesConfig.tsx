import {Outlet, RouteObject} from "react-router";
import Layout from "./Layout";

export const routesConfig: RouteObject[] = [
  {
    path: '/',
    element: <Layout>
      <Outlet/>
    </Layout>,
    errorElement: <h1>Oh no!</h1>,
    children: [],
  },
  {
    path: '*',
    element: <h1>404</h1>,
  },
];
