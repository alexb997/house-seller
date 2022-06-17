import React, { useEffect, useState } from "react";
import { Button, Card, Col, Row } from "react-bootstrap";
import { useNavigate } from "react-router-dom";
import ReductionEmblem from "./ReductionEmblem";

function ListContainer(props) {
  const [views, setViews] = useState(0);

  const navigate = useNavigate();
  useEffect(() => {
    fetch("http://localhost:8080/views/appartment/" + props.appartment.id)
      .then((response) => response.json())
      .then((data) => setViews(data));
  }, []);

  const reduced = (price, reduction) => {
    let reducedPrice = price - (price * reduction) / 100;
    return reducedPrice;
  };

  return (
    <Card onClick={() => navigate(`/appartment/` + props.appartment.id)}>
      {props.appartment.reduction > 0 ? (
        <ReductionEmblem reduction={props.appartment.reduction} />
      ) : null}
      <Card.Img
        variant="top"
        src="https://media.istockphoto.com/photos/beautiful-residential-home-exterior-on-bright-sunny-day-with-green-picture-id1211174464?k=20&m=1211174464&s=612x612&w=0&h=fQ3ahmaJnYcZb0UQtBXvOhcuhHFTgK9BA5Mylic7Gnw="
      />
      <Card.Body>
        <Card.Title>
          <Row>Adress: {props.appartment.address}</Row>
          <Row>Owner: {props.appartment.owner}</Row>
        </Card.Title>
        <Card.Text>
          <Col className="info-column">
            <Row>Status: {props.appartment.status}</Row>
            {props.appartment.reduction !== 0 ? (
              <Row style={{ display: "inline", whiteSpace: "nowrap" }}>
                Price:
                <strong style={{ textDecorationLine: "line-through" }}>
                  {props.appartment.price}
                </strong>
                <strong style={{ color: "red" }}>
                  {reduced(props.appartment.price, props.appartment.reduction)}
                </strong>
              </Row>
            ) : (
              <Row>Price: {props.appartment.price}</Row>
            )}
          </Col>
        </Card.Text>
        <Button
          variant="primary"
          onClick={() => navigate(`/appartment/edit/` + props.appartment.id)}
        >
          Edit
        </Button>
        <Row className="justify-content-end">{views} views</Row>
      </Card.Body>
    </Card>
  );
}

export default ListContainer;
