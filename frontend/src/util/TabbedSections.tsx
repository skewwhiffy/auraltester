import { Container, Nav } from "react-bootstrap"
import { Route, Routes } from "react-router"
import { Link } from "react-router-dom"
import { capitalizeFirstCharacter } from "."

export type TabbedSection = {
  getElement: () => JSX.Element
} & ({ path: string } | { text: string })

export interface Props {
  sections: TabbedSection[]
}

const getNavLink = (it: TabbedSection): JSX.Element => {
  const to = "path" in it ? it.path : "."
  const text = "text" in it ? it.text : capitalizeFirstCharacter(it.path)
  return (
    <Nav.Item key={to}>
      <Nav.Link as={Link} to={to}>{text}</Nav.Link>
    </Nav.Item>
  )
}

const getRoute = (it: TabbedSection): JSX.Element => {
  return "path" in it
    ? <Route key={it.path} path={it.path} element={it.getElement()} />
    : <Route key="." index element={it.getElement()} />
}

const TabbedSections = (props: Props): JSX.Element => {
  return (
    <Container>
      <Nav variant="tabs">
        {props.sections.map(getNavLink)}
      </Nav>
      <Routes>
        {props.sections.map(getRoute)}
      </Routes>
    </Container >
  )
}

export default TabbedSections