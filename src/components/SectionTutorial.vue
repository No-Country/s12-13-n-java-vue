<script setup>
  import { ref } from 'vue'
</script>

<template>

  <section class="conteiner">

    <!-- TODO CAROUSEL -->
    <div class="carrousel">
      <h1>Caroousel</h1>
    </div>

    <div class="container-description">
      <h3>CUBRE TUS NECESIDADES</h3>
      <button class="description-btn">Inicia</button>
    </div>

  </section>

</template>

<style scoped>
  .carrousel {
    margin-top: 10px;
  }
  .container-description h3 {
    color: #fff;
  }
  .conteiner,
  .container-description {
    display: flex;
    padding: 0px 32px;
    justify-content: center;
    align-items: center;
    gap: 10px;
    flex-direction: column;
    background-color: #F40909;
  }

  .description-btn,
  .blog-btn {
    width: 328px;
    height: 51px;
    padding: 10px;
    border-radius: 6px;
    border: solid 3px #fff;
    background-color: transparent;
    color: white;
    font-size: 20px;
  }

</style>