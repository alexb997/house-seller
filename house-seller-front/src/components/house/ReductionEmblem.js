import React from "react";
import { Container, Row } from "react-bootstrap";
import "./house.css";

function ReductionEmblem(props) {
  return (
    <Container className="emblem">
      <Row className="justify-content-center">{props.reduction}%</Row>
    </Container>
  );
}

export default ReductionEmblem;
