import axios from '@/plugins/axios'
import { defineStore } from 'pinia'

const useFormContratador = defineStore('formContratador', {
  state: () => {
    return {
      taskTitle: '',
      category: '',
      currency: '',
      taskDescription: '',
      taskLocation: ''
    }
  },

  actions: {
    async submit(formData) {
      this.reset()

      await axios
        .post('task', {
          formData
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
