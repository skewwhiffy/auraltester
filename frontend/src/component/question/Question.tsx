import { useState } from "react"
import { QuestionResponse, QuestionResponseElement } from "../../util/api/model"
import NoteNameSelector from "../selector/NoteNameSelector"
import QuestionResponseAbcElement from "./QuestionResponseAbcElement"
import QuestionResponseTextElement from "./QuestionResponseTextElement"
import { Button } from "react-bootstrap"

type OnSubmitHandler = (answer: any) => void

interface Props {
  question: QuestionResponse
  onSubmit: OnSubmitHandler
}

interface State {
  noteName?: string
}

const Question = (props: Props): JSX.Element => {
  const [state, setState] = useState<State>({})

  const renderElement = (e: QuestionResponseElement, i: number): JSX.Element => {
    const key = i.toString()
    if (e.text) {
      return <QuestionResponseTextElement key={key} text={e.text} />
    }
    if (e.abc) {
      return <QuestionResponseAbcElement key={key} abc={e.abc} />
    }
    return <p key={i}>Element</p>
  }

  const renderAnswerElement = (answerType: string, index: number): JSX.Element => {
    const key = `answer-element-${index}`
    switch (answerType) {
      case "NOTE_NAME":
        const onAnswerSelect = (note: string) => {
          setState({ ...state, noteName: note })
        }
        return <NoteNameSelector key={key} onChange={onAnswerSelect} />
      default:
        return <p>UNKNOWN</p>
    }
  }

  const renderAnswerElements = (): JSX.Element => {
    return (
      <>
        {props.question.answerTypes.map(renderAnswerElement)}
      </>
    )
  }
  
  const getSubmission = (answerType: string): string => {
    switch (answerType) {
      case "NOTE_NAME":
        if (!state.noteName) {
          throw "Require note name, but not defined"
        }
        return state.noteName
      default:
        throw `Not recognized: '${answerType}'`
    }
  }

  const onSubmit = () => {
    const submission = props.question.answerTypes.map(getSubmission)
    props.onSubmit(submission)
  }

  const renderSubmitButton = (): JSX.Element => {
    if (state.noteName) { // TODO: Generalize
      return <Button onClick={onSubmit}>Submit</Button>
    }
    return <></>
  }

  return (
    <div>
      {props.question.elements.map(renderElement)}
      {renderAnswerElements()}
      {renderSubmitButton()}
    </div>
  )
}

export default Question