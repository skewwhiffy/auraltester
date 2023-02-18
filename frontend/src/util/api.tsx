import axios from "axios"

interface ClefResponse {
  abc: string
}

const api = {
  async getClef(clef: string): Promise<ClefResponse> {
    const response = await axios.get("/api/clef", {
      params: {
        clef
      }
    })
    return response.data
  }
}

export default api