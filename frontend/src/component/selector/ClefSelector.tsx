import React from "react"
import RadioButtons from "../../util/RadioButtons"

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
        label: `${this.capitaliseFirstCharacter(it)} Clef`
      }))
    this.radioButtons = new RadioButtons({
      values,
      name: 'clef',
      defaultValue: props.defaultValue || 'treble',
      onChange: props.onChange
    })
  }

  render() {
    return this.radioButtons.render()
  }

  private capitaliseFirstCharacter(source: string) {
    return `${source[0].toUpperCase()}${source.substring(1)}`
  }
}

export default ClefSelector