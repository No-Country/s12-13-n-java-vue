import useAuth from '@/stores/auth'
import { createRouter, createWebHistory } from 'vue-router'
import LoginView from '../views/LoginView.vue'
import PostulationsView from '../views/PostulationsView.vue'
import ChatView from '../views/ChatView.vue'
import DashboardView from '../views/DashboardView.vue'
import HomeView from '../views/HomeView.vue'
import DashboardWorker from '@/views/DashboardWorker.vue'

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

      requireAuth: true,
    },
  },
  {
    path: '/',
    name: 'home',
    component: HomeView,
  },
  {
    path: '/worker',
    name: 'worker',
    component: DashboardWorker,
    meta: {
      requireAuth: true,
    },
  },
  {
    path: '/posts',
    name: 'postulations',
    component: PostulationsView,
    query: {
      category: ''
    },
    meta: {
      requereAuth: true
    }
  },

  {
    path: '/Chat',
    name: 'Chat',
    component: ChatView,
    meta: {
      requereAuth: true
    }
  },
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
