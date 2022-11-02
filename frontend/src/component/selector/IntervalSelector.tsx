import React from 'react'
import { Col, Container, Form, Row } from 'react-bootstrap'
import ClefSelector from './ClefSelector'

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
  constructor(props: Props) {
    super(props);
    this.state = {
      clef: 'treble',
      bottomNote: '',
      intervalQuality: '',
      intervalSize: '',
      keySignature: ''
    }
  }

  render() {
    return (
      <Container>
        <Form>
          <Row>
            <Col>
              <ClefSelector 
                defaultValue={this.state.clef} 
                onChange={clef => this.onFormChange({ ...this.state, clef})}
              />
            </Col>
          </Row>
        </Form>
      </Container>
    )
  }
  
  private onFormChange = (newState: State) => {
    this.setState(newState)
    this.onStateChange(newState)
  }

  private onStateChange(state: State) {
    this.props.onChange(
      state.clef,
      state.bottomNote,
      state.intervalQuality,
      state.intervalSize,
      state.keySignature
    )
  }
}

export default IntervalSelector