import React, { useState } from 'react';
import { Box, Button, Card, CardContent, Typography } from '@mui/material';
import HospitalModel from '../models/hospitalModel';

interface HospitalsListProps {
    hospitals: HospitalModel[];
}

const HospitalsList: React.FC<HospitalsListProps> = ({ hospitals }) => {
    const [selectedHospital, setSelectedHospital] = useState<HospitalModel | null>(null);

    const selectHospital = (hospital: HospitalModel) => {
        setSelectedHospital(hospital);
    }

    const reserveBed = () => {
        if(selectedHospital){
            console.log('reserve un lit')
        }
    }

    return (
        <Box sx={{ display: 'flex', flexDirection: 'column', gap: 2 }}>
            <Typography variant="h6" component="h2" sx={{ mb: 2 }}>
                Liste des hôpitaux proches correspondants à votre recherche :
            </Typography>
            <Card sx={{ backgroundColor: 'white', borderRadius: '8px', boxShadow: 2, padding: 2 }}>
                <Box sx={{ display: 'flex', flexWrap: 'wrap', gap: 2 }}>
                    {hospitals.map((hospital) => (
                        <Card
                            key={hospital.id}
                            sx={{
                                boxShadow: 2,
                                borderRadius: '8px',
                                flex: '1 1 calc(25% - 16px)',
                                '&:hover': {
                                    backgroundColor: 'rgba(0, 0, 0, 0.1)',
                                },
                                cursor: 'pointer'
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
                        disabled={!selectedHospital}
                    >
                        Réserver un lit
                    </Button>
                </Box>
            </Card>
        </Box>
    );
};


export default HospitalsList;