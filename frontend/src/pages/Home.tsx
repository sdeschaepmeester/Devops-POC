import React, { useEffect, useState } from 'react';
import { useNavigate } from 'react-router-dom';
import { jwtDecode } from "jwt-decode";
import Hospital from './Hospital';
import Login from './Login';

interface DecodedToken {
    exp: number;
}

const Home: React.FC = () => {
    const navigate = useNavigate();
    const [isAuthenticated, setIsAuthenticated] = useState(false);

    useEffect(() => {
        const token = localStorage.getItem('token');

        if (token) {
            try {
                const decodedToken: DecodedToken = jwtDecode<DecodedToken>(token);
                const currentTime = Date.now() / 1000;

                if (decodedToken.exp < currentTime) {
                    localStorage.removeItem('token');
                    setIsAuthenticated(false);
                    navigate('/connexion');
                } else {
                    setIsAuthenticated(true);
                    navigate('/dashboard');
                }
            } catch (error) {
                localStorage.removeItem('token');
                setIsAuthenticated(false);
                navigate('/connexion');
            }
        } else {
            setIsAuthenticated(false);
            navigate('/connexion');
        }
    }, [navigate]);

    return (
        <>
        {isAuthenticated ?
            <Hospital />
            :
            <Login />
        }
        </>
    );
};

export default Home;