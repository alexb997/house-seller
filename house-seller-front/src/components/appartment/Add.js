import { useEffect, useState } from "react";
import { Container, Row, Col, Button } from "react-bootstrap";

const AddAppartment = (props) => {
  const [announcement, setAnnouncement] = useState({});
  const [appartment, setAppartment] = useState({});
  const [status, setStatus] = useState("");
  const [characteristics, setCharacteristics] = useState([]);
  const [address, setAddress] = useState("");
  const [owner, setOwner] = useState("");
  const [price, setPrice] = useState(0);
  const [reduction, setReduction] = useState(0);

  useEffect(() => {
    setAppartment({
      status: status,
      address: address,
      characteristics: characteristics,
    });
    setAnnouncement({
      owner: props.announcement.owner,
      price: props.announcement.price,
      reduction: props.announcement.reduction,
      announcedObject: appartment,
    });
  }, []);

  const handleSubmit = async (e) => {
    e.preventDefault();
    const requestOptions = {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify({
        status: status,
        address: address,
        owner: owner,
        reduction: reduction,
        price: price,
        characteristics: characteristics,
      }),
    };
    fetch("http://localhost:8080/appartments/add/", requestOptions)
      .then((response) => response.json())
      .then((data) => setAppartment(data));
  };

  return (
    <Container>
      <Row>
        <h3>Add Appartment</h3>
      </Row>
      <Row>
        <Col md={8}>
          <form
            onSubmit={(e) => {
              handleSubmit(e);
            }}
          >
            <Row>
              <Col md={6}>
                <label>Address:</label>
                <input
                  type="text"
                  name="address"
                  placeholder={appartment.address}
                  onChange={(e) => setAddress(e.target.value)}
                />
              </Col>
              <Col md={6}>
                <label>Status:</label>
                <input
                  type="text"
                  name="status"
                  placeholder={appartment.status}
                  onChange={(e) => setStatus(e.target.value)}
                />
              </Col>
            </Row>
            <hr />
            <Row>
              <Col md={6}>
                <label>Owner:</label>
                <input
                  type="text"
                  name="owner"
                  placeholder={appartment.owner}
                  onChange={(e) => setOwner(e.target.value)}
                />
              </Col>
            </Row>
            <hr />
            <Row>
              <Col md={6}>
                <label>Reduction:</label>
                <input
                  type="number"
                  name="reduction"
                  placeholder={appartment.reduction}
                  onChange={(e) => setReduction(e.target.value)}
                />
              </Col>
              <Col md={6}>
                <label>Price:</label>
                <input
                  type="number"
                  name="price"
                  placeholder={appartment.price}
                  onChange={(e) => setPrice(e.target.value)}
                />
              </Col>
            </Row>
            <hr />
            <Button variant="primary" type="submit">
              Add Appartment
            </Button>
          </form>
        </Col>
      </Row>
    </Container>
  );
};

export default AddAppartment;
