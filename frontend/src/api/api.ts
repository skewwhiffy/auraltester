import axios, {AxiosResponse} from "axios"
import type {Clef, Interval, Note} from "../common/types"

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
        console.log(keySignature)
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
}

export default api;