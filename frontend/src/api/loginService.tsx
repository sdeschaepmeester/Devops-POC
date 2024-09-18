import axios from 'axios';

const BASE_URL = process.env.REACT_APP_BASE_URL;

// Connection
export const login = async (username: string, password: string): Promise<any> => {
    try {
        const response = await axios.post<any>(`${BASE_URL}/Auth/Login`, {
            username,
            password
        });
        console.log('response')
        console.log(response)
        return response.data;
    } catch (error) {
        throw new Error('Failed to login: ' + error);
    }
};