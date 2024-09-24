import React, { useState } from 'react';
import { Box, Button, Card, CardContent, Typography } from '@mui/material';
import HospitalModel from '../models/hospitalModel';
import { reserveHospitalBed } from '../api/hospitalsService';

interface HospitalsListProps {
    hospitals: HospitalModel[];
    goBack: () => void;
}

const HospitalsList: React.FC<HospitalsListProps> = ({ hospitals, goBack }) => {
    const [selectedHospital, setSelectedHospital] = useState<HospitalModel | null>(null);
    const [redirects, setRedirects] = useState<boolean>(false);
    const [loading, setLoading] = useState<boolean>(false);

    const selectHospital = (hospital: HospitalModel) => {
        setSelectedHospital(hospital);
    }

    const reserveBed = async () => {
        if (selectedHospital) {
            try {
                setLoading(true);
                const response = await reserveHospitalBed(selectedHospital.id);
                if (response) {
                    setRedirects(response);
                    setTimeout(() => {
                        goBack();
                        setLoading(false);
                    }, 1500);
                }
            } catch (error) {
                console.error('Failed to reserve a bed:', error);
            }
        }
    }

    return (
        <Box sx={{ display: 'flex', flexDirection: 'column', gap: 2 }}>
            <Typography variant="h6" component="h2" sx={{ mb: 2 }}>
                Liste des hôpitaux proches correspondants à votre recherche :
            </Typography>
            <Typography>
                {redirects &&
                    "Le lit a bien été réservé. Vous allez être redirigé vers la page d'accueil."
                }
            </Typography>
            {hospitals.length === 0 ? (
                <Typography>
                    Il n'y a pas d'hôpitaux disponibles correspondants à votre recherche...
                </Typography>
            ) : (
                <Card sx={{ backgroundColor: 'white', borderRadius: '8px', boxShadow: 2, padding: 2 }}>
                    <Box sx={{ display: 'flex', flexWrap: 'wrap', gap: 2 }}>
                        {hospitals.map((hospital) => (
                            <Card
                                key={hospital.id}
                                sx={{
                                    boxShadow: 2,
                                    borderRadius: '8px',
                                    flex: '1 1 calc(25% - 16px)',
                                    backgroundColor: selectedHospital?.id === hospital.id ? 'rgba(0, 0, 0, 0.1)' : 'transparent',
                                    '&:hover': {
                                        backgroundColor: 'rgba(0, 0, 0, 0.1)',
                                    },
                                    cursor: 'pointer',
                                }}
                                onClick={() => selectHospital(hospital)}
                            >
                                <CardContent>
                                    <Typography variant="h6" component="div">
                                        {hospital.name}
                                    </Typography>
                                    <Typography color="text.secondary">
                                        Lits disponibles : {hospital.available_beds}
                                    </Typography>
                                    <Typography color="text.secondary">
                                        Coordonnées : {hospital.latitude}, {hospital.longitude}
                                    </Typography>
                                </CardContent>
                            </Card>

                        ))}
                    </Box>
                    <Box sx={{ display: 'flex', justifyContent: 'center', mt: 3 }}>
                        <Button
                            variant="contained"
                            color="primary"
                            onClick={() => reserveBed()}
                            disabled={!selectedHospital || redirects || loading}
                        >
                            {loading ?
                                "Chargement..."
                                :
                                "Réserver un lit"
                            }
                        </Button>
                    </Box>
                </Card>
            )}
        </Box>
    );
};


export default HospitalsList;