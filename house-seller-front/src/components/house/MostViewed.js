import { useEffect, useState } from "react";
import { Button, Col, Row, Container } from "react-bootstrap";
import ListContainer from "./ListContainer";

function MostViewed() {
  const [isLoading, setIsLoading] = useState(true);
  const [mostViewedHousesIDList, setMostViewedHousesIDList] = useState([]);
  const [house1, setHouse1] = useState({});
  const [isUpdating, setIsUpdating] = useState(false);

  useEffect(() => {
    const fetchHouses = () => {
      return fetch(
        "http://localhost:8080/views/byHouses/topThree" 
      )
        .then((response) => response.json())
        .then((data) => {
          setIsLoading(false);
          setMostViewedHousesIDList(data.items);
          setIsUpdating(false);
        })
        .catch((err) => console.log(err));
    };
    fetchHouses();
  }, [isUpdating]);

  const fetchHouse = (id) => {
    return fetch("http://localhost:8080/houses/" + id)
      .then((response) => response.json())
      .then((data) => {
        return data;
      })
      .catch((err) => console.log(err));
  };

  return (
    <Container>
      {isLoading && <p>Loading...</p>}
      <Row className="justify-content-around">
        Most Viewed
      </Row>
      <hr />
      <Row>
        {mostViewedHousesIDList.length !== 0 ? (
          mostViewedHousesIDList.map((c, index) => (
            <Col md={4} key={index}>
              <ListContainer house={fetchHouse(c)} />
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
