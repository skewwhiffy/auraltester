import React from 'react'

type OnChangeHandler = (
  clef: string,
  bottomNote: string,
  intervalQuality: string,
  intervalSize: string,
  keySignature: string
) => void

interface Props {
  onChange: OnChangeHandler
}

interface State {
  clef: string,
  bottomNote: string,
  intervalQuality: string,
  intervalSize: string,
  keySignature: string
}

class IntervalSelector extends React.Component<Props, State> {
  render() {
    return (
      <h1>Hello</h1>
    )
  }
}

export default IntervalSelector