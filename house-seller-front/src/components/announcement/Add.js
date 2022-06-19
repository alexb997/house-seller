import { useEffect, useState } from "react";
import { Container, Row, Col, Button } from "react-bootstrap";

const AddAnnouncement = () => {
  const [announcement, setAnnouncement] = useState({});
  const [owner, setOwner] = useState("");
  const [price, setPrice] = useState(0);
  const [reduction, setReduction] = useState(0);
  const [type, setType] = useState("");

  useEffect(() => {
    setAnnouncement({
      owner: owner,
      price: price,
      reduction: reduction,
      type: type,
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
        owner: owner,
        price: price,
        reduction: reduction,
        type: type,
      }),
    };
    fetch("http://localhost:8080/announcements/add/", requestOptions)
      .then((response) => response.json())
      .then((data) => setAnnouncement(data));
  };

  return (
    <Container>
      <Row>
        <h3>Add Announcement</h3>
      </Row>
      <Row>
        <Col md={8}>
          <form
            onSubmit={(e) => {
              handleSubmit(e);
            }}
          >
            <Row className="justify-content-around">
              <Col>
                <label>Owner:</label>
              </Col>
              <Col>
                <input
                  type="text"
                  name="owner"
                  placeholder={announcement.owner}
                  onChange={(e) => setOwner(e.target.value)}
                />
              </Col>
            </Row>
            <hr className="hr-invisible" />
            <Row className="justify-content-around">
              <Col>
                <label>Reduction:</label>
              </Col>
              <Col>
                <input
                  type="number"
                  name="reduction"
                  placeholder={announcement.reduction}
                  onChange={(e) => setReduction(e.target.value)}
                />
              </Col>
            </Row>
            <hr className="hr-invisible" />
            <Row className="justify-content-around">
              <Col>
                <label>Price:</label>
              </Col>
              <Col>
                <input
                  type="number"
                  name="price"
                  placeholder={announcement.price}
                  onChange={(e) => setPrice(e.target.value)}
                />
              </Col>
            </Row>
            <hr className="hr-invisible" />
            <Row className="justify-content-around">
              <Col>
                <label>Type:</label>
              </Col>
              <Col>
                <input
                  type="text"
                  name="type"
                  placeholder={announcement.type}
                  onChange={(e) => setType(e.target.value)}
                />
              </Col>
            </Row>
            <hr className="hr-invisible" />
            <Button variant="primary" type="submit">
              Proceed to next step -&gt;
              {/* this will proceed to 2nd component and make the initial component dissapear. !Render effects! */}
            </Button>
          </form>
        </Col>
      </Row>
    </Container>
  );
};

export default AddAnnouncement;
