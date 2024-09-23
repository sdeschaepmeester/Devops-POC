import React from 'react';
import { Typography, Modal, Box, IconButton } from '@mui/material';
import CloseIcon from '@mui/icons-material/Close';

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

interface InstructionsModalProps {
    open: boolean;
    onClose: () => void;
}

const InstructionsModal: React.FC<InstructionsModalProps> = ({ open, onClose }) => {

    return (

        <Modal open={open} onClose={onClose}>
            <Box
                sx={{
                    ...modalStyle,
                    border: 'none',
                    borderRadius: '10px',
                    boxShadow: 24,
                    width: '50%',
                }}
            >
                <div style={{ display: 'flex', justifyContent: 'space-between', alignItems: 'center' }}>
                    <Typography variant="h4" component="h2">
                        Instructions
                    </Typography>
                    <IconButton onClick={onClose} size="small">
                        <CloseIcon />
                    </IconButton>
                </div>
                <Typography sx={{ mt: 2 }}>
                    Pour effectuer une recherche d'hôpitaux, entrez l'adresse, cliquez sur "Trouver l'adresse" et sélectionnez une des suggestions.
                </Typography>
                <Typography sx={{ mt: 2 }}>
                    Ensuite, sélectionnez une spécialité parmi celles de la liste et appuyez sur "Rechercher".
                </Typography>
                <Typography sx={{ mt: 2 }}>
                    <strong>Exemple de valeurs :</strong>
                    <Box component="div" mt={1}>
                        - Adresse : 2 rue Louis Armand Eaubonne
                    </Box>
                    <Box component="div" mt={1}>
                        - Spécialité : Dermatology
                    </Box>
                </Typography>

            </Box>
        </Modal>
    );
};

export default InstructionsModal;