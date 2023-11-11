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

interface ScaleRequest {
    clef: Clef
    note: Note
    type: string // @todo typing
    direction: string // @todo typing
}

interface ScaleResponse {
    withKeySignature: string
    withoutKeySignature: string
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

    async getScale({
                       clef,
                       note,
                       type,
                       direction
                   }: ScaleRequest) {
        const response = await axios.get("/api/scale", {
            params: {
                clef,
                note: `${note.name}${note.accidental}`,
                scaleType: type,
                direction
            }
        })
        return response as AxiosResponse<ScaleResponse>
    }
}

export default api;