import { capitalizeFirstCharacter } from '../../util/helpers'
import RadioButtons, { RadioButtonDefinition } from '../../util/RadioButtons'
import { Col, Container, Row } from 'react-bootstrap'

type OnChangeHandler = (value: string) => void

export interface Props {
  value?: string
  onChange: OnChangeHandler
}

const ClefSelector = (props: Props): JSX.Element => {
  const values: RadioButtonDefinition[] = ['treble', 'alto', 'tenor', 'bass']
    .map(it => ({
      value: it,
      label: `${capitalizeFirstCharacter(it)} Clef`
    }))
  return (
    <Container>
      <Row><Col>Clef</Col></Row>
      <Row><Col>
        <RadioButtons
          values={values}
          name='clef'
          value={props.value}
          onChange={props.onChange}
        />
      </Col></Row>
    </Container>
  )
}

export default ClefSelector
