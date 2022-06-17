import { useEffect, useState } from "react";
import { Button, Col, Row, Container } from "react-bootstrap";
import ListContainer from "./ListContainer";
import MostViewed from "./MostViewed";
import Reductions from "./Reductions";

function ListAppartments() {
  const [isLoading, setIsLoading] = useState(true);
  const [appartmentsList, setAppartmentsList] = useState([]);
  const [appartmentsPerPage, setAppartmentsPerPage] = useState(6);
  const [currentPage, setCurrentPage] = useState(1);
  const [totalPages, setTotalPages] = useState();
  const [totalElements, setTotalElements] = useState();
  const [isUpdating, setIsUpdating] = useState(false);
  const [showButtonPrev, setShowButtonPrev] = useState(false);
  const [showButtonNext, setShowButtonNext] = useState(false);

  useEffect(() => {
    const fetchAppartments = () => {
      return fetch(
        "http://localhost:8080/appartments/all" +
          "?page=" +
          (currentPage - 1) +
          "&size=" +
          appartmentsPerPage
      )
        .then((response) => response.json())
        .then((data) => {
          setIsLoading(false);
          setAppartmentsList(data.items);
          setTotalPages(data.totalPages);
          setTotalElements(data.totalItems);
          setIsUpdating(false);
          setShowButtonPrev(data.currentPage + 1 > 1);
          setShowButtonNext(data.currentPage + 1 !== data.totalPages);
        })
        .catch((err) => console.log(err));
    };
    fetchAppartments();
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
        Appartments - {totalElements} announcements
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
        {appartmentsList.length !== 0 ? (
          appartmentsList.map((c, index) => (
            <Col md={4} key={index}>
              <ListContainer appartment={c} />
            </Col>
          ))
        ) : (
          <h3>No appartments documented</h3>
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

export default ListAppartments;
