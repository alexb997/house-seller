import { useEffect, useState } from "react";
import { Button, Col, Row, Container } from "react-bootstrap";
import ListContainer from "./ListContainer";

function MostViewed() {
  const [isLoading, setIsLoading] = useState(true);
  const [mostViewedHousesIDList, setMostViewedHousesIDList] = useState([]);
  const [mostViewdHouses, setMostViewedHouses] = useState([]);
  const [isUpdating, setIsUpdating] = useState(false);

  useEffect(() => {
    const fetchHouse = (id) => {
      const response = fetch("http://localhost:8080/houses/" + id);
      const data = response.json();
      setMostViewedHouses((mostViewdHouses) => [...mostViewdHouses, data]);
    };
    const fetchHouses = () => {
      return fetch("http://localhost:8080/views/byHouses/topThree")
        .then((response) => response.json())
        .then((data) => {
          setIsLoading(false);
          setMostViewedHousesIDList(data.items);
          setIsUpdating(false);
          mostViewedHousesIDList.map((id) => fetchHouse(id));
        })
        .catch((err) => console.log(err));
    };
    fetchHouses();
  }, [isUpdating]);

  return (
    <Container>
      {isLoading && <p>Loading...</p>}
      <Row className="justify-content-around">Most Viewed</Row>
      <hr />
      <Row>
        {mostViewdHouses.length !== 0 ? (
          mostViewdHouses.map((c, index) => (
            <Col md={4} key={index}>
              <ListContainer house={c} />
            </Col>
          ))
        ) : (
          <h3>No houses documented</h3>
        )}
      </Row>
    </Container>
  );
}

export default MostViewed;
