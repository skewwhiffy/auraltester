import {Notation} from 'react-abc'
import Centre from "../../component/Centre";
import NoteSelector from "../../component/selector/NoteSelector";
import {useState} from "react";
import {Clef, Note} from "../../common/types";
import {useQuery} from "@tanstack/react-query";
import api from "../../api/api";
import SpinUntilReady from "../../component/SpinUntilReady";
import ClefSelector from "../../component/selector/ClefSelector";

const KeySignatureExample = () => {
    const [clef, setClef] = useState<Clef | undefined>()
    const [key, setKey] = useState<Note | undefined>()
    const getKeySignatureAbc = useQuery({
        queryKey: ['keySignature', key],
        queryFn: async () => {
            return await api.getKeySignature({
                clef: clef!,
                key: key!,
            })
        },
        enabled: !!clef && !!key
    })
    return (
        <>
            <Centre>
                <SpinUntilReady isLoading={getKeySignatureAbc.isLoading}>
                    {getKeySignatureAbc.data?.data.abc && <Notation notation={getKeySignatureAbc.data.data.abc}/>}
                </SpinUntilReady>
            </Centre>
            <NoteSelector showDoubles={false} title="Select your note" value={key} onChange={setKey}/>
            <ClefSelector title="Select your clef" onChange={setClef}/>
        </>
    )
}

export default KeySignatureExample

