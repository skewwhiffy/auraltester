import Centre from "../Centre";
import FancyRadioButtons from "../FancyRadioButtons";
import {IntervalSize} from "../../common/types";
import H1 from "../H1";

interface Props {
    title?: string
    value?: IntervalSize

    onChange(value: IntervalSize): void
}

const IntervalSizeSelector = ({title, value, onChange}: Props) => {
    return (
        <>
            {title && <Centre><H1>{title}</H1></Centre>}
            <Centre>
                <FancyRadioButtons value={value} onChange={onChange}>
                    <FancyRadioButtons.Item value="1">Unison</FancyRadioButtons.Item>
                    <FancyRadioButtons.Item value="2">Second</FancyRadioButtons.Item>
                    <FancyRadioButtons.Item value="3">Third</FancyRadioButtons.Item>
                    <FancyRadioButtons.Item value="4">Fourth</FancyRadioButtons.Item>
                    <FancyRadioButtons.Item value="5">Fifth</FancyRadioButtons.Item>
                    <FancyRadioButtons.Item value="6">Sixth</FancyRadioButtons.Item>
                    <FancyRadioButtons.Item value="7">Seventh</FancyRadioButtons.Item>
                    <FancyRadioButtons.Item value="8">Octave</FancyRadioButtons.Item>
                </FancyRadioButtons>
            </Centre>
        </>
    )
}

export default IntervalSizeSelector