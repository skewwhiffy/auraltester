const ClefQuiz = () => {
    return (
        <p>HELLO MUM</p>
    )
}

export default ClefQuiz
/*
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
 */