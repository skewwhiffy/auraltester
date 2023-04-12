import axios from "axios"
import { ClefResponse, IntervalRequest, IntervalResponse, KeySignatureRequest, KeySignatureResponse, QuestionResponse, ScaleRequest, ScaleResponse } from "./model"

const jsonHeaders = {
  headers: {
    "Content-Type": "application/json"
  }
}

const api = {
  async getClef(clef: string): Promise<ClefResponse> {
    const response = await axios.get("/api/clef", { params: { clef } })
    return response.data
  },
  async getInterval(request: IntervalRequest): Promise<IntervalResponse> {
    const response = await axios.get("/api/interval", { params: request })
    return response.data
  },
  async getKeySignature(request: KeySignatureRequest)
    : Promise<KeySignatureResponse> {
    const response = await axios.get("/api/keySignature", { params: request })
    return response.data
  },
  async getScale(request: ScaleRequest): Promise<ScaleResponse> {
    const response = await axios.get("/api/scale", { params: request })
    return response.data
  },
  async getClefQuestion(): Promise<QuestionResponse> {
    const response = await axios.post(
      "/api/question",
      { type: "CLEF" },
      jsonHeaders
    )
    return response.data
  },
  async answerQuestion(id: string | undefined, answer: string[]) {
    if (!id) {
      throw "Expected question ID to answer it"
    }
    const response = await axios.post(
      "/api/question/answer",
      { id, answer },
      jsonHeaders
    )
    return response.data
  }
}

export default api