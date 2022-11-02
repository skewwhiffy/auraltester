import React from 'react'

interface Props { }

interface State {
  abc: string
}

class Interval extends React.Component<Props, State> {
  constructor(props: Props) {
    super(props)
    this.state = {
      abc: `

      `
    }
  }
  
  render() {
    return (
      <h1>Hello mum</h1>
    )
  }
}
export default Interval