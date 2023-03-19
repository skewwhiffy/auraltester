import Notes from "./Notes"
import Example from "./Example"
import Quiz from "./Quiz"
import TabbedSections, { TabbedSection } from '../../util/TabbedSections'

const sections: TabbedSection[] = [{
  text: "Notes",
  getElement: Notes
}, {
  path: "example",
  getElement: Example
}, {
  path: "quiz",
  getElement: Quiz
}]

const Clef = (): JSX.Element => {
  return (
    <TabbedSections sections={sections} />
  )
}

export default Clef
