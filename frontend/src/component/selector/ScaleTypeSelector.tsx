import {ScaleType} from "../../common/types"
import Centre from "../Centre"
import FancyRadioButtons from "../FancyRadioButtons"
import H1 from "../H1"

interface ScaleTypeSelectorProps {
    title?: string | null,
    value?: ScaleType | null,

    onChange(note: ScaleType): void
}

const ScaleTypeSelector = ({title, value, onChange}: ScaleTypeSelectorProps) => {
    return (
        <>
            {title && <Centre><H1>{title}</H1></Centre>}
            <Centre>
                <FancyRadioButtons onChange={onChange} value={value}>
                    <FancyRadioButtons.Item value="major">Major</FancyRadioButtons.Item>
                    <FancyRadioButtons.Item value="minor-harmonic">Minor harmonic</FancyRadioButtons.Item>
                    <FancyRadioButtons.Item value="minor-melodic">Minor melodic</FancyRadioButtons.Item>
                </FancyRadioButtons>
            </Centre>
        </>
    )
}

export default ScaleTypeSelector
