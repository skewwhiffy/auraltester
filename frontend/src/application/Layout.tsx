import {PropsWithChildren} from "react";
import TopBar from "./TopBar";

export default ({children}: PropsWithChildren) => {
  return (
    <>
      <TopBar />
      {children}
    </>
  )
}
