import { Container, Nav } from "react-bootstrap"
import { Route, Routes } from "react-router"
import { Link } from "react-router-dom"
import { Example, Notes, Quiz } from "../component/clef"

const TabbedSections = (): JSX.Element => {
  return (
    <Container>
      <Nav variant="tabs" defaultActiveKey="notes">
        <Nav.Item>
          <Nav.Link as={Link} to=".">Notes</Nav.Link>
        </Nav.Item>
        <Nav.Item>
          <Nav.Link as={Link} to="example">Example</Nav.Link>
        </Nav.Item>
        <Nav.Item>
          <Nav.Link as={Link} to="quiz">Quiz</Nav.Link>
        </Nav.Item>
      </Nav>
      <Routes>
        <Route index element={<Notes />} />
        <Route path="example" element={<Example />} />
        <Route path="quiz" element={<Quiz />} />
      </Routes>
    </Container>

  )
}

export default TabbedSections