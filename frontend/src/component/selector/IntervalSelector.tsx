import {useState} from "react"
import {Interval, IntervalQuality, IntervalSize} from "../../common/types"
import Centre from "../Centre"
import FancyRadioButtons from "../FancyRadioButtons"
import H1 from "../H1"
import IntervalSizeSelector from "./IntervalSizeSelector";

interface IntervalSelectorProps {
    title?: string | null,
    value?: Interval | null,

    onChange(interval: Interval): void
}

const IntervalSelector = ({title, value, onChange}: IntervalSelectorProps) => {
    const [intervalSize, setIntervalSize] = useState(value?.size)
    const [intervalQuality, setIntervalQuality] = useState(value?.quality)

    const onIntervalSizeChange = (intervalSize: IntervalSize) => {
        setIntervalSize(intervalSize)
        if (!!intervalQuality) {
            onChange({size: intervalSize, quality: intervalQuality})
        }
    }

    const onIntervalQualityChange = (intervalQuality: IntervalQuality) => {
        setIntervalQuality(intervalQuality)
        if (!!intervalSize) {
            onChange({size: intervalSize, quality: intervalQuality})
        }
    }

    return (
        <>
            {title && <Centre><H1>{title}</H1></Centre>}
            <Centre>
                <FancyRadioButtons value={intervalQuality} onChange={onIntervalQualityChange}>
                    <FancyRadioButtons.Item value="diminished">Diminished</FancyRadioButtons.Item>
                    <FancyRadioButtons.Item value="minor">Minor</FancyRadioButtons.Item>
                    <FancyRadioButtons.Item value="major">Major</FancyRadioButtons.Item>
                    <FancyRadioButtons.Item value="perfect">Perfect</FancyRadioButtons.Item>
                    <FancyRadioButtons.Item value="augmented">Augmented</FancyRadioButtons.Item>
                </FancyRadioButtons>
            </Centre>
            <IntervalSizeSelector value={intervalSize} onChange={onIntervalSizeChange}/>
        </>
    )
}

export default IntervalSelector