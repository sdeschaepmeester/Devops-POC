import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import { AppBar, Toolbar, Typography, Button, Container, Modal, Box } from '@mui/material';
import SearchHospitalsForm from '../components/SearchHospitalsForm';
import logo from '../assets/logo.png';
import InstructionsModal from '../components/InstructionsModal';

const modalStyle = {
    position: 'absolute' as 'absolute',
    top: '50%',
    left: '50%',
    transform: 'translate(-50%, -50%)',
    width: 400,
    bgcolor: 'background.paper',
    border: '2px solid #000',
    boxShadow: 24,
    p: 4,
};

const Hospital: React.FC = () => {
    const navigate = useNavigate();
    const [openModal, setOpenModal] = useState(false);

    const handleOpen = () => setOpenModal(true);
    const handleClose = () => setOpenModal(false);

    const handleLogout = () => {
        localStorage.removeItem('token');
        setTimeout(() => {
            console.log('navigate')
            navigate('/connexion', { replace: true });
        }, 1000);
    };

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
                    bgcolor: '#2a6885', // Couleur de fond bleu
                    color: 'white', // Couleur du texte blanche
                    marginTop: '10px',
                    marginLeft: '10px',
                    borderRadius: '5px', // Bords arrondis
                    '&:hover': {
                        bgcolor: '#4b8dab'
                    },
                }}
                onClick={handleOpen}
            >
                Aide au fonctionnement
            </Button>


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

            <InstructionsModal open={openModal} onClose={handleClose} />
        </>
    );
};

export default Hospital;