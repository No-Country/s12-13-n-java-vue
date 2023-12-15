import useAuth from '@/stores/auth'
import { createRouter, createWebHistory } from 'vue-router'
import DashboardView from '../views/DashboardView.vue'
import HomeView from '../views/HomeView.vue'
import LoginView from '../views/LoginView.vue'
import dashboardtrabajador from '../views/DashboardTrabajador.vue'

const routes = [
 {
      path: '/login',
      name: 'login',
      component: LoginView,
       meta: {
        requereAuth: false
      },
    },

    {
      path: '/dashboard',
      name: 'dashboard',
      component: DashboardView,
      meta: {
        requereAuth: true
      },
    },

    {
      path: '/DashboardTrabajador',
      name: 'DashboardTrabajador',
      component: dashboardtrabajador
    },

    {
      path: '/',
      name: 'home',
      component: HomeView,
       meta: {
        requereAuth: false
      },
    }
]

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes
})

router.beforeEach((to, from, next)=>{

  const auth = useAuth() 
  const isAuth = auth.token

  if((to.meta.requereAuth) && (isAuth == null)){
    next('login')
  }else{
    next()
  }
  
})

export default router
