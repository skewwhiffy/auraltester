import axios from "axios"
import { ClefResponse, IntervalRequest, IntervalResponse, KeySignatureRequest, KeySignatureResponse, QuestionResponse, ScaleRequest, ScaleResponse } from "./model"

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
      { headers: { "Content-Type": "application/json" } }
    )
    return response.data
  }
}

export default api