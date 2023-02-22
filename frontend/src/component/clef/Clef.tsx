import { Container, Nav } from "react-bootstrap"
import { Route, Routes } from "react-router"
import { Link } from "react-router-dom"
import { Example, Notes, Quiz } from "."
import TabbedSections from '../../util/TabbedSections'

const Clef = (): JSX.Element => {
  return (
    <TabbedSections />
  )
}

export default Clef
