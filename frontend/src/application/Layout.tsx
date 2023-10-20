import { PropsWithChildren } from "react";
import TopBar from "./TopBar";

const Layout = ({ children }: PropsWithChildren) => {
  return (
    <>
      <TopBar />
      <div className="container mx-auto p-4 bg-white rounded-lg shadow-md p-4">
        {children}
      </div>
    </>
  )
}

export default Layout;