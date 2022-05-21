import { Card, Col, Row } from "react-bootstrap";

function ListContainer(props) {
  return (
    <Card>
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
            <Row>Price: {props.house.price}</Row>
          </Col>
        </Card.Text>
      </Card.Body>
    </Card>
  );
}

export default ListContainer;
