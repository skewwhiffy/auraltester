import React from "react"

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

  onNoteChange = (e: any) => {
    this.setState({
      ...this.state,
      note: e.target.value
    })
    this.props.onChange(this.state.clef, e.target.value, this.state.type)
  }

  render() {
    return (
      <form>
        <input
          name='note'
          type='radio'
          value='A'
          id='noteA'
          onChange={this.onNoteChange}
        />
        <label htmlFor='noteA'>A</label>
        <input
          name='note'
          type='radio'
          value='B'
          id='noteB'
          onChange={this.onNoteChange}
        />
        <label htmlFor='noteB'>B</label>
      </form>
    )
  }
}

export default ScaleSelector