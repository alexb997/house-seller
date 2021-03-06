import { useEffect, useState } from "react";
import { Button, Col, Row, Container } from "react-bootstrap";
import ListContainer from "./ListContainer";
import MostViewed from "./MostViewed";
import Reductions from "./Reductions";

function ListHouses() {
  const [isLoading, setIsLoading] = useState(true);
  const [housesList, setHousesList] = useState([]);
  const [housesPerPage, setHousesPerPage] = useState(6);
  const [currentPage, setCurrentPage] = useState(1);
  const [totalPages, setTotalPages] = useState();
  const [totalElements, setTotalElements] = useState();
  const [isUpdating, setIsUpdating] = useState(false);
  const [showButtonPrev, setShowButtonPrev] = useState(false);
  const [showButtonNext, setShowButtonNext] = useState(false);

  useEffect(() => {
    const fetchHouses = () => {
      return fetch(
        "http://localhost:8080/houses/all" +
          "?page=" +
          (currentPage - 1) +
          "&size=" +
          housesPerPage
      )
        .then((response) => response.json())
        .then((data) => {
          setIsLoading(false);
          setHousesList(data.items);
          setTotalPages(data.totalPages);
          setTotalElements(data.totalItems);
          setIsUpdating(false);
          setShowButtonPrev(data.currentPage + 1 > 1);
          setShowButtonNext(data.currentPage + 1 !== data.totalPages);
        })
        .catch((err) => console.log(err));
    };
    fetchHouses();
  }, [isUpdating]);

  const prevPage = () => {
    if (currentPage - 1 <= 1) {
      setShowButtonPrev(false);
      setCurrentPage(currentPage - 1);
      setIsUpdating(true);
    } else {
      setShowButtonNext(true);
      setCurrentPage(currentPage - 1);
      setIsUpdating(true);
    }
  };

  const nextPage = () => {
    if (currentPage + 1 >= totalPages) {
      setShowButtonNext(false);
      setCurrentPage(currentPage + 1);
      setIsUpdating(true);
    } else {
      setShowButtonPrev(true);
      setCurrentPage(currentPage + 1);
      setIsUpdating(true);
    }
  };

  return (
    <Container>
      {isLoading && <p>Loading...</p>}
      <Row className="justify-content-around">
        Houses - {totalElements} announcements
      </Row>
      <hr />
      <Row className="justify-content-around">
        <MostViewed />
      </Row>
      <hr />
      <hr />
      <Row className="justify-content-around">
        <Reductions />
      </Row>
      <hr />
      <Row>
        {housesList.length !== 0 ? (
          housesList.map((c, index) => (
            <Col md={4} key={index}>
              <ListContainer house={c} />
            </Col>
          ))
        ) : (
          <h3>No houses documented</h3>
        )}
      </Row>
      <hr />
      <Row className="justify-content-around">
        <div style={{ textAlign: "center" }}>
          {!showButtonPrev ? (
            <span></span>
          ) : (
            <Button onClick={() => prevPage()}>Prev..</Button>
          )}
          {currentPage}
          {!showButtonNext ? (
            <span></span>
          ) : (
            <Button onClick={() => nextPage()}>Next..</Button>
          )}
        </div>
      </Row>
      <Row className="justify-content-around">Out of {totalPages} pages</Row>
    </Container>
  );
}

export default ListHouses;
