import { Col, Container, Form, Row } from 'react-bootstrap'
import RadioButtons from '../../util/RadioButtons'
import { useState } from 'react'

interface State {
  intervalQuality?: string
  intervalSize?: string
}

interface Props {
  onChange(intervalQuality: string, intervalSize: string): void
}

export const IntervalSizeSelector = (props: Props) => {
  const [state, setState] = useState<State>({})
  
  const onStateChange = (state: State): void => {
    if (state.intervalQuality === undefined || state.intervalSize === undefined) {
      return
    }
    props.onChange(
      state.intervalQuality,
      state.intervalSize
    )
  }

  const onFormChange = (newState: State): void => {
    setState(newState)
    onStateChange(newState)
  }

  return (
    <Container>
      <Row>
        <Col>Interval</Col></Row>
      <Row>
        <Col>
          <RadioButtons
            values={['diminished', 'minor', 'major', 'perfect', 'augmented'].map(value => ({ value }))}
            name='intervalQuality'
            value={state.intervalQuality}
            onChange={intervalQuality => { onFormChange({ ...state, intervalQuality }) }}
          />
        </Col>
        <Col>
          <RadioButtons
            values={Array.from(Array(8).keys()).map(it => it + 1).map(it => ({ value: `${it}` }))}
            name='intervalSize'
            value={state.intervalSize}
            onChange={intervalSize => { onFormChange({ ...state, intervalSize }) }}
          />
        </Col>
      </Row>
    </Container>)
}