import React, { useEffect, useState } from "react";
import { Button, Card, Col, Row } from "react-bootstrap";
import { useNavigate } from "react-router-dom";
import ReductionEmblem from "./ReductionEmblem";

function ListContainer(props) {
  const [views, setViews] = useState(0);

  const navigate = useNavigate();
  useEffect(() => {
    fetch("http://localhost:8080/views/house/" + props.house.id)
      .then((response) => response.json())
      .then((data) => setViews(data));
  }, []);

  const reduced = (price, reduction) => {
    let reducedPrice = price - (price * reduction) / 100;
    return reducedPrice;
  };

  return (
    <Card onClick={() => navigate(`/house/` + props.house.id)}>
      {props.house.reduction > 0 ? (
        <ReductionEmblem reduction={props.house.reduction} />
      ) : null}
      <Card.Img
        variant="top"
        src="https://media.istockphoto.com/photos/beautiful-residential-home-exterior-on-bright-sunny-day-with-green-picture-id1211174464?k=20&m=1211174464&s=612x612&w=0&h=fQ3ahmaJnYcZb0UQtBXvOhcuhHFTgK9BA5Mylic7Gnw="
      />
      <Card.Body>
        <Card.Title>
          <Row>Adress: {props.house.address}</Row>
          <Row>Owner: {props.house.owner}</Row>
        </Card.Title>
        <Card.Text>
          <Col className="info-column">
            <Row>Status: {props.house.status}</Row>
            <Row>Number: {props.house.number}</Row>
            <Row>Width x Length x Height: {props.house.dimensions}</Row>
            {props.house.reduction !== 0 ? (
              <Row style={{ display: "inline", whiteSpace: "nowrap" }}>
                Price:
                <strong style={{ textDecorationLine: "line-through" }}>
                  {props.house.price}
                </strong>
                {reduced(props.house.price, props.house.reduction)}
              </Row>
            ) : (
              <Row>Price: {props.house.price}</Row>
            )}
          </Col>
        </Card.Text>
        <Button
          variant="primary"
          onClick={() => navigate(`/house/edit/` + props.house.id)}
        >
          Edit
        </Button>
        <Row className="justify-content-end">{views} views</Row>
      </Card.Body>
    </Card>
  );
}

export default ListContainer;
