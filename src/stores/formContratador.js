import axios from '@/plugins/axios'
import { defineStore } from 'pinia'

const useFormContratador = defineStore('formContratador', {
  state: () => {
    return {
      token: null,
      rol: null,
      feedback: '',
      feedbackError: ''
    }
  },

  actions: {
    async submit(username, password, role) {
      this.reset()

      await axios
        .post('task', {
          username,
          password,
          role
        })
        .then((response) => {
          console.log('Response', response)
        })
        .catch((error) => {
          console.log('Error en login', error)
        })
    },

    reset() {
      this.token = null
      this.rol = null
      this.feedback = ''
      this.feedbackError = ''
    }
  }
})

export default useFormContratador
