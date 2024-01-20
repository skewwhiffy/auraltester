import {useMutation, useQuery} from "@tanstack/react-query";
import api from "../../api/api";
import H1 from "../../component/H1";
import SpinUntilReady from "../../component/SpinUntilReady";
import Question from "../quiz/Question";
import parseAnswer from "../quiz/parseAnswer";
import {NoteName} from "../../common/types";

const ClefQuiz = () => {
    const getClefQuestionQuery = useQuery({
        queryKey: ['clefQuiz'],
        queryFn: async () => {
            return await api.getClefQuestion()
        }
    })

    const answerClefQuestionMutation = useMutation({
        mutationFn: async (submission: NoteName[]) => {
            const parsedAnswer = parseAnswer(getClefQuestionQuery.data?.data!, submission)
            return await api.answerQuestionOld(getClefQuestionQuery.data?.data?.questionId, parsedAnswer)
        },
        async onSuccess(data) {
            console.log(data);
        }
    })

    return (
        <>
            <H1>Clef Quiz</H1>
            {
                <SpinUntilReady isLoading={getClefQuestionQuery.isLoading}>
                    <Question
                        onSubmit={answerClefQuestionMutation.mutate}
                        question={getClefQuestionQuery.data?.data!}
                    />
                </SpinUntilReady>
            }
        </>
    )

}

export default ClefQuiz
/*

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