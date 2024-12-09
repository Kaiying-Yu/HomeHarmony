<template>
    <div>
        <el-container style="height: 700px; border: 1px solid #eee">
            <el-header style="font-size:40px; background-color: rgb(238, 241, 246)">
                <div style="display: flex; justify-content: space-between; align-items: center">
                    <span>HomeHarmony</span>
                    <div class="user-welcome">
                        <span>Welcome, <span class="username">{{ username }}</span></span>
                    </div>
                </div>
            </el-header>
            <el-container>
                <el-aside width="220px" style="border: 1px solid #eee">
                    <el-menu :default-openeds="['1']">
                        <el-submenu index="1">
                            <template #title><i class="el-icon-message"></i>System Management</template>
                            <el-menu-item index="1-1" @click="$router.push('/space')">Spaces</el-menu-item>
                            <el-menu-item index="1-2" @click="$router.push('/chore')">Chores</el-menu-item>
                            <el-menu-item index="1-3" @click="$router.push('/user')">Users</el-menu-item>
                        </el-submenu>
                        <el-menu-item index="2" @click="handleLogout">
                            <i class="el-icon-switch-button"></i>
                            <span>Logout</span>
                        </el-menu-item>
                    </el-menu>
                </el-aside>
                <el-main>
                    <slot></slot>
                </el-main>
            </el-container>
        </el-container>
    </div>
</template>

<script>
import axios from 'axios';

export default {
    name: 'AppLayout',
    data() {
        return {
            username: localStorage.getItem('username') || 'Guest'
        }
    },
    methods: {
        async handleLogout() {
            try {
                await axios.post('http://localhost:8080/auth/logout');
                localStorage.clear();
                this.$message.success('Logged out successfully');
                this.$router.push('/login');
            } catch (error) {
                console.error('Logout error:', error);
                localStorage.clear();
                this.$message.warning('Logout completed with errors');
                this.$router.push('/login');
            }
        }
    }
}
</script>

<style scoped>
.user-welcome {
    font-size: 24px;
    color: #2c3e50;
    font-family: 'Helvetica Neue', Arial, sans-serif;
    letter-spacing: -0.5px;
}

.username {
    font-weight: 600;
    color: #2c3e50;
    margin-left: 4px;
}
</style> 