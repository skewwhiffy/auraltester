import { PropsWithChildren } from "react"
import { Link } from "react-router-dom"

interface NavBarLinkProps extends PropsWithChildren {
  to: any
}

const NavBarLink = ({ children, to }: NavBarLinkProps) => {
  return <Link to={to}>{children}</Link>
}

export default NavBarLink