import { PropsWithChildren } from "react"
import { Link } from "react-router-dom"

const ClefTabs = ({ children }: PropsWithChildren) => {
  return (
    <>
      <nav className="flex justify-between p-8">
        <div className="flex justify-start list-none gap-4">
          <Link className="hover:underline" to="./">INFO</Link>
          <Link className="hover:underline" to="./example">EXAMPLE</Link>
        </div>
      </nav>
      {children}
    </>
  )
}

export default ClefTabs