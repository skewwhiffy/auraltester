import axios, { AxiosResponse } from "axios"
import type { Clef, Interval, IntervalQuality, IntervalSize } from "../common/types"

interface GetVersionResponse {
  version: string
}

interface ClefResponse {
  abc: string
}

interface IntervalRequest {
  clef: string
  bottomNote: string
  interval: Interval
  keySignature: string
}

interface IntervalResponse {
  abc: string
}

const api = {
  async getVersion() {
    return await axios.get('/api/info') as AxiosResponse<GetVersionResponse>
  },

  async getClef(clef: Clef) {
    const response = await axios.get("/api/clef", { params: { clef } })
    return response as AxiosResponse<ClefResponse>
  },

  async getInterval({
    clef,
    bottomNote,
    interval: [intervalQuality, intervalSize],
    keySignature
  }: IntervalRequest) {
    const response = await axios.get("/api/interval", {
      params: {
        clef,
        bottomNote,
        intervalQuality,
        intervalSize,
        keySignature,
      }
    })
    return response as AxiosResponse<IntervalResponse>
  },
}

export default api;