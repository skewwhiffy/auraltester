export interface ClefResponse {
  abc: string
}

export interface IntervalRequest {
  clef: string
  bottomNote: string
  intervalQuality: string
  intervalSize: string
  keySignature: string
}

export interface IntervalResponse {
  abc: string
}

export interface KeySignatureRequest {
  clef: string
  keySignature: string
}

export interface KeySignatureResponse {
  abc: string
}

export interface ScaleRequest {
  clef: string
  note: string
  scaleType: string
  direction: string
}

export interface ScaleResponse {
  withKeySignature: string
  withoutKeySignature: string
}

export interface QuestionResponse {
  questionId: string
  elements: QuestionResponseElement[]
  answerType: string
}

export interface QuestionResponseElement {
  abc: string | undefined
  text: string | undefined
}
