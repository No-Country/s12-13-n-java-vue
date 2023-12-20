
<script setup>
  import { Splide, SplideSlide } from "@splidejs/vue-splide";
  import "@splidejs/vue-splide/css/skyblue";

  //Define your options here
  const options = {
    type: "loop",
    drag: "free",
    snap: true,
    perPage: 1, // Number of slides to show at once
    autoplay: true,
    arrows: false,
  };
</script>

<template>
  <section>
    <Splide :options="options" aria-label="My Favorite Images">
    
      <SplideSlide style="padding: 15px;">
        <img src="@/assets/images/vista-lateral-medicos-sonrientes-posando.png" alt="Sample 1">
        <div class="description">
          <p>Tu experiencia como kinesiólogo/a será fundamental para ayudar a las personas mayores a mantener y mejorar su calidad de
          vida a través de terapias físicas adecuadas.</p>
        </div>
      </SplideSlide>
    
      <SplideSlide style="padding: 15px;">
        <img src="@/assets/images/hombre-coche-conduciendo.png" alt="Sample 1">
        <div class="description">
          <p>Si eres conductor y tienes un vehículo adaptado para personas con movilidad reducida, puedes ofrecer servicios de
          transporte personalizado a personas de la tercera edad.</p>
        </div>
      </SplideSlide>
     
      <SplideSlide style="padding: 15px;">
        <img src="@/assets/images/trabajadora-salud-espacio.png" alt="Sample 1">
        <div class="description" >
          <p>Administrar medicamentos, realizar curaciones, controlar signos vitales. Tu experiencia como enfermero/a te permitirá
          ofrecer un nivel de atención y cuidado a quienes lo necesiten.</p>
        </div>
      </SplideSlide>
    
      <SplideSlide style="padding: 15px;">
        <img src="@/assets/images/hermosa-mujer-joven.png" alt="Sample 1">
        <div class="description">
          <p>Si tienes experiencia en el cuidado de personas mayores, puedes ofrecer servicios de cuidado y compañía personalizados
          en el hogar de quienes lo necesiten.</p>
        </div>
      </SplideSlide>
    
    </Splide>
  </section>
</template>

<style scoped>
section {
    display: flex;
    flex-direction: column;
    text-align: center;
    color: #fff;
    background-color: #1d3d8f;
    padding-top: 30px;
  }

  .description {
    width: 340px;
    margin: 10px;
  }

  .description p {
    margin-bottom: 0px
  }

  @media screen (min-width: 768px){
  .description{
    font-size: 14px;
  }
}
</style>