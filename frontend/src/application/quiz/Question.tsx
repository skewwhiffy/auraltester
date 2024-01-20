import {useState} from "react"
import {QuestionResponse, QuestionResponseElement} from "../../api/api";
import {Interval, Note, NoteName} from "../../common/types";
import IntervalSelector from "../../component/selector/IntervalSelector";
import {Notation} from "react-abc";
import NoteNameSelector from "../../component/selector/NoteNameSelector";

type OnSubmitHandler = (answer: any) => void

interface Props {
    question?: QuestionResponse
    onSubmit: OnSubmitHandler
}

type State = (Note | NoteName | Interval) | undefined

const QuestionResponseTextElement = (props: { text: string }) => {
    return <p>{props.text}</p>
}

const QuestionResponseAbcElement = (props: { abc: string }) => {
    return <Notation notation={props.abc}/>
}

const renderQuestionElement = (e: QuestionResponseElement, i: number) => {
    const key = i.toString()
    if (e.text) {
        return <QuestionResponseTextElement key={key} text={e.text}/>
    }
    if (e.abc) {
        return <QuestionResponseAbcElement key={key} abc={e.abc}/>
    }
    return <p key={i}>Element</p>
}

export {
    renderQuestionElement
}
const Question = (props: Props) => {
    const [state, setState] = useState<State>()

    if (!props.question) {
        return null
    }

    const renderAnswerElement = (answerType: string, index: number) => {
        const key = `answer-element-${index}`
        switch (answerType) {
            case "NOTE_NAME":
                const onNoteSelect = (noteName: NoteName) => {
                    setState(noteName)
                }
                return <NoteNameSelector key={key} value={state as NoteName} onChange={onNoteSelect}/>
            case "INTERVAL":
                const onIntervalSelect = (interval: Interval) => {
                    setState(interval)
                }
                return <IntervalSelector value={state as Interval} onChange={onIntervalSelect}/>
            default:
                return <p>{answerType}</p>
        }
    }

    const renderAnswerElements = () => {
        if (!props.question) {
            return null
        }
        return (
            <>
                {props.question.answerTypes.map(renderAnswerElement)}
            </>
        )
    }

    const getSubmission = (answerType: string): NoteName | Note | Interval => {
        switch (answerType) {
            case "NOTE_NAME":
                return state as NoteName
            case "INTERVAL":
                return state as Interval
            default:
                throw Error(`Not recognized: '${answerType}'`)
        }
    }

    const onSubmit = () => {
        if (!props.question) {
            return
        }
        const submission = props.question.answerTypes.map(getSubmission)
        props.onSubmit(submission)
    }

    const renderSubmitButton = () => {
        if (!!state) { // TODO: Generalize
            return <button onClick={onSubmit}>Submit</button>
        }
        return <></>
    }

    return (
        <div>
            {props.question.elements.map(renderQuestionElement)}
            {renderAnswerElements()}
            {renderSubmitButton()}
        </div>
    )
}

export default Question
