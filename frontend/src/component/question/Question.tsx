import { QuestionResponse, QuestionResponseElement } from "../../util/api/model"
import QuestionResponseAbcElement from "./QuestionResponseAbcElement"
import QuestionResponseTextElement from "./QuestionResponseTextElement"

interface Props {
  question: QuestionResponse
}

const Question = (props: Props): JSX.Element => {
  const renderElement = (e: QuestionResponseElement, i: number) => {
    const key = i.toString()
    if (e.text) {
      return <QuestionResponseTextElement key={key} text={e.text} />
    }
    if (e.abc) {
      return <QuestionResponseAbcElement key={key} abc={e.abc} />
    }
    return <p key={i}>Element</p>
  }
  return (
    <div>
      { props.question.elements.map(renderElement) }
    </div>
  )
}

export default Question