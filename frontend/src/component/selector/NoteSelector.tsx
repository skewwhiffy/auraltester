import React from "react"
import { Col, Container, Row } from "react-bootstrap"
import RadioButtons from "../../util/RadioButtons"

type OnChangeHandler = (note: string) => void

interface Props {
  onChange: OnChangeHandler
}

interface State {
  note: string,
  accidental: string
}

class NoteSelector extends React.Component<Props, State> {
  constructor(props: Props) {
    super(props);
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
              defaultValue={this.state.note}
              onChange={note => this.onFormChange({ ...this.state, note })}
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
}

export default NoteSelector