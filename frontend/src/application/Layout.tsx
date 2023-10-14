import {PropsWithChildren} from "react";
import TopBar from "./TopBar";

export default ({children}: PropsWithChildren) => {
  return (
    <>
      <TopBar/>
      <div className="container mx-auto p-4">
        <div className="bg-white rounded-lg shadow-md p-4">
          {children}
        </div>
      </div>
    </>
  )
}
