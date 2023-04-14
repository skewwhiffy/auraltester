import RadioButtons from "../../util/RadioButtons"
import { useState } from "react"

type OnChangeHandler = (note: string) => void

interface Props {
  noteName?: string
  onChange: OnChangeHandler
}

interface State {
  noteName?: string
}

const NoteNameSelector = (props: Props): JSX.Element => {
  const onChange = (note: string) => {
    setState({ ...state, noteName: note })
    props.onChange(note);
  }

  const [state, setState] = useState<State>({
    noteName: props.noteName
  })

  return (
    <RadioButtons
      values={'ABCDEFG'.split('').map(it => ({ value: it }))}
      name='note'
      value={state.noteName}
      onChange={onChange}
    />
  )
}

export default NoteNameSelector