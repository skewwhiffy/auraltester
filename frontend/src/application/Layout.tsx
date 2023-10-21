import { PropsWithChildren } from "react";
import TopBar from "./TopBar";

const Layout = ({ children }: PropsWithChildren) => {
  return (
    <div className="container mx-auto bg-white rounded-lg shadow-md p-4">
      <TopBar />
      {children}
    </div>
  )
}

export default Layout;