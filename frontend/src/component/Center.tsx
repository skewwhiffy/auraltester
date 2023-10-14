import {PropsWithChildren} from "react";

export default Centre

function Centre({ children }: PropsWithChildren) {
  return <p className="content-center text-center justify-evenly flex">{children}</p>
}
