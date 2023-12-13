<script setup>
  /*import { ref } from 'vue'*/
  import router from '@/router'
  const login = ()=>{
    router.push({ name: 'login' })
  }
</script>

<template>
    <section class="conteiner">
      <div class="row gx-0">
        <div class="col-sm-12 col-md-6">
          <div class=" d-md-flex justify-content-md-center">

            <img src="@/assets/images/principal.svg" alt="principal">
          </div>
        </div>
        <div class="col-sm-12 col-md-6">
          <div class="conteiner-section1">
            <h3>CONECTAMOS GENERACIONES</h3>
            <P style="padding: 0px 21px"> Nuestra aplicación te ayuda a crear posiciones de trabajo personalizadas y conectar con personas comprometidas
              y listas para trabajar</P>
            <button @click="login" class="section1-btn"> Ofrece empleo </button>
            <p class="div">___________________ ó ___________________</p>
            <div class="container-description">
              <p>Nuestra aplicación te ayuda a crear posiciones de trabajo personalizadas y conectar con personas comprometidas
                y listas para trabajar.</p>
              <button @click="login" style="background-color: #1D3D8F;" class="description-btn">Busca oportunidades</button>
            </div>
          </div>
        </div>
      </div>
    </section>
</template>

<style scoped>
  
        
  
  .conteiner-section1, .container-description, .section-blog {
    display: flex;
    
    justify-content: center;
    align-items: center;
    gap: 10px;
    flex-direction: column;
    margin-bottom: 10px;
  }
  .conteiner-section1 {
    padding: 32px;
  }

  .conteiner-section1 h3 {
    font-size: 17px;
    font-weight: bold;
    margin: 0;
  }
  .conteiner-section1 p, .container-description p {
    text-align: center;
    margin: 0;
    margin-bottom: 15px;
  }
  .section1-btn, .description-btn {
    width: 100%;
    height: 51px;
    padding: 10px;
    border-radius: 6px;
    background-color: #149ED7;
    color: white;
    font-size: 20px;
  }
  .conteiner-section1 .div {
    margin-top: 20px;
  }

  @media (min-width: 768px){
    .col-md-6 {
      align-self: center; 
      img {
        height: 400px;
      }
    }
  }


</style>