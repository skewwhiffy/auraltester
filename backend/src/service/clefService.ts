import {clefFactory, ClefType} from "../model/clef";

interface ClefResponse {
    abc: string
}

export const getClef = (clef: ClefType): ClefResponse => {
    const clefObject = clefFactory[clef];
    console.log(clefObject)
    return {
        abc: 'hello'
    }
}
