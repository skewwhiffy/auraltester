import {Outlet, RouteObject} from "react-router";
import ClefExample from "./ClefExample";
import ClefNotes from "./ClefNotes";
import ClefTabs from "./ClefTabs";
import ClefQuiz from "./ClefQuiz";

const clefRoutes: RouteObject[] = [
    {
        path: 'clef',
        element: <ClefTabs><Outlet/></ClefTabs>,
        children: [{
            path: '',
            element: <ClefNotes/>
        }, {
            path: 'example',
            element: <ClefExample/>
        }, {
            path: 'quiz',
            element: <ClefQuiz/>
        }]
    }
]

export default clefRoutes