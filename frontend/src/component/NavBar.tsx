import React from 'react'
import { Navbar, Container, Nav } from 'react-bootstrap'
import { Link } from 'react-router-dom'

interface Props { }
interface State { }
class NavBar extends React.Component<Props, State> {
  constructor(props: Props) {
    super(props)
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
        </Container>
      </Navbar>
    )
  }
}

export default NavBar