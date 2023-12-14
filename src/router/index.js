import { createRouter, createWebHistory } from 'vue-router'
import DashboardView from '../views/DashboardView.vue'
import HomeView from '../views/HomeView.vue'
import LoginView from '../views/LoginView.vue'
import DashboardWorker from '../views/DashboardWorker.vue'

const routes = [
 {
      path: '/login',
      name: 'login',
      component: LoginView
    },

    {
      path: '/dashboard',
      name: 'dashboard',
      component: DashboardView,
     /*  meta: {
        requereAuth: true
      }, */
    },

    {
      path: '/',
      name: 'home',
      component: HomeView
    },
    {
      path: '/dashboard/worker',
      name: 'worker',
      component: DashboardWorker
    }




]

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes
})

/* router.beforeEach((to, from, next)=>{

  const auth = useAuth() 
  const isAuth = auth.token

  if((to.meta.requereAuth) && (isAuth == null)){
    next('login')
  }else{
    next()
  }
  
}) */

export default router
