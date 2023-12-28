import axios from '@/plugins/axios'
import { defineStore } from 'pinia'

const useFormContratador = defineStore('formContratador', {
  state: () => {
    return {}
  },

  actions: {
    async submit(
      taskTitle,
      description,
      price,
      currencyType,
      occupations,
      taskDate,
      client,
      address
    ) {
      const token = localStorage.getItem('token')
      console.log('token:', token)
      const headers = {
        Authorization: `Bearer ${token}`
      }

      this.reset()

      await axios
        .post(
          'task',
          { taskTitle, description, price, currencyType, occupations, taskDate, client, address },
          { headers }
        )
        .then((response) => {
          console.log('Response', response)
        })
        .catch((error) => {
          console.log('Error en form', error)
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
