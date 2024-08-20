import axios from 'axios';
import HospitalModel from '../models/hospitalModel';

const BASE_URL = process.env.REACT_APP_BASE_URL;

// Get all hospitals
export const getAllHospitals = async (): Promise<HospitalModel[]> => {
    try {
        const response = await axios.get<HospitalModel[]>(`${BASE_URL}/Hospitals`);
        return response.data;
    } catch (error) {
        throw 'Failed to fetch hospitals' + error;
    }
};

// Get one specific hospital, by id 
export const getHospitalById = async (id: number): Promise<HospitalModel> => {
    try {
        const response = await axios.get<HospitalModel>(`${BASE_URL}/Hospitals/${id}`);
        return response.data;
    } catch (error) {
        throw "Failed to fetch hospital with ID" + error;
    }
};

// Get list of hospitals filtered by parameters
export const getHospitalsFiltered = async (params: { [key: string]: any }): Promise<HospitalModel[]> => {
    try {
        const response = await axios.get<HospitalModel[]>(`${BASE_URL}/Hospitals`, { params });
        return response.data;
    } catch (error) {
        throw "Failed to fetch hospitals with parameters" + error;
    }
};
