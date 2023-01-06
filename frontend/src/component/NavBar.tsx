import axios from 'axios'
import { useEffect, useState } from 'react'
import { Navbar, Container, Nav } from 'react-bootstrap'
import { Link } from 'react-router-dom'

interface State {
  information: {
    version: string
  }
}

const NavBar = (): JSX.Element => {
  const [state, setState] = useState<State | undefined>()

  useEffect(() => {
    (async () => {
      const newInformationResponse = await axios.get('/api/info')
      const newInformation = newInformationResponse.data
      // TODO: This gets called twice. Why?
      console.log('Component did mount in NavBar')
      setState({
        ...state,
        information: newInformation
      })
    })()
  }, [])

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
              to="/clefs"
              eventKey="/clefs"
              title="Clefs"
            >Clefs</Nav.Link>
            <Nav.Link
              as={Link}
              to="/key-signatures"
              eventKey="/key-signatures"
              title="Key Signature"
            >Key Signatures</Nav.Link>
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
            {state?.information?.version == null ? 'No version information yet' : `Version: ${state.information.version}`}
          </Navbar.Text>
        </Navbar.Collapse>
      </Container>
    </Navbar>
  )
}

export default NavBar
