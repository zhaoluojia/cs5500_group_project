import './App.css';
import React from 'react';
import { BrowserRouter } from 'react-router-dom';
import {Route, Routes} from "react-router";
import NavBarComponent from './components/NavBarComponent';
import HomeComponent from './components/HomeComponent';
import AddExerciseComponent from "./components/AddExerciseComponent";
import SetGoalComponent from "./components/SetGoalComponent";
import ExerciseSummaryComponent from "./components/ExerciseSummaryComponent";

function App() {
  return (
    <div className="container">
      <BrowserRouter>
        <NavBarComponent />
        <Routes>
          <Route index element={<HomeComponent/>}/>
          <Route path="/home" element={<HomeComponent/>}/>
          <Route path="/add_exercise" element={<AddExerciseComponent/>}/>
          <Route path="/set_goal" element={<SetGoalComponent/>}/>
          <Route path="/exercise_summary" element={<ExerciseSummaryComponent/>}/>
        </Routes>
      </BrowserRouter>
    </div>
  );
}

export default App;
