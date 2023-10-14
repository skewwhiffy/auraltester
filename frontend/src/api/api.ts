import axios, {AxiosResponse} from "axios"

interface GetVersionResponse {
  version: string
}

interface ClefResponse {
  abc: string
}

export default {
  async getVersion() {
    return await axios.get('/api/info') as AxiosResponse<GetVersionResponse>
  },

  async getClef(clef: string) {
    const response = await axios.get("/api/clef", { params: { clef } })
    return response as AxiosResponse<ClefResponse>
  },
}
