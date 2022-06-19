import "./App.css";
import AppRouter from "./router/AppRouter";
import "bootstrap/dist/css/bootstrap.min.css";
import NavBar from "./components/navbar/NavBar";

function App() {
  return (
    <div className="App">
      <NavBar />
      <hr className="hr-invisible" />
      <hr className="hr-invisible" />
      <hr className="hr-invisible" />
      <AppRouter />
    </div>
  );
}

export default App;
