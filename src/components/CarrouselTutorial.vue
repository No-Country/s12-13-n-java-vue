<script setup>

</script>

<template>
<div class="conteiner">
  <!-- TODO CAROUSEL -->
  <div id="carouselExampleCaptions" class="carousel slide" data-bs-ride="carousel">
    <div class="carousel-indicators">
      <button type="button" data-bs-target="#carouselExampleCaptions" data-bs-slide-to="0" class="active"
        aria-current="true" aria-label="Slide 1"></button>
      <button type="button" data-bs-target="#carouselExampleCaptions" data-bs-slide-to="1" aria-label="Slide 2"></button>
      <button type="button" data-bs-target="#carouselExampleCaptions" data-bs-slide-to="2" aria-label="Slide 3"></button>
      <button type="button" data-bs-target="#carouselExampleCaptions" data-bs-slide-to="3" aria-label="Slide 3"></button>
    </div>
    <div class="carousel-inner">
      <div class="carousel-item active">
        <img src="@/assets/images/doctor-cuidando-mujer-mayor-casa.png" class="d-block w-100" alt="doc-cuida">
        <div class="carousel-caption ">
          <p style="color: black;">Recibe asistencia perzonalizada y cuidados profesionales en tu hogar, brindandote
            compañia, apoyo en las actividades diarias y garantizando tu seguridad y bienestar</p>
        </div>
      </div>
  
      <div class="carousel-item">
        <img src="@/assets/images/doc-anciana.png" class="d-block w-100" alt="doc-anciana">
        <div class="carousel-caption">
          <h5>Second slide label</h5>
          <p>Some representative placeholder content for the second slide.</p>
        </div>
      </div>
      <div class="carousel-item">
        <img src="@/assets/images/hombre-asilo-ancianos.png" class="d-block w-100" alt="hombre-asilo-ancianos">
        <div class="carousel-caption ">
          <h5>Third slide label</h5>
          <p>Some representative placeholder content for the third slide.</p>
        </div>
      </div>
      <div class="carousel-item">
        <img src="@/assets/images/enfermera-ayudando-mujer-mayor-utilizar-andador.png" class="d-block w-100"
          alt="enfermera-ayudando-mujer-mayor-utilizar-andador">
        <div class="carousel-caption ">
          <p>Some representative placeholder content for the third slide.</p>
        </div>
      </div>
    </div>
  
  </div>
</div>
</template>


<style scoped>
</style>