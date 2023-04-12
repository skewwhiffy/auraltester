interface Props {
  text: string
}

const textStyle = {
  fontStyle: "bold"
}

const QuestionResponseTextElement = (props: Props): JSX.Element => {
  return <p style={textStyle}>{props.text}</p>
}

export default QuestionResponseTextElement