import React from "react"
import { Col, Container, Row } from "react-bootstrap"
import RadioButtons from "../../util/RadioButtons"

type OnChangeHandler = (note: string) => void

interface Props {
  defaultValue: string,
  onChange: OnChangeHandler
}

interface State {
  noteName: string,
  accidental: string
}

class NoteSelector extends React.Component<Props, State> {
  constructor(props: Props) {
    super(props);
    this.state = {
      noteName: this.extractNoteName(props.defaultValue),
      accidental: this.extractAccidental(props.defaultValue)
    }
  }

  render() {
    return (
      <Container>
        <Row><Col>Note</Col></Row>
        <Row>
          <Col>
            <RadioButtons
              values={'ABCDEFG'.split('').map(it => ({ value: it }))}
              name='note'
              defaultValue={this.state.noteName}
              onChange={note => this.onFormChange({ ...this.state, noteName: note })}
            />
          </Col>
          <Col>
            <RadioButtons
              values={[
                { value: '#' },
                { value: '', label: 'natural' },
                { value: 'b' }
              ]}
              name='accidental'
              defaultValue={this.state.accidental}
              onChange={accidental => this.onFormChange({ ...this.state, accidental })}
            />
          </Col>
        </Row>
      </Container>
    )
  }

  /*
      note: this.extractNote(props.defaultValue),
      accidental: this.extractAccidental(props.defaultValue)
      */
  private extractNoteName = (note: string) => {
    return note.substring(0, 1)
  }
  
  private extractAccidental = (note: string) => {
    console.log(note.substring(1))
    return note.substring(1)
  }

  private onFormChange = (newState: State) => {
    this.setState(newState)
    this.onStateChange(newState)
  }

  private onStateChange(state: State) {
    this.props.onChange(`${state.noteName}${state.accidental}`)
  }
  
}

export default NoteSelector