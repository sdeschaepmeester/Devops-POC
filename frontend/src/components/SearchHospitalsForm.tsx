import { useState, useEffect } from 'react';
import axios from 'axios';
import { Box, TextField, CircularProgress, List, ListItem, FormControl, InputLabel, Select, MenuItem, ListItemText, Button, IconButton, InputAdornment, SelectChangeEvent } from '@mui/material';
import DeleteOutlineIcon from '@mui/icons-material/DeleteOutline';
import { getSpecialities } from '../api/specialitiesService';
import { getHospitalsNearby } from '../api/hospitalsService';

interface SpecialityModel {
    id: number;
    speciality: string;
    speciality_group: string;
}

interface AddressSuggestion {
    display_name: string;
    lat: string;
    lon: string;
}

const SearchHospitalsForm: React.FC = () => {
    const [address, setAddress] = useState<string>('');
    const [suggestions, setSuggestions] = useState<AddressSuggestion[]>([]);
    const [loading, setLoading] = useState<boolean>(false);
    const [specialities, setSpecialities] = useState<SpecialityModel[]>([]);
    const [selectedSpeciality, setSelectedSpeciality] = useState<SpecialityModel | null>(null);
    const [selectedAddress, setSelectedAddress] = useState<AddressSuggestion | null>(null);

    useEffect(() => {
        const fetchSpecialities = async () => {
            try {
                const specialitiesData = await getSpecialities();
                setSpecialities(specialitiesData);
            } catch (error) {
                console.error('Failed to fetch specialities:', error);
            }
        };
        fetchSpecialities();
    }, []);

    const handleSpecialityChange = (event: SelectChangeEvent<string>) => {
        const value = event.target.value as string;
        const selectedSpeciality = specialities.find(speciality => speciality.speciality === value);
        if (selectedSpeciality) {
            setSelectedSpeciality(selectedSpeciality);
        }
    };

    const fetchAddressSuggestions = async (query: string) => {
        setLoading(true);
        try {
            const response = await axios.get('https://nominatim.openstreetmap.org/search', {
                params: {
                    q: query,
                    format: 'json',
                    addressdetails: 1,
                    countrycodes: 'fr',
                    limit: 5,
                },
            });

            const results = response.data.map((result: any) => ({
                display_name: result.display_name,
                lat: result.lat,
                lon: result.lon,
            }));

            setSuggestions(results);
        } catch (error) {
            setSuggestions([]);
        } finally {
            setLoading(false);
        }
    };

    const handleSuggestionClick = (suggestion: AddressSuggestion) => {
        setAddress(suggestion.display_name);
        setSelectedAddress(suggestion);
        setSuggestions([]);
    };

    const clearAddress = () => {
        setAddress('');
        setSelectedAddress(null);
        setSuggestions([]);
    };

    const searchHospitals = async () => {
        if (selectedSpeciality && selectedAddress) {
            const latitude = parseFloat(selectedAddress.lat);
            const longitude = parseFloat(selectedAddress.lon);
            const hospitalsNearby = await getHospitalsNearby(latitude, longitude, selectedSpeciality.speciality);
            console.log('Hospitals Nearby:', hospitalsNearby);
        } else {
            alert('Veuillez sélectionner une adresse valide et une choisir une spécialité.');
        }
    };

    return (
        <Box
            style={{
                display: 'flex',
                flexDirection: 'column',
                gap: '20px',
                width: '70%',
                maxWidth: '1000px',
                padding: '20px',
                boxShadow: '0 4px 8px rgba(0, 0, 0, 0.1)',
                borderRadius: '8px',
                margin: '0 auto',
                minHeight: '200px',
                justifyContent: 'center'
            }}
        >

            <TextField
                label="Entrez votre adresse"
                variant="outlined"
                fullWidth
                value={address}
                onChange={(e) => setAddress(e.target.value)}
                InputProps={{
                    endAdornment: (
                        <>
                            {address && (
                                <InputAdornment position="end">
                                    <IconButton onClick={clearAddress}>
                                        <DeleteOutlineIcon />
                                    </IconButton>
                                </InputAdornment>
                            )}
                            <InputAdornment position="end">
                                <Button onClick={() => fetchAddressSuggestions(address)}>Trouver l'adresse</Button>
                            </InputAdornment>
                        </>
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
                            {suggestion.display_name}
                        </ListItem>
                    ))}
                </List>
            )}
            <FormControl variant="outlined" fullWidth>
                <InputLabel id="specialty-label">Sélectionner une spécialité</InputLabel>
                <Select
                    labelId="specialty-label"
                    label="Sélectionner une spécialité"
                    value={selectedSpeciality?.speciality || ''}
                    onChange={handleSpecialityChange}
                >
                    {specialities.map((speciality) => (
                        <MenuItem key={speciality.id} value={speciality.speciality}>
                            <ListItemText primary={`${speciality.speciality_group} - ${speciality.speciality}`} />
                        </MenuItem>
                    ))}
                </Select>
            </FormControl>
            <Button variant="contained" color="primary" onClick={searchHospitals} disabled={!selectedAddress}>
                Rechercher
            </Button>
        </Box>
    );
};

export default SearchHospitalsForm;