<script setup>
  import { ref } from 'vue'
</script>

<template>
    <section class="conteiner">
      <div class="row gx-0">
        <div class="col-sm-12 col-md-6">
          <img src="@/assets/images/principal.svg" alt="principal">
        </div>
        <div class="col-sm-12 col-md-6">
          <div class="conteiner-section1">
            <h3>CONECTAMOS GENERACIONES</h3>
            <P> Nuestra aplicación te ayuda a crear posiciones de trabajo personalizadas y conectar con personas comprometidas
              y listas para trabajar</P>
            <button class="section1-btn"> Ofrece empleo </button>
            <p class="div">___________________ ó ___________________</p>
          </div>
          <div class="container-description">
            <p>Nuestra aplicación te ayuda a crear posiciones de trabajo personalizadas y conectar con personas comprometidas
              y listas para trabajar.</p>
            <button style="background-color: #1D3D8F;" class="description-btn">Busca oportunidades</button>
          </div>
        </div>
      </div>
    </section>
</template>

<style scoped>
  
        
  
  .conteiner, .conteiner-section1, .container-description, .section-blog {
    display: flex;
    padding: 0px 32px;
    justify-content: center;
    align-items: center;
    gap: 10px;
    flex-direction: column;
    margin-bottom: 10px;
  }
  .conteiner-section1 {
    padding: 10px;
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
  .section1-btn, .description-btn,.blog-btn {
    width: 328px;
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
    }
  }

</style>