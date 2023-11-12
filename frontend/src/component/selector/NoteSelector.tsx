import {useState} from "react"
import {Accidental, Note, NoteName} from "../../common/types"
import Centre from "../Centre"
import FancyRadioButtons from "../FancyRadioButtons"
import H1 from "../H1"
import NoteNameSelector from "./NoteNameSelector";

interface NoteSelectorProps {
    title?: string | null,
    value?: Note | null,
    showDoubles?: boolean

    onChange(note: Note): void
}

const NoteSelector = ({title, value, onChange, showDoubles = true}: NoteSelectorProps) => {
    const [noteName, setNoteName] = useState<NoteName | undefined>(value?.name)
    const [accidental, setAccidental] = useState<Accidental | undefined>(value?.accidental)

    const onNoteNameChange = (noteName: NoteName) => {
        setNoteName(noteName)
        if (accidental !== undefined && accidental !== null) {
            onChange({name: noteName, accidental})
        }
    }

    const onAccidentalChange = (accidental: Accidental) => {
        setAccidental(accidental)
        if (noteName !== undefined && noteName !== null) {
            onChange({name: noteName, accidental})
        }
    }

    return (
        <>
            {title && <Centre><H1>{title}</H1></Centre>}
            <NoteNameSelector value={noteName} onChange={onNoteNameChange}/>
            <Centre>
                <FancyRadioButtons
                    value={accidental}
                    onChange={onAccidentalChange}
                >
                    {showDoubles && <FancyRadioButtons.Item value='x'>Double sharp</FancyRadioButtons.Item>}
                    <FancyRadioButtons.Item>#</FancyRadioButtons.Item>
                    <FancyRadioButtons.Item value=''>Natural</FancyRadioButtons.Item>
                    <FancyRadioButtons.Item>b</FancyRadioButtons.Item>
                    {showDoubles && <FancyRadioButtons.Item value='bb'>Double flat</FancyRadioButtons.Item>}
                </FancyRadioButtons>
            </Centre>
        </>
    )
}

export default NoteSelector