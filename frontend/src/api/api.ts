import axios, {AxiosResponse} from "axios"

interface GetVersionResponse {
  version: string
}

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

const api = {
  async getVersion() {
    return await axios.get('/api/info') as AxiosResponse<GetVersionResponse>
  },

  async getClef(clef: string) {
    const response = await axios.get("/api/clef", { params: { clef } })
    return response as AxiosResponse<ClefResponse>
  },

  async getInterval(request: IntervalRequest) {
    const response = await axios.get("/api/interval", { params: request })
    return response as AxiosResponse<IntervalResponse>
  },
}

export default api;