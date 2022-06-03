import { React } from "react";
import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import AddHouse from "../components/house/Add";
import Details from "../components/house/Details";
import EditHouse from "../components/house/Edit";
import ListHouses from "../components/house/List";
import Register from "../components/user/Register";

function AppRouter() {
  return (
    <Router>
      <Routes>
        <Route path="/houses" element={<ListHouses />} />
        <Route path="/house/add" element={<AddHouse />} />
        <Route path="/house/:id" elements={<Details />} />
        <Route path="/house/edit/:id" element={<EditHouse />} />
        <Route path="/register" element={<Register />} />
      </Routes>
    </Router>
  );
}

export default AppRouter;
