import { useQuery } from "@tanstack/react-query";
import SpinUntilReady from "../component/SpinUntilReady";
import api from "../api/api";
import { Link } from "react-router-dom";
import NavBar from "../component/nav/NavBar";
import NavBarLink from "../component/nav/NavBarLink";

const TopBar = () => {
  const getVersionQuery = useQuery({
    queryKey: ['get-version'],
    queryFn: () => api.getVersion()
  })

  return (
    <div className="flex justify-between">
      <NavBar>
        <NavBarLink to='/'>The Aural Tester</NavBarLink>
        <NavBarLink to='/clef'>Clef</NavBarLink>
        <NavBarLink to='/interval'>Interval</NavBarLink>
      </NavBar>
      <SpinUntilReady className="p-8" isLoading={getVersionQuery.isLoading}>
        <div className="p-8">
          Version: {getVersionQuery.data?.data.version}
        </div>
      </SpinUntilReady>
    </div>
  )
}

export default TopBar
