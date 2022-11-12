import React from 'react'
import { Col, Container, Form, Row } from 'react-bootstrap'
import RadioButtons, { RadioButtonDefinition } from '../../util/RadioButtons'
import ClefSelector from './ClefSelector'
import NoteSelector from './NoteSelector'

type OnChangeHandler = (
  clef: string,
  bottomNote: string,
  intervalQuality: string,
  intervalSize: string,
  keySignature: string
) => void

export interface Props {
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
  private keySignatureValues: Array<RadioButtonDefinition> = [
    { value: 'C#', label: 'C# / A# minor' },
    { value: 'F#', label: 'F# / D# minor' },
    { value: 'B', label: 'B / G# minor' },
    { value: 'E', label: 'E / C# minor' },
    { value: 'A', label: 'A / F# minor' },
    { value: 'D', label: 'D / B minor' },
    { value: 'G', label: 'G / E minor' },
    { value: 'C', label: 'None / C / A minor' },
    { value: 'F', label: 'F / D minor' },
    { value: 'Bb', label: 'Bb / G minor' },
    { value: 'Eb', label: 'Eb / C minor' },
    { value: 'Ab', label: 'Ab / F minor' },
    { value: 'Db', label: 'Db / Bb minor' },
    { value: 'Gb', label: 'Gb / Eb minor' },
    { value: 'Cb', label: 'Cb / Ab minor' },
  ]

  constructor(props: Props) {
    super(props);
    this.state = {
      clef: 'treble',
      bottomNote: 'C',
      intervalQuality: '',
      intervalSize: '',
      keySignature: 'C'
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
                onChange={clef => this.onFormChange({ ...this.state, clef })}
              />
            </Col>
            <Col>
              <NoteSelector
                defaultValue={this.state.bottomNote}
                includeDoubleAccidentals={true}
                onChange={bottomNote => this.onFormChange({ ...this.state, bottomNote })}
              />
            </Col>
            <Col>
              <Container>
                <Row><Col>Interval</Col></Row>
                <Row>
                  <Col>
                    <RadioButtons
                      values={['diminished', 'minor', 'major', 'perfect', 'augmented'].map(value => ({ value }))}
                      name='intervalQuality'
                      defaultValue={this.state.intervalQuality}
                      onChange={intervalQuality => this.onFormChange({ ...this.state, intervalQuality })}
                    />
                  </Col>
                  <Col>
                    <RadioButtons
                      values={Array.from(Array(8).keys()).map(it => it + 1).map(it => ({ value: `${it}` }))}
                      name='intervalSize'
                      defaultValue={this.state.intervalQuality}
                      onChange={intervalSize => this.onFormChange({ ...this.state, intervalSize })}
                    />
                  </Col>
                </Row>
              </Container>
            </Col>
            <Col>
              <Container>
                <Row><Col>Key signature</Col></Row>
                <Row>
                  <Col>
                    <RadioButtons
                      values={this.keySignatureValues}
                      name='keySignature'
                      defaultValue={this.state.keySignature}
                      onChange={keySignature => this.onFormChange({...this.state, keySignature})}
                    />
                  </Col>
                </Row>
              </Container>
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