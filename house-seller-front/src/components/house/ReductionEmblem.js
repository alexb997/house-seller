import React from "react";
import { Container, Row } from "react-bootstrap";
import "./house.css";

function ReductionEmblem(props) {
  return (
    <Container className="emblem">
      <Row className="justify-content-end">{props.reduction}%</Row>
    </Container>
  );
}

export default ReductionEmblem;
