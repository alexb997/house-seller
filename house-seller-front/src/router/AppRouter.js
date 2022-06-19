import { React } from "react";
import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import AddAnnouncement from "../components/announcement/Add";
import AddAppartment from "../components/appartment/Add";
import EditAppartment from "../components/appartment/Edit";
import AddHouse from "../components/house/Add";
import Details from "../components/house/Details";
import EditHouse from "../components/house/Edit";
import ListHouses from "../components/house/List";
import Register from "../components/user/Register";
import Home from "../pages/Home";

function AppRouter() {
  return (
    <Router>
      <Routes>
        <Route path="/" element={<Home />} />
        <Route path="/list" element={<ListHouses />} />
        <Route path="/house/add" element={<AddHouse />} />
        <Route path="/house/:id" element={<Details />} />
        <Route path="/house/edit/:id" element={<EditHouse />} />
        <Route path="/appartment/add" element={<AddAppartment />} />
        <Route path="/appartment/edit/:id" element={<EditAppartment />} />
        <Route path="/announcement/add" element={<AddAnnouncement />} />
        <Route path="/register" element={<Register />} />
      </Routes>
    </Router>
  );
}

export default AppRouter;
