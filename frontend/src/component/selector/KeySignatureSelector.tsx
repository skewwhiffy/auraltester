import React from "react"
import { Col, Container, Row } from 'react-bootstrap'
import RadioButtons, { RadioButtonDefinition } from "../../util/RadioButtons"

type OnChangeHandler = (note: string) => void

interface Props {
  defaultValue?: string
  onChange: OnChangeHandler
}

interface State {
  keySignature: string
}

class KeySignatureSelector extends React.Component<Props, State>{
  constructor(props: Props) {
    super(props)
    this.state = {
      keySignature: props.defaultValue || 'C'
    }
    props.onChange(this.state.keySignature)
  }

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

  render() {
    return (
      <Container>
        <Row><Col>Key signature</Col></Row>
        <Row>
          <Col>
            <RadioButtons
              values={this.keySignatureValues}
              name='keySignature'
              defaultValue={this.state.keySignature}
              onChange={keySignature => this.props.onChange(keySignature)}
            />
          </Col>
        </Row>
      </Container>
    )
  }
}

export default KeySignatureSelector