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
        <img src="@/assets/images/doctor-cuidando-mujer-mayor-casa.png" alt="Sample 1">
        <div class="description">
          <p>Recibe asistencia personalizada y cuidados profesionales en tu hogar, brindándote compañía, apoyo en las
            actividades
            diarias y garantizando tu seguridad y bienestar</p>
        </div>
      </SplideSlide>

      <SplideSlide style="padding: 15px;">
        <img src="@/assets/images/doc-anciana.png" alt="Sample 1">
        <div class="description">
          <p>Accede a tratamientos de rehabilitación y terapia física especializados, diseñados para mejorar tu
            movilidad, fuerza y
            flexibilidad, con la guía de un kinesiólogo calificado.</p>
        </div>
      </SplideSlide>

      <SplideSlide style="padding: 15px;">
        <img src="@/assets/images/hombre-asilo-ancianos.png" alt="Sample 1">
        <div class="description">
          <p>Recibe atención médica y cuidados profesionales en tu domicilio, incluyendo control de signos vitales,
            administración de
            medicamentos y curaciones generales.</p>
        </div>
      </SplideSlide>

      <SplideSlide style="padding: 15px;">
        <img src="@/assets/images/enfermera-ayudando-mujer-mayor-utilizar-andador.png" alt="Sample 1">
        <div class="description">
          <p>Explora una variedad de servicios adicionales para personas de la tercera edad, como retiro de
            medicamentos, transporte,
            asistencia en trámites, entre otros, para facilitar tu vida.</p>
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
    padding-top: 30px;
    color: #fff;
    background-color: #149ED7;
  }

  .description {
    width: 340px;
    margin: 10px;
  }

  .description p {
    margin-bottom: 0px
  }
</style>