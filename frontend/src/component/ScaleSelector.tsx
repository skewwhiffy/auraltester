import React from 'react'
import { RadioGroup, RadioButton } from 'react-radio-buttons'

type OnChangeHandler = (clef: string, note: string, type: string) => void

interface Props {
  onChange: OnChangeHandler
}

interface State {
  clef: string,
  note: string,
  accidental: string,
  type: string
}

class ScaleSelector extends React.Component<Props, State> {
  constructor(props: Props) {
    super(props)
    this.state = {
      clef: '',
      note: '',
      accidental: '',
      type: ''
    }
  }

  onTypeChange = (newType: string) => {
    this.onFormChange({
      ...this.state,
      type: newType
    })
  }

  onAccidentalChange = (newAccidental: string) => {
    this.onFormChange({
      ...this.state,
      accidental: newAccidental
    })
  }

  onNoteChange = (newNote: string) => {
    this.onFormChange({
      ...this.state,
      note: newNote
    })
  }

  onClefChange = (newClef: string) => {
    this.onFormChange({
      ...this.state,
      clef: newClef
    })
  }

  onFormChange = (newState: State) => {
    this.setState(newState)
    this.props.onChange(
      newState.clef,
      `${newState.note}${newState.accidental}`,
      newState.type
    )
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
        <RadioGroup onChange={this.onAccidentalChange}>
          <RadioButton value='x'>x</RadioButton>
          <RadioButton value='#'>#</RadioButton>
          <RadioButton value=''>natural</RadioButton>
          <RadioButton value='b'>b</RadioButton>
          <RadioButton value='bb'>bb</RadioButton>
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