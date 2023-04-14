import { useState } from "react"
import { QuestionResponse } from "../../util/api/model"
import NoteNameSelector from "../selector/NoteNameSelector"
import { Button } from "react-bootstrap"
import { renderQuestionElement } from "."

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
          throw Error("Require note name, but not defined")
        }
        return state.noteName
      default:
        throw Error(`Not recognized: '${answerType}'`)
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
      {props.question.elements.map(renderQuestionElement)}
      {renderAnswerElements()}
      {renderSubmitButton()}
    </div>
  )
}

export default Question