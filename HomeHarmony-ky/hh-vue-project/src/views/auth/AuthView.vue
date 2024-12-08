<template>
  <div class="auth-container">
    <div class="auth-box">
      <h2>Login</h2>
      <form @submit.prevent="handleLogin">
        <div class="form-group">
          <label for="username">Username</label>
          <input
            id="username"
            v-model="loginForm.username"
            type="text"
            placeholder="Enter username"
            required
          >
        </div>
        <div class="form-group">
          <label for="password">Password</label>
          <input
            id="password"
            v-model="loginForm.password"
            type="password"
            placeholder="Enter password"
            required
          >
        </div>
        <button type="submit" class="login-button">Login</button>
      </form>
    </div>
  </div>
</template>

<script>
import axios from 'axios';

export default {
  name: 'AuthView',
  data() {
    return {
      loginForm: {
        username: '',
        password: ''
      }
    }
  },
  methods: {
    async handleLogin() {
      try {
        const response = await axios.post('http://localhost:8080/auth/login', {
          username: this.loginForm.username,
          password: this.loginForm.password
        });
        
        if (response.data && response.data.id) {
          localStorage.setItem('userId', response.data.id);
          localStorage.setItem('username', response.data.username);
          if (response.data.spaceId) {
            localStorage.setItem('spaceId', response.data.spaceId);
          }
          this.$router.push('/space');
          this.$message.success('Login successful');
        } else {
          this.$message.error('Invalid response from server');
        }
      } catch (error) {
        console.error('Login error:', error);
        this.$message.error(error.response?.data || 'Login failed');
      }
    }
  }
}
</script>

<style scoped>
.auth-container {
  display: flex;
  justify-content: center;
  align-items: center;
  min-height: 100vh;
  background-color: #f5f5f5;
}

.auth-box {
  background: white;
  padding: 2rem;
  border-radius: 8px;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
  width: 100%;
  max-width: 400px;
}

h2 {
  text-align: center;
  margin-bottom: 2rem;
  color: #333;
}

.form-group {
  margin-bottom: 1rem;
}

label {
  display: block;
  margin-bottom: 0.5rem;
  color: #666;
}

input {
  width: 100%;
  padding: 0.75rem;
  border: 1px solid #ddd;
  border-radius: 4px;
  font-size: 1rem;
}

.login-button {
  width: 100%;
  padding: 0.75rem;
  background-color: #1d80d1;
  color: white;
  border: none;
  border-radius: 4px;
  font-size: 1rem;
  cursor: pointer;
  margin-top: 1rem;
}

.login-button:hover {
  background-color: #588fe2;
}
</style>