import { useState } from "react"
import { Button } from "react-bootstrap";
import api from "../../util/api";
import Question from "../question/Question";
import { AnswerResponse, QuestionResponse } from "../../util/api/model";

interface State {
  currentScore: number
  question?: QuestionResponse
  answerResponse?: AnswerResponse
}

const Quiz = (): JSX.Element => {
  const [state, setState] = useState<State>({
    currentScore: 0
  });

  const getQuestion = async () => {
    const response = await api.getClefQuestion();
    setState({
      ...state,
      question: response
    })
  }

  const answerQuestion = async (answer: string[]) => {
    const response = await api.answerQuestion(state.question?.questionId, answer);
    console.log(response)
    setState({
      ...state,
      question: undefined,
      answerResponse: response
    })
  }

  return (
    <div>
      <h1>Clef Quiz</h1>
      <p>Current score {state.currentScore}</p>
      {
        state.question
          ? <Question onSubmit={answerQuestion} question={state.question} />
          : <Button onClick={getQuestion}>New question</Button>
      }
    </div>
  )
}

export default Quiz