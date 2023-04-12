import { renderQuestionElement } from "."
import { AnswerResponse } from "../../util/api/model"

interface Props {
  answer: AnswerResponse
}

const Answer = (props: Props): JSX.Element => {
  return <>
    {props.answer.elements.map(renderQuestionElement)}
  </>
}

export default Answer