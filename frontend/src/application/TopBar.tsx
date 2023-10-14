import {useQuery} from "@tanstack/react-query";
import SpinUntilReady from "../component/SpinUntilReady";
import api from "../api/api";

export default () => {
  const getVersionQuery = useQuery({
    queryKey: ['get-version'],
    queryFn: () => api.getVersion()
  })
  console.log(getVersionQuery.data?.data?.version)

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
