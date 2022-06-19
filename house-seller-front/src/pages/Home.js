import { useEffect, useState } from "react";
import { Container, Row, Col, Button } from "react-bootstrap";

const Home = () => {
  return (
    <Container>
      <Row id="filters-zone">
        <h3>Filter Component</h3>
      </Row>
      <hr className="hr-invisible" />
      <Row id="promoted-announcements">
        <h3>Promoted Announcements Components</h3>
      </Row>
      <hr className="hr-invisible" />
      <Row id="quick-search-announcements">
        <h3>Quick Search </h3>
      </Row>
      <hr className="hr-invisible" />
      <Row id="blog-zone">
        <h3>Blog </h3>
      </Row>
      <hr className="hr-invisible" />
    </Container>
  );
};

export default Home;
