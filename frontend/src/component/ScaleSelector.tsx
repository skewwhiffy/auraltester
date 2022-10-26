import React from 'react'
import { RadioGroup, RadioButton } from 'react-radio-buttons'

type OnChangeHandler = (clef: String, note: String, type: String) => void

interface Props {
  onChange: OnChangeHandler
}

interface State {
  clef: String,
  note: String,
  type: String
}

class ScaleSelector extends React.Component<Props, State> {
  constructor(props: Props) {
    super(props)
    this.state = {
      clef: '',
      note: '',
      type: ''
    }
  }
  
  onTypeChange = (newType: String) => {
    this.onFormChange({
      ...this.state,
      type: newType
    })
  }

  onNoteChange = (newNote: String) => {
    this.onFormChange({
      ...this.state,
      note: newNote
    })
  }

  onClefChange = (newClef: String) => {
    this.onFormChange({
      ...this.state,
      clef: newClef
    })
  }

  onFormChange = (newState: State) => {
    this.setState(newState)
    this.props.onChange(newState.clef, newState.note, newState.type)
  }

  render() {
    return (
      <form>
        <RadioGroup onChange={this.onNoteChange}>
          <RadioButton value='A'>A</RadioButton>
          <RadioButton value='B'>B</RadioButton>
          <RadioButton value='C'>C</RadioButton>
          <RadioButton value='D'>D</RadioButton>
          <RadioButton value='E'>E</RadioButton>
          <RadioButton value='F'>F</RadioButton>
          <RadioButton value='G'>G</RadioButton>
        </RadioGroup>
        <RadioGroup onChange={this.onClefChange}>
          <RadioButton value='treble'>Treble Clef</RadioButton>
          <RadioButton value='alto'>Alto Clef</RadioButton>
          <RadioButton value='bass'>Bass Clef</RadioButton>
        </RadioGroup>
        <RadioGroup onChange={this.onTypeChange}>
          <RadioButton value='major'>Major</RadioButton>
          <RadioButton value='minor-harmonic'>Minor Harmonic</RadioButton>
          <RadioButton value='minor-melodic-ascending'>Minor Melodic Ascending</RadioButton>
          <RadioButton value='minor-melodic-descending'>Minor Melodic Descending</RadioButton>
        </RadioGroup>
      </form>
    )
  }
}

export default ScaleSelector