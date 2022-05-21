import { React } from "react";
import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import AddHouse from "../components/house/Add";
import EditHouse from "../components/house/Edit";
import ListHouses from "../components/house/List";

function AppRouter() {
  return (
    <Router>
      <Routes>
        <Route path="/houses" element={<ListHouses />} />
        <Route path="/house/add" element={<AddHouse />} />
        <Route path="/house/edit/:id" element={<EditHouse />} />
      </Routes>
    </Router>
  );
}

export default AppRouter;
