import { useState, useEffect } from 'react'
import { Container, Nav, Navbar, Tab, Tabs } from 'react-bootstrap'
import { Route, Router, Routes } from 'react-router'
import { Link } from 'react-router-dom'
import Example from './Example'

interface State {
  tab: string
}

const Clef = (): JSX.Element => {
  return (
    <Container>
      <Nav variant="tabs" defaultActiveKey="notes">
        <Nav.Item>
          <Nav.Link href=".">Notes</Nav.Link>
        </Nav.Item>
        <Nav.Item>
          <Nav.Link href="example">Example</Nav.Link>
        </Nav.Item>
      </Nav>
      <Routes>
        <Route index element={<p>NOTES</p>} />
        <Route path="example" element={<Example />} />
      </Routes>
    </Container>
  )
}

export default Clef
