import Vue from 'vue'
import VueRouter from 'vue-router'

Vue.use(VueRouter)

const router = new VueRouter({
    routes: [
        {
            path: '/',
            redirect: '/login'
        },
        {
            path: '/login',
            name: 'login',
            component: () => import('../views/auth/AuthView.vue')
        },
        {
            path: '/space',
            name: 'space',
            component: () => import('../views/homeharmony/SpaceView.vue'),
            meta: { requiresAuth: true }
        },
        {
            path: '/chore',
            name: 'chore',
            component: () => import('../views/homeharmony/ChoreView.vue'),
            meta: { requiresAuth: true }
        },
        {
            path: '/user',
            name: 'user',
            component: () => import('../views/homeharmony/UserView.vue'),
            meta: { requiresAuth: true }
        }
    ]
});

router.beforeEach((to, from, next) => {
    const isAuthenticated = !!localStorage.getItem('userId');
    
    if (to.matched.some(record => record.meta.requiresAuth)) {
        if (!isAuthenticated) {
            next('/login');
        } else {
            next();
        }
    } else if (to.path === '/login' && isAuthenticated) {
        next('/space');
    } else {
        next();
    }
});

export default router;
