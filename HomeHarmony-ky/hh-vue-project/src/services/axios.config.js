import axios from 'axios';

// Create axios instance with default config
const axiosInstance = axios.create({
    baseURL: 'http://localhost:8080',
    timeout: 5000,
    headers: {
        'Content-Type': 'application/json'
    }
});

export default axiosInstance; 