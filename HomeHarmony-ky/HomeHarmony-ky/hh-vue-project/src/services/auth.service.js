import axios from 'axios';

const AUTH_API_URL = 'http://localhost:8080/auth';

class AuthService {
    async login(username, password) {
        const response = await axios.post(`${AUTH_API_URL}/login`, {
            username,
            password
        });
        if (response.data && response.data.id) {
            this.setUserData(response.data);
        }
        return response.data;
    }

    async logout() {
        try {
            await axios.post(`${AUTH_API_URL}/logout`);
            this.clearUserData();
        } catch (error) {
            console.error('Logout error:', error);
            // Still clear data even if server request fails
            this.clearUserData();
            throw error;
        }
    }

    setUserData(user) {
        localStorage.setItem('userId', user.id);
        localStorage.setItem('username', user.username);
        localStorage.setItem('email', user.email);
    }

    clearUserData() {
        localStorage.clear();
    }

    getCurrentUser() {
        return {
            id: localStorage.getItem('userId'),
            username: localStorage.getItem('username'),
            email: localStorage.getItem('email')
        };
    }

    isLoggedIn() {
        return !!localStorage.getItem('userId');
    }
}

export default new AuthService(); 