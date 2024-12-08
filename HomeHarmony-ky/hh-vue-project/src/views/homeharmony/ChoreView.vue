<template>
    <app-layout>
        <div class="chore-container">
            <!-- Create button -->
            <div class="header-actions">
                <el-button type="primary" @click="dialogVisible = true">Create New Chore</el-button>
            </div>

            <!-- Chores table -->
            <el-table :data="tableData" border>
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
            }
        }
    },
    methods: {
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
                    .then(response => {
                        if (response.data.status === 'success') {
                            this.$message.success('Chore deleted successfully');
                            this.fetchChores();
                        } else {
                            this.$message.error('Failed to delete chore');
                        }
                    })
                    .catch(error => {
                        console.error('Error deleting chore:', error);
                        this.$message.error('Failed to delete chore');
                    });
            });
        },
        submitChore() {
            const url = this.isEdit 
                ? `http://localhost:8080/chores/${this.choreForm.id}`
                : 'http://localhost:8080/chores';
            const method = this.isEdit ? 'put' : 'post';

            axios[method](url, this.choreForm)
                .then(response => {
                    if (response.data.status === 'success') {
                        this.$message.success(this.isEdit ? 'Chore updated successfully' : 'Chore created successfully');
                        this.dialogVisible = false;
                        this.resetForm();
                        this.fetchChores();
                    } else {
                        this.$message.error('Failed to save chore');
                    }
                })
                .catch(error => {
                    console.error('Error saving chore:', error);
                    this.$message.error('Failed to save chore');
                });
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
                    if (response.data.status === 'success') {
                        this.tableData = response.data.data;
                    } else {
                        this.$message.error('Failed to fetch chores');
                    }
                })
                .catch(error => {
                    console.error('Error fetching chores:', error);
                    this.$message.error('Failed to fetch chores');
                });
        }
    },
    mounted() {
        this.fetchChores()
    }
}
</script>

<style>
.chore-container {
    padding: 20px;
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