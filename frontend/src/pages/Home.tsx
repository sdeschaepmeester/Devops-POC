import React from 'react';
import { Container, AppBar, Toolbar, Typography } from '@mui/material';
import SearchHospitalsForm from '../components/SearchHospitalsForm';
import logo from '../assets/logo.png';

const Home: React.FC = () => {
    return (
        <>
            <AppBar position="static">
                <Toolbar style={{ display: 'flex', justifyContent: 'space-between' }}>
                    <div style={{ display: 'flex', alignItems: 'center' }}>
                        <img
                            src={logo}
                            alt="Logo"
                            style={{ height: '40px', marginRight: '10px' }}
                        />
                        <Typography variant="h6">
                            Medhead - Recherche d'hôpitaux
                        </Typography>
                    </div>
                </Toolbar>
            </AppBar>

            <Container
                style={{
                    height: '100vh',
                    display: 'flex',
                    justifyContent: 'center',
                    alignItems: 'center',
                }}
            >
                <SearchHospitalsForm />
            </Container>
        </>
    );
};

export default Home;