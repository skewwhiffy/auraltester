import H1 from "../H1";
import Center from "../Center";
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
      {title && <Center><H1>{title}</H1></Center>}
      <Center>
        <FancyRadioButtons value={value} onChange={onChange}>
          <FancyRadioButtons.Item>Bass</FancyRadioButtons.Item>
        </FancyRadioButtons>
      </Center>
    </>
  )
}
