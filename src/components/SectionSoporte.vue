<script setup>
  import { ref } from 'vue'
  const name = ref('')
  const msj = ref('')
  const visible = ref(false)

  const isVisible = ()=>{
    
    return visible.value = !visible.value
  }

  const sendMessage=()=>{

    if(name.value !=''){
      console.log('tiene nombre')

       const url = `https://api.whatsapp.com/send?phone=${1133081248}&text=${encodeURIComponent(
        'Hola, me llamo '+ name.value + ', mi consulta: ' + msj.value)}`;
      window.open(url, "_blank");

      name.value=''
      msj.value=''

      return
    }
    isVisible()
    setTimeout(() => {
      isVisible()
    }, 1000)

  }
</script>

<template>
  <section class="conteiner" id="support">
    <div class="row gx-0">
      <div class="col-sm-12">
        <div class="row">
          <div class="col-12 col-sm-6">
            <div class="d-flex justify-content-center">
              <img src="@/assets/images/empresaria-centro-llamadas.png" alt="empresaria" />
            </div>
          </div>
          <div class="col-12 col-sm-6">
            <div class="conteiner-form">
              <label for="name">Nombre</label>
              <input type="text" v-model="name" id="name" autocomplete="false"/>
              <label for="messaje"  >Mensaje</label>
              <textarea v-model="msj" id="messaje" cols="30" rows="10"></textarea>
            </div>
            <span v-if="visible" class="conteiner-form mt-3" style="background-color: red;"><h2>EL NOMBRE ES REQUERIDO</h2></span>
          </div>
        </div>
      </div>
      <div class="col-sm-12">
        <div class="container-description">
          <h3>COMUNÍCATE CON NOSOTROS</h3>
          <button @click="sendMessage()" class="description-btn">whatsapp</button>
        </div>
      </div>
    </div>
  </section>
</template>

<style scoped>
.conteiner {
  padding-top: 48px;
  background-color: #1b9964;
  color: #fff;
}
.conteiner-form {
  display: flex;
  flex-direction: column;
  padding: 0px 32px;
}
.conteiner-form label {
  font-size: 20px;
   color: #fff;
}
.conteiner-form input,
.conteiner-form textarea {
  height: 40px;
  background-color: #fff;
  align-self: stretch;
  border-radius: 6px;
  font-size: 25px;
  border: 1px;
  
}
.conteiner-form textarea {
  height: 135px;
  font-size: 25px;
}
.container-description h3 {
  
  margin-top: 15px;
  font-size: 20px;
}
.container-description {
  margin-top: 30px;
  display: flex;
  padding: 0px 32px;
  justify-content: center;
  align-items: center;
  gap: 26px;
  flex-direction: column;
}


.description-btn {
  width: 328px;
  height: 51px;
  padding: 10px;
  border-radius: 6px;
  border: solid 3px #fff;
  background-color: transparent;
  color: white;
  font-size: 20px;
  margin-bottom: 48px;
}

@media (min-width: 768px) {
  img {
    height: 400px;
  }
}
@media (min-width: 576px) {
  .col-sm-6 {
    align-self: center;
  }
}
</style>
