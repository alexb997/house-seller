import { useEffect, useState } from "react";
import { Container, Row, Col, Button } from "react-bootstrap";

const AddHouse = () => {
  const [house, setHouse] = useState({});
  const [status, setStatus] = useState("");
  const [dimensions, setDimensions] = useState("");
  const [address, setAddress] = useState("");
  const [owner, setOwner] = useState("");
  const [price, setPrice] = useState(0);
  const [number, setNumber] = useState(0);
  const [description, setDescription] = useState("");

  useEffect(() => {
    setHouse({
      status: status,
      dimensions: dimensions,
      address: address,
      owner: owner,
      number: number,
      price: price,
      description: description,
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
        dimensions: dimensions,
        address: address,
        owner: owner,
        number: number,
        price: price,
        description: description,
      }),
    };
    fetch("http://localhost:8080/houses/add/", requestOptions)
      .then((response) => response.json())
      .then((data) => setHouse(data));
  };

  return (
    <Container>
      <Row>
        <h3>Add House</h3>
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
                  placeholder={house.address}
                  onChange={(e) => setAddress(e.target.value)}
                />
              </Col>
              <Col md={6}>
                <label>Status:</label>
                <input
                  type="text"
                  name="status"
                  placeholder={house.status}
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
                  placeholder={house.owner}
                  onChange={(e) => setOwner(e.target.value)}
                />
              </Col>
              <Col md={6}>
                <label>Dimensions:</label>
                <input
                  type="text"
                  name="dimensions"
                  placeholder={house.dimensions}
                  onChange={(e) => setDimensions(e.target.value)}
                />
              </Col>
            </Row>
            <hr />
            <Row>
              <Col md={6}>
                <label>Number:</label>
                <input
                  type="number"
                  name="number"
                  placeholder={house.number}
                  onChange={(e) => setNumber(e.target.value)}
                />
              </Col>
              <Col md={6}>
                <label>Price:</label>
                <input
                  type="number"
                  name="price"
                  placeholder={house.price}
                  onChange={(e) => setPrice(e.target.value)}
                />
              </Col>
            </Row>
            <hr />
            <Row>
              <Col>
                <label>description:</label>
                <input
                  type="text"
                  name="description"
                  placeholder={house.description}
                  onChange={(e) => setDescription(e.target.value)}
                />
              </Col>
            </Row>
            <Button variant="primary" type="submit">
              Add House
            </Button>
          </form>
        </Col>
      </Row>
    </Container>
  );
};

export default AddHouse;
