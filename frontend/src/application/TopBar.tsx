import { useQuery } from "@tanstack/react-query";
import SpinUntilReady from "../component/SpinUntilReady";
import api from "../api/api";

const TopBar = () => {
  const getVersionQuery = useQuery({
    queryKey: ['get-version'],
    queryFn: () => api.getVersion()
  })

  return (
    <nav className="flex justify-between p-8">
      <div className="flex justify-start gap-4">
        <h1 className="">The Aural Tester</h1>
        <div>HELLO WORLD</div>
        <div>HELLO WORLD</div>
      </div>
      <SpinUntilReady isLoading={getVersionQuery.isLoading}>
        <div>Version: {getVersionQuery.data?.data.version}</div>
      </SpinUntilReady>
    </nav>
  )
}

export default TopBar