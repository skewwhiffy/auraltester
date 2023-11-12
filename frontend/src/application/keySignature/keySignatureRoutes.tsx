import {Outlet} from "react-router";
import KeySignatureTabs from "./KeySignatureTabs";
import KeySignatureNotes from "./KeySignatureNotes";
import KeySignatureExample from "./KeySignatureExample";

const keySignatureRoutes = [
    {
        path: 'key-signature',
        element: <KeySignatureTabs><Outlet/></KeySignatureTabs>,
        children: [{
            path: '',
            element: <KeySignatureNotes/>
        }, {
            path: 'example',
            element: <KeySignatureExample/>
        }]
    }
]

export default keySignatureRoutes
