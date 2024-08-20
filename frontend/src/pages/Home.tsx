import React from 'react';
import { Container } from '@mui/material';
import SearchHospitalsForm from '../components/SearchHospitalsForm';

const Home: React.FC = () => {
    return (
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
    );
};

export default Home;