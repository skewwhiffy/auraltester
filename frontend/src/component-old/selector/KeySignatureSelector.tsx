import { Col, Container, Row } from 'react-bootstrap'
import RadioButtons, { RadioButtonDefinition } from '../../util-old/RadioButtons'

type OnChangeHandler = (note: string) => void

interface Props {
  value?: string
  onChange: OnChangeHandler
}

const keySignatureValues: RadioButtonDefinition[] = [
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
  { value: 'Cb', label: 'Cb / Ab minor' }
]

const KeySignatureSelector = (props: Props): JSX.Element => {
  const currentKeySignature = props.value
  return (
    <Container>
      <Row><Col>Key signature</Col></Row>
      <Row>
        <Col>
          <RadioButtons
            values={keySignatureValues}
            name='keySignature'
            value={currentKeySignature}
            onChange={keySignature => { props.onChange(keySignature) }}
          />
        </Col>
      </Row>
    </Container>
  )
}

export default KeySignatureSelector
