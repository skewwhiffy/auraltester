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

  const renderAnswerElements = (): JSX.Element => {
    switch (props.question.answerType) {
      case "NOTE_NAME":
        const onAnswerSelect = (note: string) => {
          setState({ ...state, noteName: note })
        }
        return <NoteNameSelector onChange={onAnswerSelect} />
      default:
        return <p>UNKNOWN</p>
    }
  }

  const onSubmit = () => {
    switch (props.question.answerType) {
      case "NOTE_NAME":
        props.onSubmit(state.noteName)
        break;
      default:
        throw `Not recognized: '${props.question.answerType}'`
    }
  }

  const renderSubmitButton = (): JSX.Element => {
    if (state.noteName) {
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