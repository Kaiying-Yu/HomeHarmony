<template>
    <app-layout>
        <div class="user-dashboard">
            <div class="dashboard-header">
                <h2>Space Members</h2>
            </div>
            
            <el-row :gutter="20">
                <el-col :span="8" v-for="user in tableData" :key="user.id">
                    <el-card class="member-card" shadow="hover">
                        <div class="member-header">
                            <div class="member-info">
                                <h3>{{ user.username }}</h3>
                                <el-tag size="small" type="success">{{ user.points }} Points</el-tag>
                            </div>
                        </div>
                        
                        <div class="member-chores">
                            <div class="section-title">
                                <span>Assigned Chores</span>
                            </div>
                            
                            <el-table
                                v-if="userChores[user.id] && userChores[user.id].length"
                                :data="userChores[user.id]"
                                style="width: 100%">
                                <el-table-column
                                    prop="choreName"
                                    label="Chore Name">
                                </el-table-column>
                                <el-table-column
                                    prop="functionalSpaceType"
                                    label="Functional Space">
                                </el-table-column>
                            </el-table>
                            <div v-else class="no-chores">
                                <i class="el-icon-info"></i>
                                <span>No chores assigned</span>
                            </div>
                        </div>
                    </el-card>
                </el-col>
            </el-row>
        </div>
    </app-layout>
</template>

<script>
import AppLayout from '@/components/layout/AppLayout.vue';
import axios from 'axios';

export default {
    components: {
        AppLayout
    },
    data() {
        return {
            tableData: [],
            userChores: {},
            loadingUsers: false
        }
    },
    methods: {
        async fetchUsers() {
            this.loadingUsers = true;
            const spaceId = localStorage.getItem('spaceId');
            if (!spaceId) {
                this.loadingUsers = false;
                return;
            }
            
            try {
                const response = await axios.get(`http://localhost:8080/space/${spaceId}`);
                if (response.data && response.data.users) {
                    this.tableData = response.data.users;
                    this.tableData.forEach(user => {
                        this.fetchUserChores(user.id);
                    });
                }
            } catch (error) {
                console.error('Error fetching space users:', error);
                this.$message.error('Failed to fetch users');
            } finally {
                this.loadingUsers = false;
            }
        },
        fetchUserChores(userId) {
            axios.get(`http://localhost:8080/chores/user/${userId}`)
                .then((response) => {
                    if (response.data.status === 'success') {
                        this.$set(this.userChores, userId, response.data.data);
                    }
                })
                .catch((error) => {
                    console.error('Error fetching user chores:', error);
                });
        }
    },
    mounted() {
        this.fetchUsers();
    }
}
</script>

<style scoped>
.user-container {
    padding: 20px;
}
.user-card {
    margin-bottom: 20px;
}
.card-header {
    display: flex;
    justify-content: space-between;
    align-items: center;
}
.chores-list {
    margin-top: 10px;
}
.no-chores {
    color: #999;
    text-align: center;
    padding: 20px;
}

:deep(.el-table) {
    font-size: 14px;
}

:deep(.el-table th) {
    background-color: white;
    color: #909399;
    font-weight: normal;
    padding: 8px;
}

:deep(.el-table td) {
    padding: 8px;
}

:deep(.el-table::before) {
    height: 0;
}

.member-chores {
    margin-top: 15px;
}

.section-title {
    margin-bottom: 15px;
    color: #5e3a1a;
    font-weight: bold;
}
</style>