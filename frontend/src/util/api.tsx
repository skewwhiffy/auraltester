import axios from "axios"

interface ClefResponse {
  abc: string
}

interface IntervalRequest {
  clef: string
  bottomNote: string
  intervalQuality: string
  intervalSize: string
  keySignature: string
}

interface IntervalResponse {
  abc: string
}

interface KeySignatureRequest {
  clef: string
  keySignature: string
}

interface KeySignatureResponse {
  abc: string
}

interface ScaleRequest {
  clef: string
  note: string
  scaleType: string
  direction: string
}

interface ScaleResponse {
  withKeySignature: string
  withoutKeySignature: string
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
  }
}

export default api