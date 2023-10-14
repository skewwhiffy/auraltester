import { RouteObject } from "react-router";

export const routesConfig: RouteObject[] = [
  {
    path: '/',
    element: <h1>HELLO</h1>,
    errorElement: <h1>Oh no!</h1>,
    children: [
    ],
  },
  {
    path: '*',
    element: <h1>404</h1>,
    errorElement: <h1>Oh no!</h1>
  },
];
