import { useState } from "react"
import { Button } from "react-bootstrap";
import api from "../../util-old/api";
import Question from "../question/Question";
import { AnswerResponse, QuestionResponse } from "../../util-old/api/model";
import Answer from "../question/Answer";

interface State {
  currentScore: number
  numberOfQuestions: number
  question?: QuestionResponse
  answerResponse?: AnswerResponse
}

const Quiz = (): JSX.Element => {
  const [state, setState] = useState<State>({
    currentScore: 0,
    numberOfQuestions: 0
  });

  const getQuestion = async () => {
    const response = await api.getClefQuestion();
    setState({
      ...state,
      answerResponse: undefined,
      question: response
    })
  }

  const answerQuestion = async (answer: string[]) => {
    const response = await api.answerQuestion(state.question?.questionId, answer);
    console.log(response)
    setState({
      ...state,
      currentScore: state.currentScore + (response.isCorrect ? 1 : 0),
      numberOfQuestions: state.numberOfQuestions + 1,
      question: undefined,
      answerResponse: response
    })
  }

  return (
    <div>
      <h1>Clef Quiz</h1>
      <p>Current score {state.currentScore} out of {state.numberOfQuestions}</p>
      {
        state.question && <Question onSubmit={answerQuestion} question={state.question} />
      }
      {
        state.answerResponse && <Answer answer={state.answerResponse} />
      }
      {
        !state.question && <Button onClick={getQuestion}>New question</Button>
      }
    </div>
  )
}

export default Quiz
