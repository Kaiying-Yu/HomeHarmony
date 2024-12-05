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
                    <h2>{{ currentSpace.name }}</h2>
                    <el-tag type="success">{{ currentSpace.users.length }} Members</el-tag>
                </div>
            </el-card>

            <!-- Cards Grid -->
            <el-row :gutter="20" class="card-grid">
                <!-- Functional Spaces Card -->
                <el-col :span="8">
                    <el-card class="box-card">
                        <template #header>
                            <div class="card-header">
                                <span><i class="el-icon-office-building"></i> Functional Spaces</span>
                            </div>
                        </template>
                        <div v-for="space in currentSpace.functionalSpaces" 
                             :key="space.id" 
                             class="functional-space-item">
                            <span>{{ space.name }}</span>
                            <el-tag size="small" type="info">{{ space.choreTemplates.length }} templates</el-tag>
                        </div>
                    </el-card>
                </el-col>

                <!-- Team Members Card -->
                <el-col :span="8">
                    <el-card class="box-card">
                        <template #header>
                            <div class="card-header">
                                <span><i class="el-icon-user"></i> Team Members</span>
                            </div>
                        </template>
                        <div v-for="user in currentSpace.users" 
                             :key="user.id" 
                             class="user-item">
                            <span>{{ user.username }}</span>
                            <el-tag size="small" type="success">Active</el-tag>
                        </div>
                    </el-card>
                </el-col>

                <!-- Chores Card -->
                <el-col :span="8">
                    <el-card class="box-card">
                        <template #header>
                            <div class="card-header">
                                <span><i class="el-icon-tickets"></i> Active Chores</span>
                            </div>
                        </template>
                        <div v-for="chore in spaceChores" 
                             :key="chore.id" 
                             class="chore-item">
                            <span>{{ chore.name }}</span>
                            <el-tag size="small" :type="getChoreStatusType(chore.status)">
                                {{ chore.status }}
                            </el-tag>
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
                
                <el-form-item label="Default Spaces">
                    <el-checkbox-group v-model="newSpace.functionalSpaces">
                        <el-checkbox 
                            v-for="space in availableFunctionalSpaces" 
                            :key="space.id" 
                            :label="space.id">
                            {{ space.name }}
                        </el-checkbox>
                    </el-checkbox-group>
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
            spaceChores: [],
            dialogVisible: false,
            newSpace: {
                name: '',
                functionalSpaces: []
            },
            availableFunctionalSpaces: [],
            joinDialogVisible: false,
            joinSpace: {
                spaceId: ''
            }
        }
    },
    methods: {
        createSpace() {
            this.dialogVisible = true;
            this.fetchFunctionalSpaces();
        },
        fetchFunctionalSpaces() {
            axios.get('http://localhost:8080/functionalSpace')
                .then(response => {
                    this.availableFunctionalSpaces = response.data;
                })
                .catch(error => {
                    this.$message.error('Failed to fetch functional spaces');
                    console.error('Error:', error);
                });
        },
        submitNewSpace() {
            const userId = localStorage.getItem('userId');
            const username = localStorage.getItem('username');
            const email = localStorage.getItem('email');
            
            const spaceRequest = {
                name: this.newSpace.name,
                functionalSpaces: this.newSpace.functionalSpaces.map(id => ({
                    id: id
                })),
                users: [{
                    id: parseInt(userId),
                    username: username,
                    email: email,
                    assignedChores: []
                }],
                choreIds: []
            };

            axios.post('http://localhost:8080/space', spaceRequest)
                .then((response) => {
                    this.$message.success('Space created successfully!');
                    this.dialogVisible = false;
                    this.newSpace.name = '';
                    this.newSpace.functionalSpaces = [];
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
                return; // User hasn't joined any space yet
            }
            
            axios.get(`http://localhost:8080/space/${spaceId}`)
                .then(response => {
                    this.currentSpace = response.data;
                    if (this.currentSpace) {
                        this.fetchSpaceChores();
                    }
                })
                .catch(error => {
                    console.error('Error fetching space:', error);
                    this.$message.error('Failed to fetch space details');
                    localStorage.removeItem('spaceId'); // Clear invalid spaceId
                });
        },
        fetchSpaceChores() {
            if (this.currentSpace && this.currentSpace.choreIds) {
                axios.get(`http://localhost:8080/chores/space/${this.currentSpace.id}`)
                    .then(response => {
                        this.spaceChores = response.data;
                    })
                    .catch(error => {
                        console.error('Error fetching chores:', error);
                    });
            }
        },
        getChoreStatusType(status) {
            if (status === 'Pending') {
                return 'warning';
            } else if (status === 'In Progress') {
                return 'primary';
            } else if (status === 'Completed') {
                return 'success';
            }
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
            
            axios.post(`http://localhost:8080/space/join`, {
                spaceId: spaceId,
                userId: parseInt(userId)
            })
            .then(() => {
                this.$message.success('Successfully joined space!');
                this.joinDialogVisible = false;
                this.joinSpace.spaceId = '';
                localStorage.setItem('spaceId', spaceId);
                this.fetchCurrentSpace();
            })
            .catch(error => {
                this.$message.error('Failed to join space: ' + error.response.data);
                console.error('Error:', error);
            });
        }
    },
    mounted() {
        this.fetchCurrentSpace();
    },
    created() {
        const userId = localStorage.getItem('userId');
        console.log('UserId from localStorage:', userId);
        if (!userId) {
            this.$message.error('User ID not found. Please login again.');
            // Redirect to login page
            this.$router.push('/login');
        }
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

.functional-space-item, .user-item, .chore-item {
    display: flex;
    justify-content: space-between;
    align-items: center;
    padding: 10px 0;
    border-bottom: 1px solid #eee;
}

.functional-space-item:last-child, 
.user-item:last-child, 
.chore-item:last-child {
    border-bottom: none;
}

.box-card {
    margin-bottom: 20px;
}
</style> 
