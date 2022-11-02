import axios from 'axios'
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
  
  async componentDidMount() {
    const newInformationResponse = await axios.get('info')
    const newInformation = newInformationResponse.data
    // TODO: This gets called twice. Why?
    console.log('Component did mount in NavBar')
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
              <Nav.Link
                as={Link}
                to="/intervals"
                eventKey="/intervals"
                title="Intervals"
              >Intervals</Nav.Link>
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