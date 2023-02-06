import axios from 'axios'
import { useEffect, useState } from 'react'
import { Navbar, NavDropdown, Container, Nav } from 'react-bootstrap'
import { Link } from 'react-router-dom'

interface State {
  information?: {
    version: string
  }
  section: string
}

interface Props {
  section?: string
}

const NavBar = (props: Props): JSX.Element => {
  let initialized = false
  const [state, setState] = useState<State>({
    section: props.section ?? 'home'
  })

  const getTitle = () => {
    switch (state.section) {
      case 'home':
        return 'Home'
      default:
        return 'POO'
    }
  }

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
  const sectionSelected = (event: React.ChangeEvent<HTMLInputElement>) => {
    console.log(event)
  }

  return (
    <Navbar bg="light" expand="lg">
      <Container>
        <Navbar.Collapse id="basic-navbar-nav">
          <Navbar.Brand>
            <Nav.Link
              as={Link}
              to="/"
              eventKey="/home"
              title="Home"
            >The Aural Tester
            </Nav.Link>
          </Navbar.Brand>
          <NavDropdown onSelect={sectionSelected} title={getTitle()}>
            <NavDropdown.Item
              as={Link}
              to="/clefs"
              eventKey="/clefs"
              title="Clefs"
            >Clefs
            </NavDropdown.Item>
            <NavDropdown.Item
              as={Link}
              to="/key-signatures"
              eventKey="/key-signatures"
              title="Key Signature"
            >Key Signatures
            </NavDropdown.Item>
          </NavDropdown>
          <Nav className="me-auto">
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
