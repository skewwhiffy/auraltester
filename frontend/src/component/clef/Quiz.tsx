import { useState } from "react"
import { Button } from "react-bootstrap";
import api from "../../util/api";
import Question from "../question/Question";
import { QuestionResponse } from "../../util/api/model";

interface State {
  currentScore: number
  question?: QuestionResponse
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
    console.log(response)
  }

  return (
    <div>
      <h1>Clef Quiz</h1>
      <p>Current score {state.currentScore}</p>
      {state.question ? <Question question={state.question} /> : <Button onClick={getQuestion}>New question</Button>}
    </div>
  )
}

export default Quiz