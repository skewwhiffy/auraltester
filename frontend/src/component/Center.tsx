import {PropsWithChildren} from "react";

export default Centre

function Centre({ children }: PropsWithChildren) {
  return <div className="content-center text-center justify-evenly flex">{children}</div>
}
