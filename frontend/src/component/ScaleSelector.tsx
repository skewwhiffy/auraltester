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
      clef: 'treble',
      note: '',
      type: 'major'
    }
  }

  onNoteChange = (newNote: String) => {
    this.setState({
      ...this.state,
      note: newNote
    })
    this.props.onChange(this.state.clef, newNote, this.state.type)
  }

  render() {
    return (
      <form>
        <RadioGroup onChange={this.onNoteChange}>
          <RadioButton value='A'>A</RadioButton>
          <RadioButton value='B'>B</RadioButton>
        </RadioGroup>
      </form>
    )
  }
}

export default ScaleSelector