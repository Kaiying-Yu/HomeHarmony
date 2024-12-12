<template>
    <app-layout>
        <!-- Show create button if user has no space -->
        <div v-if="!currentSpace" class="empty-state">
            <el-empty description="You haven't joined any space yet">
                <el-button type="primary" @click="createSpace">Create New Space</el-button>
                <el-button type="info" @click="showJoinSpace">Join Existing Space</el-button>
            </el-empty>
        </div>

        <!-- Show space details if user has a space -->
        <div v-else class="space-dashboard">
            <!-- Space Header Card -->
            <el-card class="space-header">
                <div class="space-title">
                    <h2>
                        {{ currentSpace ? currentSpace.name : 'No Space Joined' }}
                        <span class="space-id" v-if="currentSpace">(ID: {{ currentSpace.id }})</span>
                    </h2>
                    <div v-if="currentSpace">
                        <el-button type="danger" @click="quitSpace">Quit Space</el-button>
                    </div>
                </div>
            </el-card>

            <!-- Cards Grid -->
            <el-row :gutter="20" class="card-grid">
                <!-- Team Members Card -->
                <el-col :span="24">
                    <el-card class="box-card">
                        <template #header>
                            <div class="card-header">
                                <span><i class="el-icon-user"></i> Team Members</span>
                            </div>
                        </template>
                        <div v-for="user in currentSpace.users" 
                             :key="user.id" 
                             class="user-item">
                            <div class="user-info">
                                <span>{{ user.username }}</span>
                                <span class="user-points">Points: {{ user.points }}</span>
                            </div>
                            <el-tag size="small" type="success">Active</el-tag>
                        </div>
                    </el-card>
                </el-col>
            </el-row>
        </div>

        <!-- Dialog for creating new space -->
        <el-dialog title="Create New Space" :visible.sync="dialogVisible" width="30%">
            <el-form :model="newSpace" label-width="120px">
                <el-form-item label="Space Name">
                    <el-input v-model="newSpace.name" placeholder="Enter space name"></el-input>
                </el-form-item>
            </el-form>
            
            <template #footer>
                <span class="dialog-footer">
                    <el-button @click="dialogVisible = false">Cancel</el-button>
                    <el-button type="primary" @click="submitNewSpace">Create</el-button>
                </span>
            </template>
        </el-dialog>

        <!-- Dialog for joining existing space -->
        <el-dialog title="Join Existing Space" :visible.sync="joinDialogVisible" width="30%">
            <el-form :model="joinSpace" label-width="120px">
                <el-form-item label="Space ID">
                    <el-input v-model="joinSpace.spaceId" placeholder="Enter space ID"></el-input>
                </el-form-item>
            </el-form>
            
            <template #footer>
                <span class="dialog-footer">
                    <el-button @click="joinDialogVisible = false">Cancel</el-button>
                    <el-button type="primary" @click="submitJoinSpace">Join</el-button>
                </span>
            </template>
        </el-dialog>
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
            currentSpace: null,
            dialogVisible: false,
            newSpace: {
                name: ''
            },
            joinDialogVisible: false,
            joinSpace: {
                spaceId: ''
            }
        }
    },
    methods: {
        createSpace() {
            this.dialogVisible = true;
        },
        submitNewSpace() {
            const userId = localStorage.getItem('userId');
            const username = localStorage.getItem('username');
            const email = localStorage.getItem('email');
            
            const spaceRequest = {
                name: this.newSpace.name,
                users: [{
                    id: parseInt(userId),
                    username: username,
                    email: email,
                    assignedChores: []
                }]
            };

            axios.post('http://localhost:8080/space', spaceRequest)
                .then((response) => {
                    this.$message.success('Space created successfully!');
                    this.dialogVisible = false;
                    this.newSpace.name = '';
                    localStorage.setItem('spaceId', response.data.id);
                    this.fetchCurrentSpace();
                })
                .catch(error => {
                    this.$message.error('Failed to create space: ' + error.response.data.message);
                    console.error('Error:', error);
                });
        },
        fetchCurrentSpace() {
            const spaceId = localStorage.getItem('spaceId');
            if (!spaceId) {
                return;
            }
            
            axios.get(`http://localhost:8080/space/${spaceId}`)
                .then(response => {
                    this.currentSpace = response.data;
                })
                .catch(error => {
                    console.error('Error fetching space:', error);
                    this.$message.error('Failed to fetch space details');
                    localStorage.removeItem('spaceId');
                });
        },
        showJoinSpace() {
            this.joinDialogVisible = true;
        },
        
        submitJoinSpace() {
            const userId = localStorage.getItem('userId');
            const spaceId = parseInt(this.joinSpace.spaceId);
            
            if (isNaN(spaceId)) {
                this.$message.error('Please enter a valid Space ID');
                return;
            }
            
            axios.post(`http://localhost:8080/space/${spaceId}/users/${userId}`)
                .then(() => {
                    this.$message.success('Successfully joined space!');
                    this.joinDialogVisible = false;
                    this.joinSpace.spaceId = '';
                    localStorage.setItem('spaceId', spaceId);
                    this.fetchCurrentSpace();
                })
                .catch(error => {
                    const errorMessage = error.response?.data || 'Failed to join space';
                    this.$message.error(errorMessage);
                    console.error('Error:', error);
                });
        },
        quitSpace() {
            const userId = localStorage.getItem('userId');
            const spaceId = this.currentSpace.id;
            
            console.log('Attempting to quit space with:', {
                userId,
                spaceId,
                currentSpace: this.currentSpace,
                localStorage: {
                    userId: localStorage.getItem('userId'),
                    spaceId: localStorage.getItem('spaceId')
                }
            });

            this.$confirm('Are you sure you want to quit this space?', 'Warning', {
                confirmButtonText: 'Yes',
                cancelButtonText: 'No',
                type: 'warning'
            }).then(() => {
                axios.delete(`http://localhost:8080/space/${spaceId}/users/${userId}`)
                    .then(() => {
                        this.$message.success('Successfully quit the space');
                        localStorage.removeItem('spaceId');
                        this.currentSpace = null;
                    })
                    .catch(error => {
                        console.error('Error quitting space:', {
                            error,
                            response: error.response?.data,
                            status: error.response?.status
                        });
                        const errorMessage = error.response?.data?.message 
                            || error.response?.data?.error 
                            || 'Failed to quit space';
                        this.$message.error(errorMessage);
                    });
            });
        }
    },
    mounted() {
        this.fetchCurrentSpace();
    },
    created() {
        const userId = localStorage.getItem('userId');
        if (!userId) {
            this.$message.error('User ID not found. Please login again.');
            this.$router.push('/login');
            return;
        }
        
        axios.get(`http://localhost:8080/user/${userId}`)
            .then(response => {
                if (response.data.status === 'success') {
                    const user = response.data.data;
                    if (user.spaceId) {
                        localStorage.setItem('spaceId', user.spaceId);
                        this.fetchCurrentSpace();
                    }
                }
            })
            .catch(error => {
                console.error('Error fetching user:', error);
                this.$message.error('Failed to fetch user information');
            });
    }
}
</script> 
<style scoped>
.space-dashboard {
    padding: 20px;
}

.space-header {
    margin-bottom: 20px;
}

.space-title {
    display: flex;
    justify-content: space-between;
    align-items: center;
}

.space-title h2 {
    margin: 0;
}

.card-grid {
    margin-top: 20px;
}

.box-card {
    margin-bottom: 20px;
    height: 100%;
}

.card-header {
    display: flex;
    justify-content: space-between;
    align-items: center;
}

.space-item, .user-item, .chore-item {
    padding: 10px;
    border-bottom: 1px solid #ebeef5;
    display: flex;
    justify-content: space-between;
    align-items: center;
}

.space-item:last-child, 
.user-item:last-child, 
.chore-item:last-child {
    border-bottom: none;
}

.el-tag {
    margin-left: 8px;
}

/* Add hover effect to items */
.space-item:hover, 
.user-item:hover, 
.chore-item:hover {
    background-color: #f5f7fa;
}

/* Make sure the cards have proper spacing */
.el-col {
    margin-bottom: 20px;
}

/* Add some padding to the card content */
.el-card__body {
    padding: 15px;
}

.empty-state {
    display: flex;
    justify-content: center;
    align-items: center;
    min-height: 400px;
}

.empty-state .el-button {
    margin: 0 8px;
}

.dialog-footer {
    display: flex;
    justify-content: flex-end;
    gap: 10px;
}

.card-header {
    display: flex;
    justify-content: space-between;
    align-items: center;
}

.functional-space-item, .user-item {
    display: flex;
    justify-content: space-between;
    align-items: center;
    padding: 10px 0;
    border-bottom: 1px solid #eee;
}

.functional-space-item:last-child, 
.user-item:last-child {
    border-bottom: none;
}

.box-card {
    margin-bottom: 20px;
}

.user-info {
    display: flex;
    flex-direction: column;
}
.user-points {
    font-size: 12px;
    color: #909399;
    margin-top: 4px;
}

.space-id {
    font-size: 14px;
    color: #909399;
    font-weight: normal;
    margin-left: 8px;
}
</style> 
