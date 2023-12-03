//import axios from '@plugins/axios'
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

    async login(email, password, tipo){
      this.reset()
      let rol = 0
      if(tipo == 'contratador'){
        rol = 0
      }

      if(tipo =='trabajador'){
        rol = 1
      }
      console.log('desde el store', email, password, rol)
      alert('iniciando sesion', email, password, rol)
      this.notification('inicio correcto') //BORRAR solo de test

      
      // await axios.post('login',{
      //   email,
      //   password
      // })
      // .then (response =>{

      // })
      // .catch((error)=>{
      //   console.log('Error en login', error)
      // })

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
