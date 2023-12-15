import axios from '@/plugins/axios'
import { defineStore } from 'pinia'

const useAuth = defineStore('auth', {
  state: () => {
    return {
      token: null,
      rol: null,
      feedback: '',
      feedbackError: ''
    }
  },

  actions: {
    async login(username, password, tipo) {
      this.reset()

      let role = 0

      if (tipo == 'contratador') {
        role = 0
      }

      if (tipo == 'trabajador') {
        role = 1
      }

      await axios
        .post('auth/login', {
          username,
          password,
          role
        })

        .then((response) => {
          this.token = response.data.token
          localStorage.setItem('token', this.token)
          this.notification('Acceso correcto')
        })
        .catch((error) => {
          console.log('Error en login', error)
          this.notificationError(error.response.data.error)
        })
    },

    notification(messaje) {
      this.feedback = messaje
      setTimeout(() => {
        this.feedback = ''
      }, 4000)
      return
    },

    notificationError(messaje) {
      this.feedbackError = messaje
      setTimeout(() => {
        this.feedbackError = ''
      }, 4000)
      return
    },

    reset() {
      this.token = null
      this.rol = null
      this.feedback = ''
      this.feedbackError = ''
    }

  },

  persist:true

})

export default useAuth
