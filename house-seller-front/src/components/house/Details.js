import { useEffect, useState } from "react";
import { Button, Col, Container, Row } from "react-bootstrap";
import { useNavigate, useParams } from "react-router-dom";
import ListContainer from "./ListContainer";

function Details() {
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
        const requestOptions = {
          method: "POST",
          headers: {
            "Content-Type": "application/json",
          },
          body: JSON.stringify({
            userID: "userID",
            houseID: id,
            dateView: new Date().toUTCString,
          }),
        };
        fetch("http://localhost:8080/views/add/", requestOptions).then(
          (response) => response.json()
        );
      })
      .catch((err) => console.log(err));
  }, []);
  return (
    <Container>
      <ListContainer house={house} />
    </Container>
  );
}

export default Details;
