import {PropsWithChildren} from "react"
import TabBar from "../../component/nav/TabBar"

const ClefTabs = ({children}: PropsWithChildren) => {
    return (
        <>
            <TabBar>
                <TabBar.Link to="./">INFO</TabBar.Link>
                <TabBar.Link to="./example">EXAMPLE</TabBar.Link>
                <TabBar.Link to="./quiz">QUIZ</TabBar.Link>
            </TabBar>
            {children}
        </>
    )
}

export default ClefTabs