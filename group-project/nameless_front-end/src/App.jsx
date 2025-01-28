import React from "react";
import Dashboard from "./components/dashboard/DashBoard";
import { BrowserRouter as Router, Route, Routes } from "react-router-dom";

const App = () => {
  return (
    <Router>
      <main>
        <Routes>
          <Route path="/" element={<Dashboard />} />
        </Routes>
      </main>
    </Router>
  );
};

export default App;
