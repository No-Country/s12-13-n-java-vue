import useAuth from '@/stores/auth'
import { createRouter, createWebHistory } from 'vue-router'
import LoginView from '../views/LoginView.vue'
import DashboardTrabajador from '../views/DashboardTrabajador.vue'
import ChatView from '../views/ChatView.vue'

const routes = [
  {
    path: '/login',
    name: 'login',
    component: LoginView,
    meta: {
      requereAuth: false
    }
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
      component: DashboardTrabajador,
      meta: {
        requereAuth: true
      },
    },

    {
      path: '/Chat',
      name: 'Chat',
      component: ChatView,
      meta: {
        requereAuth: true
      },
    },

  {
    path: '/',
    name: 'home',
    component: HomeView,
    meta: {
      requereAuth: false
    }
  }
]

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes
})

router.beforeEach((to, from, next) => {
  const auth = useAuth()
  const isAuth = auth.token

  if (to.meta.requereAuth && isAuth == null) {
    next('login')
  } else {
    next()
  }
})

export default router
