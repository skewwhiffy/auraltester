import {PropsWithChildren} from "react";

export default H1

function H1({children}: PropsWithChildren) {
  return (
    <h1 className="text-xl text-center py-4">
      {children}
    </h1>
  )
}
