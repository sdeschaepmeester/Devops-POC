import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import { AppBar, Toolbar, Typography, Button, Container, Modal, Box } from '@mui/material';
import SearchHospitalsForm from '../components/SearchHospitalsForm';
import logo from '../assets/logo.png';
import InstructionsModal from '../components/InstructionsModal';
import HospitalModel from '../models/hospitalModel';
import HospitalsList from '../components/HospitalsList';

const Hospital: React.FC = () => {
    const navigate = useNavigate();
    const [openModal, setOpenModal] = useState(false);
    const [hospitals, setHospitals] = useState<HospitalModel[]>([]);
    const [displayResults, setDisplayResults] = useState(false);

    const handleOpen = () => setOpenModal(true);
    const handleClose = () => setOpenModal(false);

    const handleLogout = () => {
        localStorage.removeItem('token');
        setTimeout(() => {
            navigate('/connexion', { replace: true });
        }, 1000);
    };

    const retrieveHospitals = (hospitals: HospitalModel[]) => {
        setHospitals(hospitals);
        setDisplayResults(true);
    };

    const goBack = () => {
        setDisplayResults(false);
    }

    return (
        <>
            <AppBar position="static">
                <Toolbar style={{ display: 'flex', justifyContent: 'space-between' }}>
                    <div style={{ display: 'flex', alignItems: 'center' }}>
                        <img
                            onClick={() => navigate('/')}
                            src={logo}
                            alt="Logo"
                            style={{ height: '40px', marginRight: '10px' }}
                        />
                        <Typography variant="h6">
                            Medhead - Recherche d'hôpitaux
                        </Typography>
                    </div>
                    <Button
                        color="inherit"
                        onClick={handleLogout}
                        style={{ marginLeft: 'auto' }}
                    >
                        Déconnexion
                    </Button>
                </Toolbar>
            </AppBar>

            <Button
                sx={{
                    bgcolor: '#2a6885',
                    color: 'white',
                    marginTop: '10px',
                    marginLeft: '10px',
                    borderRadius: '5px',
                    '&:hover': {
                        bgcolor: '#4b8dab'
                    },
                }}
                onClick={handleOpen}
            >
                Aide au fonctionnement
            </Button>
            {displayResults &&
                <Button
                    sx={{
                        bgcolor: '#2a6885',
                        color: 'white',
                        marginTop: '10px',
                        marginLeft: '10px',
                        borderRadius: '5px',
                        '&:hover': {
                            bgcolor: '#4b8dab'
                        },
                    }}
                    onClick={goBack}
                >
                    Revenir à la recherche
                </Button>
            }
            <Container
                style={{
                    height: '100vh',
                    display: 'flex',
                    justifyContent: 'center',
                    alignItems: 'center',
                }}
            >
                {displayResults ?
                    <HospitalsList hospitals={hospitals} goBack={goBack} />
                    :
                    <SearchHospitalsForm retrieveHospitals={retrieveHospitals} />
                }
            </Container>
            <InstructionsModal open={openModal} onClose={handleClose} />
        </>
    );
};

export default Hospital;