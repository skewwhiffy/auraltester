import {QuestionResponse} from "../../api/api";

const parseAnswer = (question: QuestionResponse, answer: string[]) => {
    console.log(question.answerTypes);
    console.log(answer);
}

export default parseAnswer