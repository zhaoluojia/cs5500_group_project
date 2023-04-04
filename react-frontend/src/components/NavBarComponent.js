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
                  <a className={`nav-link ${active === 'page_one' ? 'active'
                      : ''}`} href="page_one">Page 1</a>
                </li>
                <li className="nav-item">
                  <a className={`nav-link ${active === 'page_two' ? 'active'
                      : ''}`} href="page_two">Set Goal</a>
                </li>
                <li className="nav-item">
                  <a className={`nav-link ${active === 'page_three' ? 'active'
                      : ''}`} href="page_three">Page 3</a>
                </li>
              </ul>
            </div>
          </div>
        </nav>
      </div>
  )
};

export default NavBarComponent;