import {QuestionResponse, QuestionType} from "../../api/api";
import {Interval, Note, NoteName} from "../../common/types";

type AnswerType = NoteName | Note | Interval

const parseAnswer = (question: QuestionResponse, answer: AnswerType[]) => {
        const questionResponse = question as QuestionResponse;
        if (!questionResponse.answerTypes) {
            throw Error('Expected a QuestionResponse')
        }
        if (questionResponse.answerTypes.length !== answer.length) {
            throw Error('Require answer types and answer to have the same length')
        }
        return [...Array(answer.length).keys()]
            .map(index => parseSingleAnswer(question.answerTypes[index], answer[index]));
}

const parseSingleAnswer = (questionType: QuestionType, answer: AnswerType) => {
    switch (questionType) {
        case "NOTE_NAME":
            return answer as string
        default:
            throw Error('TODO')
    }
}

export default parseAnswer