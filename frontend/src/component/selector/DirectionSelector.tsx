import Centre from "../Centre"
import FancyRadioButtons from "../FancyRadioButtons"
import H1 from "../H1"
import {Direction} from "../../common/types";

interface DirectionSelectorProps {
    title?: string | null,
    value?: Direction | null,

    onChange(direction: Direction): void
}

const DirectionSelector = ({title, value, onChange}: DirectionSelectorProps) => {
    return (
        <>
            {title && <Centre><H1>{title}</H1></Centre>}
            <Centre>
                <FancyRadioButtons value={value} onChange={onChange}>
                    <FancyRadioButtons.Item value="ascending">Ascending</FancyRadioButtons.Item>
                    <FancyRadioButtons.Item value="descending">Descending</FancyRadioButtons.Item>
                </FancyRadioButtons>
            </Centre>
        </>
    )
}

export default DirectionSelector
