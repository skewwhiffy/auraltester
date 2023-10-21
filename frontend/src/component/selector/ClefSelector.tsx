import H1 from "../H1";
import Centre from "../Centre";
import FancyRadioButtons from "../FancyRadioButtons";

export default ClefSelector

interface Props {
  title?: string | null
  value?: string | null

  onChange(value: string): void
}

function ClefSelector({title, value, onChange}: Props) {
  return (
    <>
      {title && <Centre><H1>{title}</H1></Centre>}
      <Centre>
        <FancyRadioButtons value={value} onChange={onChange}>
          <FancyRadioButtons.Item>Bass</FancyRadioButtons.Item>
        </FancyRadioButtons>
      </Centre>
    </>
  )
}
