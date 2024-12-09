<template>
    <app-layout>
        <div class="chore-container">
            <!-- Create button -->
            <div class="header-actions">
                <el-button type="primary" @click="dialogVisible = true">Create New Chore</el-button>
            </div>

            <!-- Chores table -->
            <el-table :data="sortedTableData" border :default-sort="{prop: 'functionalSpaceType', order: 'ascending'}">
                <el-table-column prop="functionalSpaceType" label="Functional Space Type" width="180" sortable></el-table-column>
                <el-table-column prop="choreName" label="Chore Name" width="200"></el-table-column>
                <el-table-column prop="points" label="Points" width="180"></el-table-column>
                <el-table-column prop="dueDate" label="Due Date" width="180"></el-table-column>
                <el-table-column prop="choreStatus" label="Status" width="120">
                    <template slot-scope="scope">
                        <el-tag :type="getStatusType(scope.row.choreStatus)">
                            {{ scope.row.choreStatus }}
                        </el-tag>
                    </template>
                </el-table-column>
                <el-table-column label="Assigned To" width="180">
                    <template #default="scope">
                        <template v-if="scope.row.choreStatus === 'PENDING' || scope.row.showSelect">
                            <el-select
                                v-model="scope.row.assignedUserId"
                                placeholder="Assign user"
                                :loading="loadingUsers"
                                filterable
                                @change="(value) => {
                                    assignUser(scope.row.id, value);
                                    scope.row.showSelect = false;
                                }">
                                <el-option
                                    v-for="user in cachedSpaceUsers"
                                    :key="user.id"
                                    :label="user.username"
                                    :value="user.id">
                                </el-option>
                            </el-select>
                        </template>
                        <template v-else>
                            <div>
                                {{ scope.row.assignedUser ? scope.row.assignedUser.username : '' }}
                                <el-button 
                                    type="text"
                                    size="mini" 
                                    @click="handleChangeClick(scope.row)" 
                                    v-if="scope.row.choreStatus === 'IN_PROGRESS'">
                                    Change
                                </el-button>
                            </div>
                        </template>
                    </template>
                </el-table-column>
                <el-table-column label="Complete" width="120">
                    <template slot-scope="scope">
                        <el-button 
                            :type="scope.row.choreStatus === 'COMPLETED' ? 'success' : 'primary'"
                            size="mini"
                            :disabled="scope.row.choreStatus === 'COMPLETED'"
                            @click="markAsCompleted(scope.row)">
                            {{ scope.row.choreStatus === 'COMPLETED' ? 'Completed' : 'Mark Complete' }}
                        </el-button>
                    </template>
                </el-table-column>
                <el-table-column label="Options">
                    <template slot-scope="scope">
                        <el-button type="primary" size="mini" @click="editChore(scope.row)">Edit</el-button>
                        <el-button type="danger" size="mini" @click="deleteChore(scope.row.id)">Delete</el-button>
                    </template>
                </el-table-column>
            </el-table>

            <!-- Create/Edit Dialog -->
            <el-dialog :title="isEdit ? 'Edit Chore' : 'Create New Chore'" :visible.sync="dialogVisible" width="30%">
                <el-form :model="choreForm" label-width="120px">
                    <el-form-item label="Chore Name">
                        <el-input v-model="choreForm.choreName" placeholder="Enter chore name"></el-input>
                    </el-form-item>
                    <el-form-item label="Points">
                        <el-input-number v-model="choreForm.points" :min="0"></el-input-number>
                    </el-form-item>
                    <el-form-item label="Due Date">
                        <el-date-picker
                            v-model="choreForm.dueDate"
                            type="datetime"
                            placeholder="Select due date"
                            value-format="yyyy-MM-dd HH:mm:ss">
                        </el-date-picker>
                    </el-form-item>
                </el-form>
                <span slot="footer" class="dialog-footer">
                    <el-button @click="dialogVisible = false">Cancel</el-button>
                    <el-button type="primary" @click="submitChore">{{ isEdit ? 'Update' : 'Create' }}</el-button>
                </span>
            </el-dialog>
        </div>
    </app-layout>
</template>

<script>
import AppLayout from '@/components/layout/AppLayout.vue'
import axios from 'axios'

export default {
    components: {
        AppLayout
    },
    data() {
        return {
            tableData: [],
            dialogVisible: false,
            isEdit: false,
            choreForm: {
                id: null,
                choreName: '',
                points: 0,
                dueDate: '',
                choreStatus: 'PENDING'
            },
            loadingUsers: false,
            userCache: null,
            lastFetchTime: null,
            resizeObserver: null
        }
    },
    mounted() {
        this.fetchChores()
        this.fetchSpaceUsers()
        
        // Debounced resize observer
        this.setupResizeObserver()
    },
    beforeDestroy() {
        // Cleanup resize observer
        if (this.resizeObserver) {
            this.resizeObserver.disconnect()
        }
    },
    methods: {
        setupResizeObserver() {
            let timeout
            this.resizeObserver = new ResizeObserver((entries) => {
                // Debounce the resize callback
                clearTimeout(timeout)
                timeout = setTimeout(() => {
                    entries.forEach(entry => {
                        // Handle resize if needed
                        if (entry.target.classList.contains('el-table__body-wrapper')) {
                            this.$nextTick(() => {
                                const table = this.$el.querySelector('.el-table')
                                if (table && table.__vue__) {
                                    table.__vue__.doLayout()
                                }
                            })
                        }
                    })
                }, 16) // 60fps throttle
            })

            // Observe the table wrapper
            this.$nextTick(() => {
                const tableWrapper = this.$el.querySelector('.el-table__body-wrapper')
                if (tableWrapper) {
                    this.resizeObserver.observe(tableWrapper)
                }
            })
        },
        getStatusType(status) {
            switch (status) {
                case 'PENDING': return 'warning'
                case 'IN_PROGRESS': return 'primary'
                case 'COMPLETED': return 'success'
                default: return 'info'
            }
        },
        editChore(row) {
            this.isEdit = true
            this.choreForm = { ...row }
            this.dialogVisible = true
        },
        deleteChore(id) {
            this.$confirm('Are you sure you want to delete this chore?', 'Warning', {
                confirmButtonText: 'Delete',
                cancelButtonText: 'Cancel',
                type: 'warning'
            }).then(() => {
                axios.delete(`http://localhost:8080/chores/${id}`)
                    .then(() => {
                        this.$message.success('Chore deleted successfully')
                        this.fetchChores()
                    })
                    .catch(error => {
                        console.error('Error deleting chore:', error)
                        this.$message.error('Failed to delete chore')
                    })
            })
        },
        submitChore() {
            const url = this.isEdit 
                ? `http://localhost:8080/chores/${this.choreForm.id}`
                : 'http://localhost:8080/chores'
            const method = this.isEdit ? 'put' : 'post'

            axios[method](url, this.choreForm)
                .then(() => {
                    this.$message.success(this.isEdit ? 'Chore updated successfully' : 'Chore created successfully')
                    this.dialogVisible = false
                    this.resetForm()
                    this.fetchChores()
                })
                .catch(error => {
                    console.error('Error saving chore:', error)
                    this.$message.error('Failed to save chore')
                })
        },
        resetForm() {
            this.isEdit = false
            this.choreForm = {
                id: null,
                choreName: '',
                points: 0,
                dueDate: '',
                choreStatus: 'PENDING'
            }
        },
        fetchChores() {
            axios.get('http://localhost:8080/chores')
                .then(response => {
                    if (response.data && response.data.status === 'success') {
                        this.tableData = response.data.data.map(chore => ({
                            ...chore,
                            showSelect: false,
                            functionalSpaceType: this.formatSpaceType(chore.functionalSpaceType)
                        }));
                    } else {
                        this.$message.error('Failed to fetch chores: Invalid response format');
                    }
                })
                .catch(error => {
                    console.error('Error fetching chores:', error);
                    this.$message.error('Failed to fetch chores');
                });
        },
        async fetchSpaceUsers() {
            // Don't fetch if we have recent data (cache for 5 minutes)
            const CACHE_DURATION = 5 * 60 * 1000; // 5 minutes in milliseconds
            if (this.userCache && this.lastFetchTime && 
                (Date.now() - this.lastFetchTime < CACHE_DURATION)) {
                return;
            }

            this.loadingUsers = true;
            const spaceId = localStorage.getItem('spaceId');
            if (!spaceId) {
                this.loadingUsers = false;
                return;
            }
            
            try {
                const response = await axios.get(`http://localhost:8080/space/${spaceId}`);
                if (response.data && response.data.users) {
                    this.userCache = response.data.users;
                    this.lastFetchTime = Date.now();
                }
            } catch (error) {
                console.error('Error fetching space users:', error);
                this.$message.error('Failed to fetch space users');
            } finally {
                this.loadingUsers = false;
            }
        },
        assignUser(choreId, userId) {
            axios.put(`http://localhost:8080/chores/${choreId}/assign/${userId}`)
                .then(response => {
                    if (response.data.status === 'success') {
                        const assignedUser = this.cachedSpaceUsers.find(user => user.id === userId);
                        const chore = this.tableData.find(c => c.id === choreId);
                        if (chore) {
                            chore.assignedUser = assignedUser;
                            chore.choreStatus = 'IN_PROGRESS';
                        }
                        this.$message.success('User assigned successfully');
                    } else {
                        this.$message.error('Failed to assign user');
                    }
                })
                .catch(error => {
                    console.error('Error assigning user:', error);
                    this.$message.error('Failed to assign user');
                });
        },
        markAsCompleted(chore) {
            axios.put(`http://localhost:8080/chores/${chore.id}/complete`)
                .then(response => {
                    if (response.data.status === 'success') {
                        const updatedChore = this.tableData.find(c => c.id === chore.id);
                        if (updatedChore) {
                            updatedChore.choreStatus = 'COMPLETED';
                        }
                        this.$message.success('Chore marked as completed');
                    } else {
                        this.$message.error('Failed to complete chore');
                    }
                })
                .catch(error => {
                    console.error('Error completing chore:', error);
                    this.$message.error('Failed to complete chore');
                });
        },
        async handleChangeClick(row) {
            await this.fetchSpaceUsers();  // Ensure users are fetched
            row.showSelect = true;  // Show the select dropdown
        },
        formatSpaceType(type) {
            if (!type) return '';
            return type.toLowerCase()
                .split('_')
                .map(word => word.charAt(0).toUpperCase() + word.slice(1))
                .join(' ');
        }
    },
    computed: {
        assignedUserId: {
            get() {
                return this.assignedUser ? this.assignedUser.id : null;
            },
            set(value) {
                this.assignUser(this.id, value);
            }
        },
        sortedTableData() {
            // First, separate completed and non-completed chores
            const completedChores = this.tableData.filter(chore => chore.choreStatus === 'COMPLETED');
            const activeChores = this.tableData.filter(chore => chore.choreStatus !== 'COMPLETED');

            // Sort each group by functional space type
            const sortBySpaceType = (a, b) => {
                return a.functionalSpaceType.localeCompare(b.functionalSpaceType);
            };

            // Sort each group independently
            const sortedActiveChores = [...activeChores].sort(sortBySpaceType);
            const sortedCompletedChores = [...completedChores].sort(sortBySpaceType);

            // Return active chores on top, completed chores at bottom
            return [...sortedActiveChores, ...sortedCompletedChores];
        },
        cachedSpaceUsers() {
            return this.userCache || [];
        }
    }
}
</script>

<style>
.chore-container {
    padding: 20px;
    position: relative;
    max-width: 100%;
}

:deep(.el-table) {
    max-width: 100%;
}

:deep(.el-table__body-wrapper) {
    overflow-x: auto !important;
}

:deep(.el-table__header-wrapper) {
    overflow-x: hidden !important;
}

.header-actions {
    margin-bottom: 20px;
    display: flex;
    justify-content: flex-end;
}

.dialog-footer {
    display: flex;
    justify-content: flex-end;
    gap: 10px;
}

</style>