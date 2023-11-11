import {Accidental, Note, NoteName} from "../../common/types";
import H1 from "../H1";
import Centre from "../Centre";
import FancyRadioButtons from "../FancyRadioButtons";

interface IntervalSelectorProps {
    title?: string | null,
    value?: Note | null,

    onChange(note: Note): void
}

export const KeySignatureSelector = ({title, value, onChange}: IntervalSelectorProps) => {
    const keysString = "CbAbGbEbDbBbAbF EbC BbG F D C A G E D B A F#E C#B G#F#D#C#A#"
    const getKeys = ((source: string): string[] => {
        if (source == "") {
            return []
        }
        return [source.substring(0, 4), ...getKeys(source.substring(4))]
    })
    const keys: { text: string, note: Note }[] = getKeys(keysString)
        .map(it => ({
            text: `${it.substring(0, 2)} / ${it.substring(2)}m`,
            note: {
                name: it[0] as NoteName,
                accidental: it[1].trim() as Accidental
            }
        }))
    const blah = (note: Note) => {
        onChange(note)
    }
    return (
        <>
            {title && <Centre><H1>{title}</H1></Centre>}
            <Centre>
                <FancyRadioButtons
                    value={value}
                    onChange={blah}
                >
                    <div className="flex">
                        {keys.map(it =>
                            <FancyRadioButtons.Item key={it.text} value={it.note}>
                                {it.text}
                            </FancyRadioButtons.Item>
                        )}
                    </div>
                </FancyRadioButtons>
            </Centre>
        </>
    )
}