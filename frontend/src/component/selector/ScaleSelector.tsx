import React from 'react'
import { Form, Container, Row, Col } from 'react-bootstrap'
import RadioButtons from '../../util/RadioButtons'
import ClefSelector from './ClefSelector'
import { capitalizeFirstCharacter } from '../../util'
import NoteSelector from './NoteSelector'

type OnChangeHandler = (
  clef: string,
  note: string,
  type: string,
  direction: string
) => void

interface Props {
  onChange: OnChangeHandler
}

interface State {
  clef: string,
  note: string,
  type: string,
  direction: string
}

class ScaleSelector extends React.Component<Props, State> {
  constructor(props: Props) {
    super(props)
    this.state = {
      clef: 'treble',
      note: 'C',
      type: 'major',
      direction: 'ascending'
    }
    this.onStateChange(this.state)
  }

  render() {
    return (
      <Container>
        <Form>
          <Row>
            <Col>
              <NoteSelector
                defaultValue={this.state.note}
                onChange={note => this.onFormChange({ ...this.state, note })}
              />
            </Col>
            <Col>
              <ClefSelector
                defaultValue={this.state.clef}
                onChange={clef => this.onFormChange({ ...this.state, clef })}
              />
            </Col>
            <Col>
              <RadioButtons
                values={['major', 'minor-harmonic', 'minor-melodic']
                  .map(it => ({
                    value: it,
                    label: it.split('-').map(capitalizeFirstCharacter).join(' ')
                  }))}
                name='type'
                defaultValue={this.state.type}
                onChange={type => this.onFormChange({ ...this.state, type })}
              />
            </Col>
            <Col>
              <RadioButtons
                values={['ascending', 'descending']
                  .map(it => ({
                    value: it,
                    label: capitalizeFirstCharacter(it)
                  }))}
                name='direction'
                defaultValue={this.state.direction}
                onChange={direction => this.onFormChange({ ...this.state, direction })}
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
      state.note,
      state.type,
      state.direction
    )
  }
}

export default ScaleSelector