import React from 'react'
import {useLocation} from "react-router";

const NavBarComponent = () => {
  const {pathname} = useLocation();
  const paths = pathname.split('/')
  const active = paths[1];
  return (
      <div>
        <nav className="navbar navbar-expand-lg navbar-dark bg-primary">
          <div className="container-fluid">
            <a className="navbar-brand" href="home">Exercise Manager</a>
            <div className="collapse navbar-collapse" id="navbarColor01">
              <ul className="navbar-nav me-auto">
                <li className="nav-item">
                  <a className={`nav-link ${active === 'add_exercise' ? 'active'
                      : ''}`} href="add_exercise">Add Exercise</a>
                </li>
                <li className="nav-item">
                  <a className={`nav-link ${active === 'set_goal' ? 'active'
                      : ''}`} href="set_goal">Set Goal</a>
                </li>
                <li className="nav-item">
                  <a className={`nav-link ${active === 'exercise_summary' ? 'active'
                      : ''}`} href="exercise_summary">Exercise Summary</a>
                </li>
              </ul>
            </div>
          </div>
        </nav>
      </div>
  )
};

export default NavBarComponent;