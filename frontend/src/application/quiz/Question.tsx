import {useState} from "react"
import {QuestionResponse, QuestionResponseElement} from "../../api/api";
import {Interval, Note} from "../../common/types";
import IntervalSelector from "../../component/selector/IntervalSelector";
import NoteSelector from "../../component/selector/NoteSelector";
import {Notation} from "react-abc";

type OnSubmitHandler = (answer: any) => void

interface Props {
    question?: QuestionResponse
    onSubmit: OnSubmitHandler
}

interface State {
    note?: Note
    interval?: Interval
}

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
    const [state, setState] = useState<State>({})

    if (!props.question) {
        return null
    }

    const renderAnswerElement = (answerType: string, index: number) => {
        const key = `answer-element-${index}`
        switch (answerType) {
            case "NOTE_NAME":
                const onNoteSelect = (note: Note) => {
                    setState({...state, note})
                }
                return <NoteSelector key={key} value={state.note} onChange={onNoteSelect}/>
            case "INTERVAL":
                const onIntervalSelect = (interval: Interval) => {
                    setState({...state, interval})
                }
                return <IntervalSelector value={state.interval} onChange={onIntervalSelect}/>
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

    const getSubmission = (answerType: string): Note | Interval => {
        switch (answerType) {
            case "NOTE_NAME":
                if (!state.note) {
                    throw Error("Require note name, but not defined")
                }
                return state.note
            case "INTERVAL":
                if (!state.interval) {
                    throw Error("Require interval interval, but not defined")
                }
                return state.interval
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
        if (state.note || state.interval) { // TODO: Generalize
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
