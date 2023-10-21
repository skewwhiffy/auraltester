import { PropsWithChildren } from "react"
import { Link } from "react-router-dom"

interface TabBarLinkProps extends PropsWithChildren {
  to: any
}

const TabBarLink = ({ to, children }: TabBarLinkProps) => {
  return <Link className="hover:underline" to={to}>{children}</Link>
}
const TabBar = ({ children }: PropsWithChildren) => {
  return (
    <>
      <nav className="flex justify-between p-8">
        <div className="flex justify-start list-none gap-4">
          {children}
        </div>
      </nav>
    </>
  )
}

TabBar.Link = TabBarLink

export default TabBar