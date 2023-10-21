import H1 from "../H1";
import Centre from "../Centre";
import FancyRadioButtons from "../FancyRadioButtons";
import { Clef } from "../../common/types";

interface ClefSelectorProps {
  title?: string | null
  value?: Clef | null

  onChange(value: Clef): void
}

const ClefSelector = ({title, value, onChange}: ClefSelectorProps) => {
  return (
    <>
      {title && <Centre><H1>{title}</H1></Centre>}
      <Centre>
        <FancyRadioButtons value={value} onChange={onChange}>
          <FancyRadioButtons.Item value="bass">Bass</FancyRadioButtons.Item>
          <FancyRadioButtons.Item value="tenor">Tenor</FancyRadioButtons.Item>
          <FancyRadioButtons.Item value="alto">Alto</FancyRadioButtons.Item>
          <FancyRadioButtons.Item value="treble">Treble</FancyRadioButtons.Item>
        </FancyRadioButtons>
      </Centre>
    </>
  )
}

export default ClefSelector