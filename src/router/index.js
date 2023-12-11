import { createRouter, createWebHistory } from 'vue-router'
import HomeView from '../views/HomeView.vue'
import LoginView from '../views/LoginView.vue'
import DashboardView from '../views/DashboardView.vue'
import DemoVista from '../views/DemoVista.vue'
import DashboardTrabajador from '../views/DashboardTrabajador.vue'

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/login',
      name: 'login',
      component: LoginView
    },

    {
      path: '/demo',
      name: '/demo',
      component:DemoVista
    },

    {
      path: '/dashboard',
      name: 'dashboard',
      component: DashboardView
    },

    {
      path: '/DashboardTrabajador',
      name: 'DashboardTrabajador',
      component: DashboardTrabajador
    },
    
    {
      path: '/',
      name: 'home',
      component: HomeView
    }
    /* {
      path: '/about',
      name: 'about',
      // route level code-splitting
      // this generates a separate chunk (About.[hash].js) for this route
      // which is lazy-loaded when the route is visited.
      component: () => import('../views/AboutView.vue')
    } */
  ]
})

export default router
