<script setup>
  

  //pasar props
  // defineProps({
  //   msg: {
  //     type: String,
  //     required: true
  //   }

  // })

</script>

<template>
  <main>
    <header>ESPACIO PARA EL NAVBAR</header>
    <section class="conteiner">
      
      <img src="@/assets/images/principal.svg" alt="principal">
      
      <section class="conteiner-description">
        <h3>CONECTAMOS GENERACIONES</h3>
        <P> Nuestra aplicación te ayuda a crear posiciones de trabajo personalizadas y conectar con personas comprometidas y listas para trabajar</P>
        <button class="description-btn"> Ofrece empleo </button>
        <p class="div">___________________ ó ___________________</p>
      </section>

      <section class="container-description2">
        <p>Nuestra aplicación te ayuda a crear posiciones de trabajo personalizadas y conectar con personas comprometidas y listas para trabajar.</p>
        <button style="background-color: #1D3D8F;" class="description2-btn">Busca oportunidades</button>
      </section>
    </section>
  </main>
</template>

<style scoped>
  .conteiner, .conteiner-description, .container-description2 {
    display: flex;
    padding: 0px 32px;
    justify-content: center;
    align-items: center;
    gap: 10px;
    flex-direction: column;
  }
  .conteiner-description {
    padding: 10px;
  }

  .conteiner-description h3 {
    font-size: 17px;
    font-weight: bold;
    margin: 0;
  }
  .conteiner-description p, .container-description2 p {
    text-align: center;
    margin: 0;
    margin-bottom: 15px;
  }
  .description-btn, .description2-btn {
    width: 328px;
    height: 51px;
    padding: 10px;
    border-radius: 6px;
    background-color: #149ED7;
    color: white;
    font-size: 20px;
  }
  .conteiner-description .div {
    margin-top: 20px;
  }
 


</style>
