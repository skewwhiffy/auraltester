import {RouterProvider} from 'react-router-dom'
import {createBrowserRouter} from 'react-router-dom'
import routesConfig from "./application/routesConfig";
import './index.css'
import {QueryClient, QueryClientProvider} from "@tanstack/react-query";

function App() {
  const router = createBrowserRouter(routesConfig);
  const queryClient = new QueryClient()
  return (
    <QueryClientProvider client={queryClient}>
      <RouterProvider router={router}/>
    </QueryClientProvider>
  )
}

export default App
