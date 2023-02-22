import { Example, Notes, Quiz } from "."
import TabbedSections, { TabbedSection } from '../../util/TabbedSections'

const sections: TabbedSection[] = [{
  text: "Notes",
  getElement: Notes
}, {
  path: "example",
  text: "Example",
  getElement: Example
}, {
  path: "quiz",
  text: "Quiz",
  getElement: Quiz
}]

const Clef = (): JSX.Element => {
  return (
    <TabbedSections sections={sections} />
  )
}

export default Clef
