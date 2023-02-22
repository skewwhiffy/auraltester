import { Container, Nav } from "react-bootstrap"
import { Route, Routes } from "react-router"
import { Link } from "react-router-dom"
import { Example, Notes, Quiz } from "../component/clef"

export interface TabbedSection {
  path?: string
  text: string
  getElement: () => JSX.Element
}

export interface Props {
  sections: TabbedSection[]
}

const TabbedSections = (props: Props): JSX.Element => {
  return (
    <Container>
      <Nav variant="tabs">
        {props.sections.map(it => (
          <Nav.Item>
            <Nav.Link as={Link} to={it.path || "."}>{it.text}</Nav.Link>
          </Nav.Item>
        ))}
      </Nav>
      <Routes>
        {props.sections.map(it => (
          it.path ? <Route path={it.path} element={it.getElement()} /> : <Route index element={it.getElement()} />
        ))}
      </Routes>
    </Container >
  )
}

export default TabbedSections