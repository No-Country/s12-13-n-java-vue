<script setup>
  import { ref, watch } from 'vue'
  import useAuth from '@/stores/auth'

  const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
  const store = useAuth()

  const isEmailValid = ref(true)

  const password = ref('') 
  const email = ref('')

  //para el color de la respuesta del back
  const hasError = false
  const hasSuccess = false

  //para ver la contraseña
  let eyes = ref(true)
  let type = ref('password')
  

  watch(email, (newEmail)=>{
    isEmailValid.value = emailRegex.test(newEmail);
    if(email.value == ""){
      isEmailValid.value= true
    }
  })

  const logged_in = async ()=> {
   await store.login(email.value, password.value)
  }

  const handleEyes = ()=> {
    eyes.value = !eyes.value

    if (type.value == 'password'){
      type.value ='text'
      return
    }
    if (type.value == 'text'){
      type.value ='password'
      return
    }
    
  }
 
  
</script>


<template>
  <main class="login">
    <h1>Login</h1>
    <div class="login-logo">
      <img src="@/assets/logo.png" alt="">
    </div>
    <div class="login-head">

      <input v-model="email" type="text" placeholder="email@">
      <span v-if="!isEmailValid" class="error response ">Formato Incorrecto</span>

      <input v-model="password" :type="type" placeholder="password" >
      <v-icon @click="handleEyes" v-if="eyes" class="icon" name="fa-regular-eye-slash" scale="2"/>
      <v-icon @click="handleEyes" v-if="!eyes" class="icon" name="fa-regular-eye" scale="2"/>
      <span class="response" :class="{'error': hasError, 'success': hasSuccess }">{{store.feedback}}</span>

      <button @click.prevent="logged_in" class="head-btn">Entrar</button>
    </div>
  </main>
</template>


<style scoped>
  .login {
    padding: 15px;
    width: 93vw;
  }
  .login h1 {
    text-align: center;
  }
  .login-logo {
    text-align: center
  }
  .login-logo img {
    width: 150px;
    height: 150px;
  }
  .login .login-head {
    display: flex;
    flex-direction: column;
    align-items: center;
    height: 100vh;
    padding: 10px;
    justify-content: center;
    border: solid 2px rgb(187, 255, 0);
    border-radius: 15px;
  }
  .login .login-head input{
    margin-top: 40px;
    border: solid 2px;
    border-radius: 25px;
    padding: 15px;
    width: 75%;
    font-size: 20px;
  }

  .login-head .icon {
    position: absolute;
    right: 18%;
    cursor: pointer;
  }

  .login .login-head .head-btn {
    background-color: rgb(212, 0, 255);
    margin-top: 60px;
    padding: 10px 20px;
    display: inline-block;
    border-radius: 15px;
    width: 50%;
    cursor: pointer;
    font-size: 16px;
    border: 2px solid #3498db;
    transition: background-color 0.3s;
  }

  .login .login-head .head-btn:hover {
    background-color: #3498db;
    color: #fff;
  }

  .response {
    width: 75%;
    font-size: 24px;
    margin-top: 5px; 
    color: #fff;
    text-align: center;
  }

  .error{
    background-color:#f73620;
  }

  .success{
    background-color: #2ecc71;  
  }

</style>