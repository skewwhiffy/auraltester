import { useState } from "react"
import { Button } from "react-bootstrap";
import axios from "axios";

interface State {
  currentScore: number
  question?: any
}

const Quiz = (): JSX.Element => {
  const [state, setState] = useState<State>({
    currentScore: 0
  });

  const getQuestion = async () => {
    const options = {
      method: "POST",
      url: "/api/question",
      headers: { "Content-Type": "application/json" },
      data: { type: "CLEF" }
    };
    const response = await axios.request(options);
    const data = response.data
    console.log(data)
  }

  return (
    <div>
      <h1>Clef Quiz</h1>
      <p>Current score {state.currentScore}</p>
      {state.question ? <p>Question</p> : <Button onClick={getQuestion}>New question</Button>}
    </div>
  )
}

export default Quiz