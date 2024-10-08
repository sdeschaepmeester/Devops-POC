import axios from 'axios';
import SpecialityModel from '../models/specialityModel';

const BASE_URL = process.env.REACT_APP_BASE_URL;

const getAuthToken = (): string | null => {
    return localStorage.getItem('token');
};

// Get all specialities
export const getSpecialities = async (): Promise<SpecialityModel[]> => {
    const token = getAuthToken();
    try {
        const response = await axios.get<SpecialityModel[]>(`${BASE_URL}/Specialities`, {
            headers: {
                'Content-Type': 'application/json',
                'Authorization': token ? `Bearer ${token}` : '',
            }
        });
        return response.data;
    } catch (error) {
        console.error('Failed to fetch specialities:', error);
        throw new Error('Failed to fetch specialities: ' + error);
    }
};
