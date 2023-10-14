import {PropsWithChildren} from "react";

export default P

function P({children}: PropsWithChildren) {
  return (
    <p className="text-lg pt-8">
      {children}
    </p>
  )
}
