<script setup>
</script>

<template>
  <div class="container-description ">
  
    <h3 class="mb-3">REDES</h3>
  
    <div class="mb-3">
      <v-icon class="me-3" fill="white" scale="1.5" name="ri-facebook-box-line" />
      <v-icon class="me-3" fill="white" scale="1.5" name="si-instagram" />
      <v-icon class="me-3" fill="white" scale="1.5" name="co-tiktok" />
      <v-icon fill="white" scale="1.5" name="ri-twitter-line" />
    </div>

    <div class="foot">
      <small style="color: #fff;">copyright © 2023 unamano</small>
      <small style="color: #fff;"> seleccionado 12</small>
    </div>
  
  
  </div>
</template>


<style scoped>
    .container-description h3 {
    color: #fff;
    margin-top: 48px;
    font-size: 20px;

  }
 
  .container-description {
    display: flex;
    background-color: #1D3D8F;
    flex-direction: column;
    align-items: center;
    text-align: center
  }

  .foot {
    display: flex;
    flex-direction: column;
  }

  @media (min-width: 768px){
    .foot{
      display: flex;
      flex-direction: row;
    }
  }
</style>