<script setup>
  import { ref } from 'vue'
  import useAuth from '@/stores/auth'
  import router from '@/router'

  const store = useAuth()

  const password = ref('')
  const email = ref('')

  // Para ver la contraseña
  let eyes = ref(true)
  let type = ref('password')

  // Control del switch
  let activeCont = true
  let activeTrab = false
  let activeTab = ref('contratador')

  const setActiveTab = (tab) => {
    activeTab.value = tab;

    if (activeTab.value === 'contratador') {
      activeCont = true
      activeTrab = false
    }
    if (activeTab.value === 'trabajador') {
      activeCont = false
      activeTrab = true
    }
  };

  const logged_in = async () => {
    // Realizar el inicio de sesión
    if (!email.value || !password.value) {
      // Notificar al usuario que ambos campos son requeridos
      store.notificationError('Por favor, completa todos los campos.');
      return;
    }
    await store.login(email.value, password.value, activeTab.value);
  
    // Verificar si el usuario está autenticado
    if (store.token) {
      // Si está autenticado, redirigir a la página correspondiente
      if (activeTab.value === 'trabajador') {
        router.push({ name: 'worker' });
      } else {
        router.push({ name: 'dashboard' });
      }
    } else {
      // Si no está autenticado, puedes mostrar un mensaje de error o redirigir a la página de inicio de sesión
      router.push({ name: 'login' });
    }
  }

  const handleEyes = () => {
    eyes.value = !eyes.value

    if (type.value === 'password') {
      type.value = 'text'
    } else if (type.value === 'text') {
      type.value = 'password'
    }
  }
</script>



<template>
  <main class="login container">
    <div class="login-logo">
      <img src="@/assets/logo.svg" alt="">
    </div>
    <div class="login-head">
      <div style="width: 85%;">
        <label style=" font-size: 20px; " for="email">Email o Usuario</label>
      </div>
      <input autocomplete="none" id="email" v-model="email" type="text">
      <div style="width: 85%; margin-top: 30px">
        <label style=" font-size: 20px; " for="password">Contraseña</label>
      </div>

      <input id="password" v-model="password" :type="type">
      <v-icon @click="handleEyes" v-if="eyes" class="icon" name="fa-regular-eye-slash" scale="2" />
      <v-icon @click="handleEyes" v-if="!eyes" class="icon" name="fa-regular-eye" scale="2" />
      <span class="response, success mt-2">{{store.feedback}}</span>
      <span class="response, error mt-2">{{store.feedbackError}}</span>

      <div class="switch-button">
        <span :class="{ active: activeTab.value === 'contratador' }"></span>
        <button class="switch-button-case contratador " :class="{ 'active-case':  activeCont }"
          @click="setActiveTab('contratador')" style="border-radius: 6px 0px 0px 6px;">
          CONTRATADOR
        </button>
        <button class="switch-button-case trabajador" :class="{ 'active-case': activeTrab }"
          @click="setActiveTab('trabajador')" style="border-radius: 0px 6px 6px 0px;">
          TRABAJADOR
        </button>
      </div>

      <button @click.prevent="logged_in" class="head-btn">Iniciar Sesión</button>

    </div>
    <div class="footer">
      <hr style=" margin-top: 46px">
      <label style="position: relative; left: 52px;">¿No tienes cuenta? <b>Regístrate</b></label>
      <hr>
      <label>¿Olvidasgte tu contraseña? <b>Restáurala</b></label>
    </div>
  </main>
</template>


<style scoped>
  .login-logo {
    text-align: center
  }

  .login-logo img {
    margin-top: 76px;
    width: 150px;
    height: 150px;
  }

  .login .login-head {
    display: flex;
    flex-direction: column;
    align-items: center;
    padding: 10px;
    justify-content: center;
  }

  .login .login-head input {
    margin-top: 5px;
    border: solid 1px;
    border-radius: 6px;
    background-color: #D2D8EE;
    padding: 5px;
    width: 85%;
    height: 40px;
    font-size: 20px;
  }

  .login-head .icon {
    position: absolute;
    right: 14%;
    top: 45%;
    cursor: pointer;
  }

  .login .login-head .head-btn {
    border-radius: 6px;
    background-color: #1D3D8F;
    color: #fff;
    margin-top: 20px;
    padding: 10px;
    display: inline-block;
    width: 85%;
    font-size: 16px;
    border: 2px solid #1D3D8F;
    transition: background-color 0.3s;
  }

  .login .login-head .head-btn:hover {
    background-color: #149ED7;
    color: #fff;
  }

  .response {
    width: 75%;
    font-size: 24px;
    margin-top: 5px;
    color: #fff;
    text-align: center;
    margin-top: 10px;
  }

  .error {
    background-color: #f73620;
  }

  .success {
    background-color: #2ecc71;
  }

  .footer label {
    position: relative;
    left: 20px;
    font-size: 20px;
  }

  .footer label b {
    cursor: pointer;
    color: #149ED7;
  }

  .footer hr {
    width: 180px;
    border: solid 1px #149ED7;
    margin-left: 95px;
  }


  .switch-button {
    /* Estilos para el contenedor del interruptor */
    margin-top: 46px;
    display: flex;
    align-self: auto;
  }

  .switch-button-case {
    /* Estilos para los casos del interruptor */
    width: 148px;
    height: 40px;
    background-color: #D2D8EE;
  }

  .active-case {
    /* Estilos para el caso del interruptor activo */
    color: #1D3D8Fed;
    border: solid 2px #1D3D8Fed;
    background-color: #fff;
    transition: background-color 0.3s;
  }
</style>