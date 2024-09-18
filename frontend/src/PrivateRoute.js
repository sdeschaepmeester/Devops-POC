import React from 'react';
import { Route, Navigate } from 'react-router-dom';

const PrivateRoute = ({ element, ...rest }) => {
  const isAuthenticated = Boolean(localStorage.getItem('token')); 

  return (
    <Route 
      {...rest} 
      element={isAuthenticated ? element : <Navigate to="/connexion" />} 
    />
  );
};

export default PrivateRoute;