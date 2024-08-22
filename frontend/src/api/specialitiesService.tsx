import axios from 'axios';
import SpecialityModel from '../models/specialityModel';

const BASE_URL = process.env.REACT_APP_BASE_URL;
const API_USERNAME = process.env.REACT_APP_API_USERNAME;
const API_PASSWORD = process.env.REACT_APP_API_PASSWORD;

const authHeader = 'Basic ' + btoa(`${API_USERNAME}:${API_PASSWORD}`);

// Get all specialities
export const getSpecialities = async (): Promise<SpecialityModel[]> => {
    try {
        const response = await axios.get<SpecialityModel[]>(`${BASE_URL}/Specialities`, {
            headers: {
                'Content-Type': 'application/json',
                'Authorization': authHeader,
            }
        });
        return response.data;
    } catch (error) {
        console.error('Failed to fetch specialities:', error);
        throw new Error('Failed to fetch specialities: ' + error);
    }
};
