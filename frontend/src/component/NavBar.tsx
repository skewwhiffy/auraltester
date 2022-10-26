import React from 'react'
import { Navbar, Container, Nav } from 'react-bootstrap'
import { Link } from 'react-router-dom'

interface Props { }
interface State {
  information: {
    version: string
  }
}
class NavBar extends React.Component<Props, State> {
  constructor(props: Props) {
    super(props)
  }
  
  async componentDidMount() {
    const newInformationResponse = await fetch('info')
    const newInformation = await newInformationResponse.json()
    console.log(newInformation)
    this.setState({
      ...this.state,
      information: newInformation
    })
  }

  render() {
    return (
      <Navbar bg="light" expand="lg">
        <Container>
          <Navbar.Brand href="#home">The Aural Tester</Navbar.Brand>
          <Navbar.Collapse id="basic-navbar-nav">
            <Nav className="me-auto">
              <Nav.Link
                as={Link}
                to="/"
                eventKey="/home"
                title="Home"
              >Home</Nav.Link>
              <Nav.Link
                as={Link}
                to="/scales"
                eventKey="/scales"
                title="Scales"
              >Scales</Nav.Link>
            </Nav>
          </Navbar.Collapse>
          <Navbar.Collapse className="justify-content-end">
            <Navbar.Text>
              { this.state?.information?.version == null ? 'No version information yet' : `Version: ${this.state.information.version}`}
            </Navbar.Text>
          </Navbar.Collapse>
        </Container>
      </Navbar>
    )
  }
}

export default NavBar