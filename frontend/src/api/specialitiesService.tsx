import axios from 'axios';
import SpecialityModel from '../models/specialityModel';

const BASE_URL = process.env.REACT_APP_BASE_URL;

// Get all specialities
export const getSpecialities = async (): Promise<SpecialityModel[]> => {
    try {
        const response = await axios.get<SpecialityModel[]>(`${BASE_URL}/Specialities`, {
            headers: {
                'Content-Type': 'application/json',
                'Access-Control-Allow-Origin': '*',
            }
        });
        return response.data;
    } catch (error) {
        throw 'Failed to fetch specialities: ' + error;
    }
};
