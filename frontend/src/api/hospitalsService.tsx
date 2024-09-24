import axios from 'axios';
import HospitalModel from '../models/hospitalModel';

const BASE_URL = process.env.REACT_APP_BASE_URL;

// Get all hospitals
export const getAllHospitals = async (): Promise<HospitalModel[]> => {
    try {
        const response = await axios.get<HospitalModel[]>(`${BASE_URL}/Hospitals`);
        return response.data;
    } catch (error) {
        throw new Error('Failed to fetch hospitals: ' + (error as Error).message);
    }
};

// Get one specific hospital, by id 
export const getHospitalById = async (id: number): Promise<HospitalModel> => {
    try {
        const response = await axios.get<HospitalModel>(`${BASE_URL}/Hospitals/${id}`);
        return response.data;
    } catch (error) {
        throw new Error('Failed to fetch hospital by id: ' + (error as Error).message);
    }
};

// Get list of hospitals filtered by parameters
export const getHospitalsFiltered = async (params: { [key: string]: any }): Promise<HospitalModel[]> => {
    try {
        const response = await axios.get<HospitalModel[]>(`${BASE_URL}/Hospitals`, { params });
        return response.data;
    } catch (error) {
        throw new Error('Failed to fetch hospitals with parameters: ' + (error as Error).message);
    }
};

// Get hospitals nearby location with selected speciality
export const getHospitalsNearby = async (latitude: number, longitude: number, specialityId: string): Promise<HospitalModel[]> => {
    try {
        const token = getAuthToken();
        const response = await axios.get<HospitalModel[]>(`${BASE_URL}/Hospitals/nearby`, {
            params: {
                latitude,
                longitude,
                specialityId
            },
            headers: {
                'Content-Type': 'application/json',
                'Authorization': token ? `Bearer ${token}` : '',
            }
        });
        return response.data;
    } catch (error) {
        throw new Error('Failed to fetch hospitals nearby: ' + (error as Error).message);
    }
};

// Reserve an hospital bed
export const reserveHospitalBed = async (hospitalId: number): Promise<any> => {
    try {
        const token = getAuthToken();
        const response = await axios.post<any>(`${BASE_URL}/Hospitals/${hospitalId}/reserve`, null, {
            headers: {
                'Content-Type': 'application/x-www-form-urlencoded',
                'Authorization': token ? `Bearer ${token}` : '',
            }
        });
        return response.data;
    } catch (error) {
        throw new Error('Failed to reserve a bed: ' + (error as Error).message);
    }
};

const getAuthToken = (): string | null => {
    return localStorage.getItem('token');
};