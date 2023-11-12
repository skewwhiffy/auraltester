import Centre from "../Centre";
import H1 from "../H1";
import FancyRadioButtons from "../FancyRadioButtons";
import {NoteName} from "../../common/types";

interface Props {
    title?: string,
    value?: NoteName,

    onChange(value: NoteName): void
}

const NoteNameSelector = ({title, value, onChange}: Props) => {
    return (
        <>
            {title && <Centre><H1>{title}</H1></Centre>}
            <Centre>
                <FancyRadioButtons
                    value={value}
                    onChange={onChange}
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
        </>
    )
}

export default NoteNameSelector