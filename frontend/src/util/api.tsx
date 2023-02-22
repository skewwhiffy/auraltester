import axios from "axios"

interface ClefResponse {
  abc: string
}

interface KeySignatureRequest {
  clef: string
  keySignature: string
}

interface KeySignatureResponse {
  abc: string
}

const api = {
  async getClef(clef: string): Promise<ClefResponse> {
    const response = await axios.get("/api/clef", { params: { clef } })
    return response.data
  },
  async getKeySignature({ clef, keySignature }: KeySignatureRequest)
    : Promise<KeySignatureResponse> {
    const response = await axios.get("/api/keySignature", { params: { clef, keySignature } })
    return response.data
  }
}

export default api