import { React } from "react";
import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import ListHouses from "../components/house/List";

function AppRouter() {
  return (
    <Router>
      <Routes>
        <Route path="/" element={<ListHouses />} />
        {/* <Route path="/home/add" element={<AddHouse />} />
        <Route path="/home/edit/:id" element={<EditHouse />} /> */}
      </Routes>
    </Router>
  );
}

export default AppRouter;
