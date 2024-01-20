import axios, {AxiosResponse} from "axios"
import type {Clef, Interval, Note} from "../common/types"

const jsonHeaders = {
    headers: {
        "Content-Type": "application/json"
    }
}

interface GetVersionResponse {
    version: string
}

interface ClefResponse {
    abc: string
}

interface IntervalRequest {
    clef: Clef
    bottomNote: Note
    interval: Interval
    keySignature: Note
}

interface IntervalResponse {
    abc: string
}

interface ScaleRequest {
    clef: Clef
    note: Note
    type: string // @todo typing
    direction: string // @todo typing
}

interface ScaleResponse {
    withKeySignature: string
    withoutKeySignature: string
}

interface KeySignatureRequest {
    clef: Clef,
    key: Note
}

interface KeySignatureResponse {
    abc: string
}

export interface QuestionResponseElement {
    abc: string | undefined
    text: string | undefined
}

export interface QuestionResponse {
    questionId: string
    elements: QuestionResponseElement[]
    answerTypes: string[]
}

const api = {
    async getVersion() {
        return await axios.get('/api/info') as AxiosResponse<GetVersionResponse>
    },

    async getClef(clef: Clef) {
        const response = await axios.get("/api/clef", {params: {clef}})
        return response as AxiosResponse<ClefResponse>
    },

    async getInterval({
                          clef,
                          bottomNote,
                          interval: {
                              size: intervalSize,
                              quality: intervalQuality
                          },
                          keySignature
                      }: IntervalRequest) {
        const request = {
            params: {
                clef,
                bottomNote: `${bottomNote.name}${bottomNote.accidental}`,
                intervalQuality,
                intervalSize,
                keySignature: `${keySignature.name}${keySignature.accidental}`
            }
        }
        const response = await axios.get("/api/interval", request)
        return response as AxiosResponse<IntervalResponse>
    },

    async getScale({
                       clef,
                       note,
                       type,
                       direction
                   }: ScaleRequest) {
        const response = await axios.get("/api/scale", {
            params: {
                clef,
                note: `${note.name}${note.accidental}`,
                scaleType: type,
                direction
            }
        })
        return response as AxiosResponse<ScaleResponse>
    },

    async getKeySignature({
                              clef,
                              key
                          }: KeySignatureRequest) {
        const response = await axios.get("/api/keySignature", {
            params: {
                clef: clef,
                keySignature: `${key.name}${key.accidental}`
            }
        })
        return response as AxiosResponse<KeySignatureResponse>
    },

    async getClefQuestion() {
        const response = await axios.post(
            "/api/question",
            { type: "CLEF" },
            jsonHeaders
        )
        return response as AxiosResponse<QuestionResponse>
    },

    async answerQuestionOld(id: string | undefined, answer: string[]) {
        if (!id) {
            throw Error("Expected question ID to answer it")
        }
        const response = await axios.post(
            "/api/question/answer",
            { id, answer },
            jsonHeaders
        )
        return response.data
    }
}

export default api;