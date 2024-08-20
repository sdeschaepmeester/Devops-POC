import React, { useState } from 'react';
import { Box, TextField, MenuItem, Select, FormControl, InputLabel, Button, List, ListItem, CircularProgress,
    InputAdornment, IconButton
 } from '@mui/material';
import axios from 'axios';
import DeleteOutlineIcon from '@mui/icons-material/DeleteOutline';

const SearchHospitalsForm: React.FC = () => {
    const [address, setAddress] = useState<string>('');
    const [suggestions, setSuggestions] = useState<string[]>([]);
    const [loading, setLoading] = useState<boolean>(false);

    // Detect if we need to call the Nominatim API to fetch corresponding address results
    const handleAddressChange = (event: React.ChangeEvent<HTMLInputElement>) => {
        const value = event.target.value;
        setAddress(value);

        if (value.length >= 5) {
            fetchAddressSuggestions(value);
        } else {
            setSuggestions([]);
        }
    };

    // Get corresponding UK addresses based on address field content
    const fetchAddressSuggestions = async (query: string) => {
        suggestions.length === 0 && setLoading(true);
        try {
            const response = await axios.get('https://nominatim.openstreetmap.org/search', {
                params: {
                    q: query,
                    format: 'json',
                    addressdetails: 1,
                    countrycodes: 'gb',
                    limit: 5,
                },
            });

            const results = response.data.map((result: any) => result.display_name);
            setSuggestions(results);
        } catch (error) {
            console.error('Error fetching address suggestions:', error);
            setSuggestions([]);
        } finally {
            setLoading(false);
        }
    };

    // Update address field as the user types in
    const handleSuggestionClick = (suggestion: string) => {
        setAddress(suggestion);
        setSuggestions([]);
    };

    // Delete address fields and suggestions associated
    const clearAddress = () => {
        setAddress('');
        setSuggestions([]);
    };

    return (
        <Box
            style={{
                display: 'flex',
                flexDirection: 'column',
                gap: '20px',
                width: '100%',
                maxWidth: '500px',  // Augmenter la largeur du formulaire
                padding: '20px',
                boxShadow: '0 4px 8px rgba(0, 0, 0, 0.1)',
                borderRadius: '8px'
            }}
        >
            <TextField
                label="Entrer votre adresse"
                variant="outlined"
                fullWidth
                value={address}
                onChange={handleAddressChange}
                InputProps={{
                    endAdornment: (
                        address && (
                            <InputAdornment position="end">
                                <IconButton onClick={clearAddress}>
                                    <DeleteOutlineIcon />
                                </IconButton>
                            </InputAdornment>
                        )
                    ),
                }}
            />
            {loading ? (
                <CircularProgress size={24} style={{ alignSelf: 'center' }} />
            ) : (
                <List
                    style={{
                        border: '1px solid #ddd',
                        borderRadius: '4px',
                        maxHeight: '150px',
                        overflowY: 'auto',
                        padding: '0',
                        marginTop: '10px',
                    }}
                >
                    {suggestions.map((suggestion, index) => (
                        <ListItem
                            key={index}
                            style={{
                                padding: '10px',
                                cursor: 'pointer',
                                borderBottom: '1px solid #ddd',
                            }}
                            onClick={() => handleSuggestionClick(suggestion)}
                            onMouseOver={(e) => (e.currentTarget.style.backgroundColor = '#f0f0f0')}
                            onMouseOut={(e) => (e.currentTarget.style.backgroundColor = 'white')}
                        >
                            {suggestion}
                        </ListItem>
                    ))}
                </List>
            )}
            <FormControl variant="outlined" fullWidth>
                <InputLabel id="specialty-label">Sélectionner les spécialités</InputLabel>
                <Select
                    labelId="specialty-label"
                    label="Sélectionner les spécialités"
                    defaultValue=""
                >
                    <MenuItem value="cardiology">Cardiologie</MenuItem>
                    <MenuItem value="dermatology">Dermatologie</MenuItem>
                    <MenuItem value="pediatrics">Pédiatrie</MenuItem>
                </Select>
            </FormControl>
            <Button variant="contained" color="primary">
                Rechercher
            </Button>
        </Box>
    );
};

export default SearchHospitalsForm;