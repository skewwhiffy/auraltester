import {useState} from "react"
import {Interval, IntervalQuality, IntervalSize} from "../../common/types"
import Centre from "../Centre"
import FancyRadioButtons from "../FancyRadioButtons"
import H1 from "../H1"

interface IntervalSelectorProps {
    title?: string | null,
    value?: Interval | null,

    onChange(note: Interval): void
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
                <FancyRadioButtons
                    value={intervalQuality}
                    onChange={onIntervalQualityChange}
                >
                    <FancyRadioButtons.Item value="diminished">Diminished</FancyRadioButtons.Item>
                    <FancyRadioButtons.Item value="minor">Minor</FancyRadioButtons.Item>
                    <FancyRadioButtons.Item value="major">Major</FancyRadioButtons.Item>
                    <FancyRadioButtons.Item value="perfect">Perfect</FancyRadioButtons.Item>
                    <FancyRadioButtons.Item value="augmented">Augmented</FancyRadioButtons.Item>
                </FancyRadioButtons>
            </Centre>
            <Centre>
                <FancyRadioButtons
                    value={intervalSize}
                    onChange={onIntervalSizeChange}
                >
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

export default IntervalSelector