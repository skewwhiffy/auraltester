import React from 'react'
import { Notation } from 'react-abc'
import ScaleSelector from './ScaleSelector'

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
  
  scaleSelected = async (clef: String, note: String, type: String) => {
    if (clef === '' || note === '' || type === '') {
      this.setState({
        ...this.state,
        abc: ''
      })
      return
    }
    const response = await fetch(`/api/scale/${clef}/${note}/${type}`)
    const json = await response.json()
    const newAbc = json.abc
    this.setState({
      ...this.state,
      abc: newAbc
    })
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