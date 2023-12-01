import axios from '@/plugins/axios'
import { defineStore } from 'pinia'

const useAuth = defineStore('auth', {
  state: () => {
    return {
      token: null,
      rol: null,
      feedback: ''
    }
  },

  actions:{

    async login(username, password, tipo){

      this.reset()

      let role = 0

      if(tipo == 'contratador'){
        role = 0
      }

      if(tipo =='trabajador'){
        role = 1
      }

      await axios.post('auth/login',{
        username,
        password,
        role
      })
      
      .then (response =>{
        console.log('respuesta del back',response)
      })
      .catch((error)=>{
        console.log('Error en login', error)
      })

    },

    notification(messaje) {
      this.feedback = messaje
      setTimeout(() => {
        this.feedback = ''
      }, 4000)
      return
    },

 

    reset() {
      this.token = null
      this.rol = null
      this.feedback=''
    },




  }

})

export default useAuth
