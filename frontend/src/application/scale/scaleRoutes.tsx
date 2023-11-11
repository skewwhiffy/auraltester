import {Outlet} from "react-router";
import ScaleTabs from "./ScaleTabs";
import ScaleNotes from "./ScaleNotes";
import ScaleExample from "./ScaleExample";

const scaleRoutes = [
    {
        path: 'scale',
        element: <ScaleTabs><Outlet/></ScaleTabs>,
        children: [{
            path: '',
            element: <ScaleNotes/>
        }, {
            path: 'example',
            element: <ScaleExample/>
        }]
    }
]

export default scaleRoutes