import { Notation } from 'react-abc'

interface Props {
  abc: string
}

const QuestionResponseAbcElement = (props: Props): JSX.Element => {
  return <Notation notation={props.abc} />
}

export default QuestionResponseAbcElement