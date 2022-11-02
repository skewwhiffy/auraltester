import React from 'react'
import RadioButtons from '../../util/RadioButtons'
import { capitalizeFirstCharacter } from '../../util'
import { Col, Container, Row } from 'react-bootstrap'

type OnChangeHandler = (value: string) => void

interface Props {
  defaultValue?: string,
  onChange: OnChangeHandler
}

interface State { }

class ClefSelector extends React.Component<Props, State> {
  private radioButtons: RadioButtons

  constructor(props: Props) {
    super(props);
    const values = ['treble', 'alto', 'tenor', 'bass']
      .map(it => ({
        value: it,
        label: `${capitalizeFirstCharacter(it)} Clef`
      }))
    this.radioButtons = new RadioButtons({
      values,
      name: 'clef',
      defaultValue: props.defaultValue || 'treble',
      onChange: props.onChange
    })
  }

  render() {
    return (
      <Container>
        <Row><Col>Clef</Col></Row>
        <Row><Col>{this.radioButtons.render()}</Col></Row>
      </Container>
    )
  }
}

export default ClefSelector