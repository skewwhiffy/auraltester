import { useState } from "react"
import { QuestionResponse } from "../../util/api/model"
import NoteNameSelector from "../selector/NoteNameSelector"
import { Button } from "react-bootstrap"
import { renderQuestionElement } from "."
import IntervalSelector from "../selector/IntervalSelector"
import { IntervalSizeSelector } from "../selector/IntervalSizeSelector"

type OnSubmitHandler = (answer: any) => void

interface Props {
  question: QuestionResponse
  onSubmit: OnSubmitHandler
}

interface State {
  noteName?: string
  intervalName?: string
}

const Question = (props: Props): JSX.Element => {
  const [state, setState] = useState<State>({})
  const getIntervalShorthand = (size: number, quality: string) => {
    switch (quality) {
      case 'major':
      case 'perfect':
        return `${size}`
      case 'augmented':
        return `${size}+`
      case 'minor':
        return `${size}-`
      case 'diminished':
        return [1, 4, 5, 8].includes(size) ? `${size}-` : `${size}--`
      default:
        throw Error(`I do not understand quality '${quality}'`)
    }
  }

  const renderAnswerElement = (answerType: string, index: number): JSX.Element => {
    const key = `answer-element-${index}`
    switch (answerType) {
      case "NOTE_NAME":
        const onNoteSelect = (note: string) => {
          setState({ ...state, noteName: note })
        }
        return <NoteNameSelector key={key} onChange={onNoteSelect} />
      case "INTERVAL":
        const onIntervalSelect = (intervalQuality: string, intervalSize: string) => {
          const intervalName = getIntervalShorthand(parseInt(intervalSize), intervalQuality)
          setState({ ...state, intervalName })
        }
        return <IntervalSizeSelector onChange={onIntervalSelect} />
      default:
        return <p>{answerType}</p>
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