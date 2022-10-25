import React from "react"
import { Notation } from "react-abc"
import ScaleSelector from "./ScaleSelector"

interface Props {}

interface State {
  abc: string
}

class Scale extends React.Component<Props, State> {
  constructor(props: Props) {
    super(props)
    this.state = {
      abc: ''
    }
  }
  
  scaleSelected(clef: String, note: String, type: String) {
    console.log('Hello')
    console.log(clef, note, type)
  }

  render() {
    return (
      <div className="Scale">
        <Notation notation={this.state.abc} />
        <ScaleSelector onChange={this.scaleSelected} />
      </div>
    )
  }
}

export default Scale