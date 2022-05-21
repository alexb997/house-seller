import { useEffect, useState } from "react";
import { Button, Col, Row, Container } from "react-bootstrap";
import ListContainer from "./ListContainer";

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

  useEffect(async () => {
    await fetch(
      "http://localhost:8080/houses/all?page=" +
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
        setShowButtonNext(data.currentPage + 1 != data.totalPages);
      })
      .catch((err) => console.log(err));
  }, [isUpdating]);

  const prevPage = () => {
    if (currentPage - 1 < 1) {
      setShowButtonPrev(false);
    } else {
      setShowButtonNext(true);
      setCurrentPage(currentPage - 1);
      setIsUpdating(true);
    }
  };

  const nextPage = () => {
    if (currentPage + 1 > totalPages) {
      setShowNext(true);
    } else {
      setCurrentPage(currentPage + 1);
      setIsUpdating(true);
    }
  };

  return (
    <Container style={{ backgroundColor: "black" }}>
      {isLoading && <p>Loading...</p>}
      <Row className="justify-content-around">
        Houses - {totalElements} announcements
      </Row>
      <hr />
      <Row>
        {housesList.length !== 0 ? (
          housesList.map((c, index) => (
            <Col md={3}>
              <ListContainer house={c} key={index} />
            </Col>
          ))
        ) : (
          <h3>No houses documented</h3>
        )}
      </Row>
      <Row>
        <span>
          {!showButtonLeft ? (
            <span></span>
          ) : (
            <Button onClick={() => prevPage()}>Prev..</Button>
          )}
          <span>{currentPage}</span>
          {!showButtonNext ? (
            <span></span>
          ) : (
            <Button onClick={() => nextPage()}>Next..</Button>
          )}
          <br />
          Out of {totalPages} pages
        </span>
      </Row>
    </Container>
  );
}

export default ListHouses;
