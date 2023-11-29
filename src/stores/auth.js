import { defineStore } from 'pinia'

const useAuth = defineStore('auth', {
  state: () => {
    return {
      token: null,
      rol: null,
      statusUser: false,
      user: '',
      feedback: ''
    }
  },

  actions:{

    async login(email, password){
      this.reset()
      console.log('desde el store', email, password)
      alert('iniciando sesion', email, password)
      
      this.notification('inicio correcto') //BORRAR solo de test

    },

    notification(messaje) {
      this.feedback = messaje
      setTimeout(() => {
        this.feedback = ''
      }, 4000)
      return
    },

 

    reset() {
      this.token = null,
      this.statusUser = false,
      this.user = '',
      this.rol = null
      this.feedback=''
    },




  }

})

export default useAuth
