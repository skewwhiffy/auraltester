import { PropsWithChildren } from "react"
import TabBar from "../../component/nav/TabBar"

const IntervalTabs = ({ children }: PropsWithChildren) => {
  return (
    <>
      <TabBar>
        <TabBar.Link to="./">INFO</TabBar.Link>
        <TabBar.Link to="./example">EXAMPLE</TabBar.Link>
      </TabBar>
      {children}
    </>
  )
}

export default IntervalTabs