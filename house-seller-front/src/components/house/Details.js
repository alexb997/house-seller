import { useEffect, useState } from "react";
import { Button, Col, Container, Row } from "react-bootstrap";
import { useNavigate, useParams } from "react-router-dom";

function Details() {
  const { id } = useParams();
  const { view, setView } = useState({});
  useEffect(() => {
    const requestOptions = {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify({
        userID: "userID",
        houseID: id,
        viewDate: new Date(),
      }),
    };
    fetch("http://localhost:8080/views/add/", requestOptions)
      .then((response) => response.json())
      .then((data) => setView(data));
  }, []);
  return <Container>Details page</Container>;
}

export default Details;
