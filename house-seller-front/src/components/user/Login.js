import { React, useState } from "react";
import { Container } from "react-bootstrap";
import PropTypes from "prop-types";

function Login() {
  const [user, setUser] = useState({});
  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");

  const HandleSubmit = async (e) => {
    e.preventDefault();
    //login logic - no backend yet
  };

  return (
    <Container className="login">
      <h3>Login form</h3>
      <form
        onSubmit={(e) => {
          HandleSubmit(e);
        }}
      >
        <label>Username:</label>
        <input
          className="form-input"
          type="text"
          name="username"
          placeholder={user.username}
          onKeyUp={(e) => setUsername(e.target.value)}
          onKeyDown={(e) => setUsername(e.target.value)}
        />
        <hr className="hr-invisible" />
        <label>Password:</label>
        <input
          className="form-input"
          type="password"
          name="password"
          placeholder={user.password}
          onKeyUp={(e) => setPassword(e.target.value)}
          onKeyDown={(e) => setPassword(e.target.value)}
        />
        <hr className="hr-invisible" />
        <input className="input-submit" type="submit" value="Submit" />
      </form>
    </Container>
  );
}
export default Login;
