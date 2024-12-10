<template>
    <app-layout>
        <div class="user-dashboard">
            <div class="dashboard-header">
                <h2>Space Members</h2>
            </div>
            
            <el-row :gutter="20">
                <el-col :span="8" v-for="user in sortedUsers" :key="user.id">
                    <el-card :class="['member-card', { 'champion-card': isChampion(user) }]" shadow="hover">
                        <div class="member-header">
                            <div class="member-info">
                                <div class="username-container">
                                    <h3>{{ user.username }}</h3>
                                    <i v-if="isChampion(user)" class="el-icon-trophy champion-icon"></i>
                                </div>
                                <el-tag size="small" :type="isChampion(user) ? 'warning' : 'success'">
                                    {{ user.points }} Points
                                </el-tag>
                            </div>
                        </div>
                        
                        <div class="member-chores">
                            <div class="section-title">
                                <span>Assigned Chores</span>
                            </div>
                            
                            <el-table
                                v-if="userChores[user.id] && userChores[user.id].length"
                                :data="getSortedChores(userChores[user.id])"
                                style="width: 100%">
                                <el-table-column
                                    prop="choreName"
                                    label="Chore Name">
                                </el-table-column>
                                <el-table-column
                                    prop="functionalSpaceType"
                                    label="Space Type">
                                </el-table-column>
                                <el-table-column
                                    prop="points"
                                    label="Points"
                                    width="80">
                                    <template slot-scope="scope">
                                        <el-tag 
                                            size="mini" 
                                            :type="scope.row.choreStatus === 'COMPLETED' ? 'success' : 'info'">
                                            {{ scope.row.points }}
                                        </el-tag>
                                    </template>
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
    computed: {
        sortedUsers() {
            return [...this.tableData].sort((a, b) => b.points - a.points);
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
        },
        isChampion(user) {
            if (!this.tableData.length) return false;
            const maxPoints = Math.max(...this.tableData.map(u => u.points));
            return user.points === maxPoints && user.points > 0;
        },
        getSortedChores(chores) {
            if (!chores) return [];
            
            // Separate completed and active chores
            const completedChores = chores.filter(chore => chore.choreStatus === 'COMPLETED');
            const activeChores = chores.filter(chore => chore.choreStatus !== 'COMPLETED');
            
            // Sort each group by functional space type
            const sortBySpaceType = (a, b) => {
                return a.functionalSpaceType.localeCompare(b.functionalSpaceType);
            };
            
            // Sort and combine
            return [
                ...activeChores.sort(sortBySpaceType),
                ...completedChores.sort(sortBySpaceType)
            ];
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

.champion-card {
    border: 2px solid #ffd700;
    box-shadow: 0 0 15px rgba(255, 215, 0, 0.2) !important;
}

.username-container {
    display: flex;
    align-items: center;
    gap: 8px;
}

.champion-icon {
    color: #ffd700;
    font-size: 20px;
    animation: shine 2s infinite;
}

@keyframes shine {
    0% { opacity: 0.6; }
    50% { opacity: 1; }
    100% { opacity: 0.6; }
}
</style>