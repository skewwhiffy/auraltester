import axios, {AxiosResponse} from "axios"

interface GetVersionResponse {
  version: string
}

export default {
  async getVersion() {
    return await axios.get('/api/info') as AxiosResponse<GetVersionResponse>
  }
}
