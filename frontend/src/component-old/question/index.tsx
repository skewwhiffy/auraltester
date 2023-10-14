import { QuestionResponseElement } from "../../util-old/api/model"
import QuestionResponseAbcElement from "./QuestionResponseAbcElement"
import QuestionResponseTextElement from "./QuestionResponseTextElement"

const renderQuestionElement = (e: QuestionResponseElement, i: number): JSX.Element => {
  const key = i.toString()
  if (e.text) {
    return <QuestionResponseTextElement key={key} text={e.text} />
  }
  if (e.abc) {
    return <QuestionResponseAbcElement key={key} abc={e.abc} />
  }
  return <p key={i}>Element</p>
}

export {
  renderQuestionElement
}
