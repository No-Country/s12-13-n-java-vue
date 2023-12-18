import { createRouter, createWebHistory } from 'vue-router'
import DashboardView from '../views/DashboardView.vue'
import HomeView from '../views/HomeView.vue'
import LoginView from '../views/LoginView.vue'
import DashboardWorker from '../views/DashboardWorker.vue'
import useAuth from '@/stores/auth' // Importa useAut

const routes = [
  {
    path: '/login',
    name: 'login',
    component: LoginView,
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
    path: '/dashboard/worker',
    name: 'worker',
    component: DashboardWorker,
    meta: {
      requireAuth: true,
    },
  },
];

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes,
});

router.beforeEach((to, from, next) => {
  const auth = useAuth(); // Utiliza useAuth directamente
  const isAuth = auth.token;

  if (to.meta.requireAuth && !isAuth) {
    // Redirige al usuario a la página de inicio de sesión
    next({ name: 'login' });
  } else {
    next();
  }
});

export default router;

