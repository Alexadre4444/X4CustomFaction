import AppLayout from '@/layout/AppLayout.vue';
import { createRouter, createWebHistory } from 'vue-router';

const router = createRouter({
    history: createWebHistory(),
    routes: [
        {
            path: '/',
            component: AppLayout,
            children: [
                {
                    path: '/',
                    name: 'home',
                    component: () => import('@/views/pages/home/Home.vue')
                },
                {
                    path: '/turrets',
                    name: 'turrets',
                    component: () => import('@/views/pages/turret/Turrets.vue')
                },
                {
                    path: '/turrets/:id',
                    component: () => import('@/views/pages/turret/TurretUpdate.vue')
                }
            ]
        }
    ]
});

export default router;
