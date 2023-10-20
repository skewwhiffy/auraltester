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

interface Item {
  displayName: string
  url: string
  eventKey: string
}

const items: Array<Item> = [{
  displayName: 'Home',
  url: '/home',
  eventKey: 'home'
}, {
  displayName: 'Clefs',
  url: '/clefs/',
  eventKey: 'clefs'
}, {
  displayName: 'Key Signatures',
  url: '/key-signatures/',
  eventKey: 'key-signatures'
}, {
  displayName: 'Scales',
  url: '/scales/',
  eventKey: 'scales'
}, {
  displayName: 'Intervals',
  url: '/intervals/',
  eventKey: 'intervals'
}]

const NavBar = (props: Props): JSX.Element => {
  let initialized = false
  const getSection = () => {
    return items
      .find(it => window.location.href.includes(it.url))
      ?.eventKey || 'home'
  }

  const [state, setState] = useState<State>({
    section: props.section ?? getSection()
  })

  const getTitle = () => {
    return items
      .find(it => it.eventKey === state.section)
      ?.displayName
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

  const sectionSelected = (event: string | null) => {
    if (!event) {
      return
    }
    setState({
      ...state,
      section: event
    })
  }

  return (
    <Navbar bg="light" expand="lg" onSelect={sectionSelected}>
      <Container>
        <Navbar.Collapse id="basic-navbar-nav">
          <Navbar.Brand>
            <Nav.Link
              as={Link}
              to="/"
              eventKey="home"
              title="Home"
            >The Aural Tester
            </Nav.Link>
          </Navbar.Brand>
          <NavDropdown title={getTitle()}>
            {items.map(it => (
              <NavDropdown.Item key={it.eventKey} as={Link} to={it.url} eventKey={it.eventKey} title={it.displayName}>{it.displayName}</NavDropdown.Item>
            ))}
          </NavDropdown>
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
