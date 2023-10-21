import { PropsWithChildren } from "react"

const NavBar = ({ children }: PropsWithChildren) => {
  return (
    <nav className="flex justify-between p-8">
      <div className="flex justify-start gap-4">
        {children}
      </div>
    </nav>
  )
}

export default NavBar