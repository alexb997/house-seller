import { useEffect, useState } from "react";
import { Container, Row, Col, Button } from "react-bootstrap";
import { useParams } from "react-router-dom";

const EditAppartment = () => {
  const { id } = useParams();
  const [appartment, setAppartment] = useState({});
  const [status, setStatus] = useState("");
  const [dimensions, setDimensions] = useState("");
  const [address, setAddress] = useState("");
  const [owner, setOwner] = useState("");
  const [price, setPrice] = useState(0);
  const [number, setNumber] = useState(0);

  useEffect(() => {
    fetch("http://localhost:8080/appartments/" + id)
      .then((response) => response.json())
      .then((data) => {
        setAppartment(data);
        setStatus(appartment.status);
        setDimensions(appartment.dimensions);
        setAddress(appartment.address);
        setPrice(appartment.price);
        setOwner(appartment.owner);
        setNumber(appartment.number);
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
        status: status ? status : appartment.status,
        address: address ? address : appartment.address,
        owner: owner ? owner : appartment.owner,
        reduction: reduction ? reduction : appartment.reduction,
        price: price ? price : appartment.price,
      }),
    };
    fetch("http://localhost:8080/appartments/edit/" + id, requestOptions)
      .then((response) => response.json())
      .then((data) => setAppartment(data));
  };

  return (
    <Container>
      <Row>
        <h3>Edit Appartment by id: {id}</h3>
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
                <label>Number:</label>
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
            <Button variant="primary" type="submit">
              Edit Appartment
            </Button>
          </form>
        </Col>
      </Row>
    </Container>
  );
};

export default EditAppartment;
