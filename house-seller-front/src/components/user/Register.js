import { useEffect, useState } from "react";
import { Container } from "react-bootstrap";

function Register() {
  const [user, setUser] = useState({});
  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");

  useEffect(() => {
    setUser({
      username: username,
      password: password,
    });
  }, []);

  const handleSubmit = (e) => {
    e.preventDefault();
    const requestOptions = {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify({
        username: username,
        password: password,
      }),
    };
    fetch("http://localhost:8080/users/add", requestOptions)
      .then((response) => response.json())
      .then((data) => setUser(data));
  };
  const usernameChange = (e) => {
    setUsername(e);
    setUser({
      username: username,
    });
  };
  const passwordChange = (e) => {
    setPassword(e);
    setUser({
      password: password,
    });
  };

  return (
    <Container>
      <h3>Register form</h3>
      <form
        onSubmit={(e) => {
          handleSubmit(e);
        }}
      >
        <label>Username:</label>
        <input
          className="form-input"
          type="text"
          name="username"
          placeholder={user.username}
          onKeyUp={(e) => usernameChange(e.target.value)}
          onKeyDown={(e) => usernameChange(e.target.value)}
        />
        <hr className="hr-invisible" />
        <label>Password:</label>
        <input
          className="form-input"
          type="password"
          name="password"
          placeholder={user.password}
          onKeyUp={(e) => passwordChange(e.target.value)}
          onKeyDown={(e) => passwordChange(e.target.value)}
        />
        <hr className="hr-invisible" />
        <input className="input-submit" type="submit" value="Submit" />
      </form>
    </Container>
  );
}

export default Register;
