import { Container, Nav, Navbar, NavDropdown } from "react-bootstrap";

function NavBar() {
  return (
    <Navbar
      className="navShadow"
      bg="light"
      variant="light"
      fixed="top"
      expand="lg"
    >
      <Container fluid>
        <Navbar.Collapse className="justify-content-end">
          <Nav>
            <Nav.Link href="/">Home</Nav.Link>
            <NavDropdown title="Announcements" id="basic-nav-dropdown">
              <NavDropdown.Item href="/list">List</NavDropdown.Item>
              <NavDropdown.Item href="/announcement/add">Add</NavDropdown.Item>
            </NavDropdown>
            <Nav.Link href="/register">Register</Nav.Link>
          </Nav>
        </Navbar.Collapse>
      </Container>
    </Navbar>
  );
}

export default NavBar;
