import React from 'react';
import './Component.css';

const HeaderComponent = () => {
  return (
    <div className="container p-3 mb-2 bg-light text-dark header-wrapper d-flex align-items-center justify-content-center">
      <div className="text-center">
        <h1>Exercise Manager</h1>
        <p>Shape your body, own your health.</p>
      </div>
    </div>
  )
};

export default HeaderComponent;