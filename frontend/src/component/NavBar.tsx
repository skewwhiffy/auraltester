import axios from 'axios'
import React, { useEffect, useState } from 'react'
import { Navbar, Container, Nav } from 'react-bootstrap'
import { Link } from 'react-router-dom'

interface Props { }
interface State {
  information: {
    version: string
  }
}

const NavBar = () => {
  let initialized = false
  const [state, setState] = useState<State | undefined>()

  useEffect(() => {
    (async () => {
      if (initialized) return
      initialized = true
      const newInformationResponse = await axios.get('/api/info')
      const newInformation = newInformationResponse.data
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

class NavBarOld extends React.Component<Props, State> {

}

export default NavBar