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
    <div class="login-logo">
      <img src="@/assets/logo.svg" alt="">
    </div>
    <div class="login-head">
      <div style="width: 85%;">
        <label  for="email">Email o Usuario</label>
      </div>
      <input id="email" v-model="email" type="text">
      <span v-if="!isEmailValid" class="error response ">Formato Incorrecto</span>

      <div style="width: 85%; margin-top: 30px">
        <label for="password">Contraseña</label>
      </div>
      <input id="password" v-model="password" :type="type" >
      <v-icon @click="handleEyes" v-if="eyes" class="icon" name="fa-regular-eye-slash" scale="2"/>
      <v-icon @click="handleEyes" v-if="!eyes" class="icon" name="fa-regular-eye" scale="2"/>
      <span class="response" :class="{'error': hasError, 'success': hasSuccess }">{{store.feedback}}</span>

      

      <button @click.prevent="logged_in" class="head-btn">Iniciar Sesión</button>
      
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
    margin-top: 150px;
    width: 150px;
    height: 150px;
  }
  .login .login-head {
    display: flex;
    flex-direction: column;
    align-items: center;
    height: 75vh;
    padding: 10px;
    justify-content: center;
    border: solid 2px rgb(187, 255, 0);
    border-radius: 15px;
  }
  .login-head div label {
    font-size: 20px; 
  }
  .login .login-head input{
    margin-top: 5px;
    border: solid 2px;
    background-color: #D9D9D9;
    padding: 5px;
    width: 85%;
    height: 40px;
   
  }

  .login-head .icon {
    position: absolute;
    right: 18%;
    top:56%;
    cursor: pointer;
  }

  .login .login-head .head-btn {
    background-color:#1D3D8F;
    color: #fff;
    margin-top: 60px;
    padding: 10px 20px;
    display: inline-block;
    border-radius: 15px;
    width: 50%;
    cursor: pointer;
    font-size: 16px;
    border: 2px solid #1D3D8F;
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