import {Notation} from 'react-abc'
import {useState} from "react"
import {Clef, Direction, Note, ScaleType} from "../../common/types"
import Centre from "../../component/Centre"
import api from "../../api/api"
import {useQuery} from "@tanstack/react-query"
import SpinUntilReady from "../../component/SpinUntilReady"
import ClefSelector from '../../component/selector/ClefSelector'
import NoteSelector from '../../component/selector/NoteSelector'
import ScaleTypeSelector from "../../component/selector/ScaleTypeSelector";
import DirectionSelector from "../../component/selector/DirectionSelector";

const ScaleExample = () => {
    const [clef, setClef] = useState<Clef | undefined>()
    const [note, setNote] = useState<Note | undefined>()
    const [type, setType] = useState<ScaleType | undefined>()
    const [direction, setDirection] = useState<Direction | undefined>()
    const getScaleAbc = useQuery({
        queryKey: ['scale', clef, note, type, direction],
        queryFn: async () => {
            return await api.getScale({
                clef: clef!,
                note: note!,
                type: type!,
                direction: direction!
            })
        },
        enabled: !!clef && !!note && !!type && !!direction
    })
    return (
        <>
            <Centre>
                <SpinUntilReady isLoading={getScaleAbc.isLoading}>
                    {
                        getScaleAbc.data?.data.withoutKeySignature &&
                        <Notation notation={getScaleAbc.data.data.withoutKeySignature}/>
                    }
                    {
                        getScaleAbc.data?.data.withKeySignature &&
                        <Notation notation={getScaleAbc.data.data.withKeySignature}/>
                    }
                </SpinUntilReady>
            </Centre>
            <ClefSelector title="Select your clef" value={clef} onChange={setClef}/>
            <NoteSelector showDoubles={false} title="Select your note" value={note} onChange={setNote}/>
            <ScaleTypeSelector title="Select type of scale" value={type} onChange={setType}/>
            <DirectionSelector title="Select your direction" value={direction} onChange={setDirection}/>
        </>
    )
}

export default ScaleExample

