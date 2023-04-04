import './App.css';
import React from 'react';
import { BrowserRouter } from 'react-router-dom';
import {Route, Routes} from "react-router";
import NavBarComponent from './components/NavBarComponent';
import HomeComponent from './components/HomeComponent';
import PageOneComponent from "./components/PageOneComponent";
import PageTwoComponent from "./components/PageTwoComponent";
import PageThreeComponent from "./components/PageThreeComponent";

function App() {
  return (
    <div className="container">
      <BrowserRouter>
        <NavBarComponent />
        <Routes>
          <Route index element={<HomeComponent/>}/>
          <Route path="/home" element={<HomeComponent/>}/>
          <Route path="/page_one" element={<PageOneComponent/>}/>
          <Route path="/page_two" element={<PageTwoComponent/>}/>
          <Route path="/page_three" element={<PageThreeComponent/>}/>
        </Routes>
      </BrowserRouter>
    </div>
  );
}

export default App;
