import { useEffect, useState } from "react";
import { Container, Row, Col, Button } from "react-bootstrap";
import { useParams } from "react-router-dom";

const EditHouse = () => {
  const { id } = useParams();
  const [house, setHouse] = useState({});
  const [status, setStatus] = useState("");
  const [dimensions, setDimensions] = useState("");
  const [address, setAddress] = useState("");
  const [owner, setOwner] = useState("");
  const [price, setPrice] = useState(0);
  const [number, setNumber] = useState(0);

  useEffect(() => {
    fetch("http://localhost:8080/houses/" + id)
      .then((response) => response.json())
      .then((data) => {
        setHouse(data);
        setStatus(house.status);
        setDimensions(house.dimensions);
        setAddress(house.address);
        setPrice(house.price);
        setOwner(house.owner);
        setNumber(house.number);
      })
      .catch((err) => console.log(err));
  }, []);

  const handleSubmit = async (e) => {
    e.preventDefault();
    const requestOptions = {
      method: "PUT",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify({
        status: status ? status : house.status,
        dimensions: dimensions ? dimensions : house.dimensions,
        address: address ? address : house.address,
        owner: owner ? owner : house.owner,
        number: number ? number : house.number,
        price: price ? price : house.price,
      }),
    };
    fetch("http://localhost:8080/houses/edit/" + id, requestOptions)
      .then((response) => response.json())
      .then((data) => setHouse(data));
  };

  return (
    <Container>
      <Row>
        <h3>Edit house by id: {id}</h3>
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
            <Button variant="primary" type="submit">
              Edit House
            </Button>
          </form>
        </Col>
      </Row>
    </Container>
  );
};

export default EditHouse;
