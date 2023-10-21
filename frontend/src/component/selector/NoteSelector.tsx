import { useState } from "react"
import { Accidental, Note, NoteName } from "../../common/types"
import Centre from "../Centre"
import FancyRadioButtons from "../FancyRadioButtons"
import H1 from "../H1"

interface NoteSelectorProps {
  title?: string | null,
  value?: Note | null,
  onChange(note: Note): void
}

const NoteSelector = ({ title, value, onChange }: NoteSelectorProps) => {
  const [noteName, setNoteName] = useState(value?.[0])
  const [accidental, setAccidental] = useState(value?.[1])

  const onNoteNameChange = (noteName: NoteName) => {
    setNoteName(noteName)
    if (accidental) {
      onChange([noteName, accidental])
    }
  }

  const onAccidentalChange = (accidental: Accidental) => {
    setAccidental(accidental)
    if (noteName) {
      onChange([noteName, accidental])
    }
  }

  return (
    <>
      {title && <Centre><H1>{title}</H1></Centre>}
      <Centre>
        <FancyRadioButtons
          value={noteName}
          onChange={onNoteNameChange}
        >
          <FancyRadioButtons.Item>A</FancyRadioButtons.Item>
          <FancyRadioButtons.Item>B</FancyRadioButtons.Item>
          <FancyRadioButtons.Item>C</FancyRadioButtons.Item>
          <FancyRadioButtons.Item>D</FancyRadioButtons.Item>
          <FancyRadioButtons.Item>E</FancyRadioButtons.Item>
          <FancyRadioButtons.Item>F</FancyRadioButtons.Item>
          <FancyRadioButtons.Item>G</FancyRadioButtons.Item>
        </FancyRadioButtons>
      </Centre>
      <Centre>
        <FancyRadioButtons
          value={accidental}
          onChange={onAccidentalChange}
        >
          <FancyRadioButtons.Item value='x'>Double sharp</FancyRadioButtons.Item>
          <FancyRadioButtons.Item>#</FancyRadioButtons.Item>
          <FancyRadioButtons.Item value=''>Natural</FancyRadioButtons.Item>
          <FancyRadioButtons.Item>b</FancyRadioButtons.Item>
          <FancyRadioButtons.Item value='bb'>Double flat</FancyRadioButtons.Item>
        </FancyRadioButtons>
      </Centre>
    </>
  )
}

export default NoteSelector