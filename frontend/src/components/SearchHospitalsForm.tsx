import React, { useState, useEffect } from 'react';
import axios from 'axios';
import {
    Box,
    TextField,
    InputAdornment,
    IconButton,
    CircularProgress,
    List,
    ListItem,
    FormControl,
    InputLabel,
    Select,
    MenuItem,
    ListItemText,
    Button
} from '@mui/material';
import { SelectChangeEvent } from '@mui/material/Select';
import DeleteOutlineIcon from '@mui/icons-material/DeleteOutline';
import SpecialityModel from '../models/specialityModel';
import { getSpecialities } from '../api/specialitiesService';
import { getHospitalsNearby } from '../api/hospitalsService';

const SearchHospitalsForm: React.FC = () => {
    const [address, setAddress] = useState<string>('');
    const [suggestions, setSuggestions] = useState<string[]>([]);
    const [loading, setLoading] = useState<boolean>(false);
    const [specialities, setSpecialities] = useState<SpecialityModel[]>([]);
    const [selectedSpeciality, setSelectedSpeciality] = useState<SpecialityModel | null>(null);

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
        const selectedSpeciality = specialities.find(speciality =>
            speciality.speciality === value
        );
        if (selectedSpeciality) {
            setSelectedSpeciality(selectedSpeciality);
        }
    };

    const handleAddressChange = (event: React.ChangeEvent<HTMLInputElement>) => {
        const value = event.target.value;
        setAddress(value);

        if (value.length >= 5) {
            fetchAddressSuggestions(value);
        } else {
            setSuggestions([]);
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

            const results = response.data.map((result: any) => result.display_name);
            setSuggestions(results);
        } catch (error) {
            setSuggestions([]);
        } finally {
            setLoading(false);
        }
    };

    const handleSuggestionClick = (suggestion: string) => {
        console.log(suggestion);
        setAddress(suggestion);
        setSuggestions([]);
    };

    const clearAddress = () => {
        setAddress('');
        setSuggestions([]);
    };

    const searchHospitals = async () => {
        if (selectedSpeciality) {
            //const hospitalsNearby = await getHospitalsNearby(48.8630915, 2.31034709, selectedSpeciality?.speciality);
            const hospitalsNearby = await getHospitalsNearby(48.8630915, 2.31034709, "Dermatology");
            console.log('hospitals nearby')
            console.log(hospitalsNearby)
        }
    }

    return (
        <Box
            style={{
                display: 'flex',
                flexDirection: 'column',
                gap: '20px',
                width: '100%',
                maxWidth: '500px',
                padding: '20px',
                boxShadow: '0 4px 8px rgba(0, 0, 0, 0.1)',
                borderRadius: '8px'
            }}
        >
            <TextField
                label="Entrez votre adresse"
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
                <InputLabel id="specialty-label">Sélectionner une spécialité</InputLabel>
                <Select
                    labelId="specialty-label"
                    label="Sélectionner une spécialité"
                    value={selectedSpeciality?.speciality || ''}
                    onChange={handleSpecialityChange}
                >
                    {specialities.map((speciality) => (
                        <MenuItem
                            key={speciality.id}
                            value={speciality.speciality}
                        >
                            <ListItemText
                                primary={`${speciality.speciality_group} - ${speciality.speciality}`}
                            />
                        </MenuItem>
                    ))}
                </Select>
            </FormControl>
            <Button variant="contained" color="primary" onClick={() => searchHospitals()}>
                Rechercher
            </Button>
        </Box>
    );
};

export default SearchHospitalsForm;