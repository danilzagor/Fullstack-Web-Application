import React, { useState } from 'react'
import { Navigate } from 'react-router-dom';

function PrivateRouter({children}) {
    const [jwt, setJwt] = useState("", "jwt");
  return jwt ? children: <Navigate to="/login"></Navigate>
}

export default PrivateRouter