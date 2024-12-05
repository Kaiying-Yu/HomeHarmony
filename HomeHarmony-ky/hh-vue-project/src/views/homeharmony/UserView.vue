<template>
    <app-layout>
        <el-table :data="tableData" border>
            <el-table-column prop="userName" label="User Name" width="200"></el-table-column>
            <el-table-column prop="points" label="Points" width="180"></el-table-column>
            <el-table-column prop="createTime" label="Create Time" width="180"></el-table-column>
            <el-table-column prop="updateTime" label="Update Time" width="180"></el-table-column>
            <el-table-column label="Options">
                <template #default="scope">
                    <el-button type="primary" size="mini" @click="editUser(scope.row)">Edit</el-button>
                    <el-button type="danger" size="mini" @click="deleteUser(scope.row.id)">Delete</el-button>
                </template>
            </el-table-column>
        </el-table>
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
            tableData: []
        }
    },
    methods: {
       // 编辑任务的方法，跳转到编辑页面或处理编辑逻辑
        editUser(user) {
            // 可以将用户引导至编辑页面或者展示编辑弹窗
            console.log('Edit user:', user);
            // 如果需要跳转到编辑页面，可以使用 this.$router.push
            this.$router.push({ name: 'EditUser', params: { id: user.id } });
        },
        // 删除任务的方法，发起 DELETE 请求
        deleteUser(userId) {
            this.$confirm('Are you sure you want to delete this user?', 'Warning', {
                confirmButtonText: 'Yes',
                cancelButtonText: 'No',
                type: 'warning'
            }).then(() => {
                axios.delete(`http://localhost:8080/user/${userId}`)
                    .then(() => {
                        this.$message({
                            type: 'success',
                            message: 'User deleted successfully!'
                        });
                        // 更新数据，重新获取 chore 列表
                        this.fetchUsers();
                    })
                    .catch(error => {
                        this.$message({
                            type: 'error',
                            message: 'Failed to delete the user.'
                        });
                        console.error('Error deleting user:', error);
                    });
            }).catch(() => {
                this.$message({
                    type: 'info',
                    message: 'Deletion canceled'
                });
            });
        },
        // 获取 chore 列表的方法
        fetchUsers() {
            axios.get("http://localhost:8080/user")
                .then((result) => {
                    console.log(result.data.data);
                    this.tableData = result.data.data;
                })
                .catch((error) => {
                    console.error('Error fetching users:', error);
                });
        }
            

    },
    mounted() {
        // axios.get("http://localhost:8080/chores").then((result) => {
        //     console.log(result.data.data);
        //     this.tableData = result.data.data;
        // });
        this.fetchUsers();
    }
    
}
</script>

<style>
.el-menu-item a {
    text-decoration: none;
}
/* Optional: if you want to remove the router-link completely and use the click handler */
.el-menu-item {
    cursor: pointer;
}
</style>