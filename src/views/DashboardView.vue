<script setup></script>

<template>
  <main>
    <header>ESPACIO PARA EL NAVBAR</header>
    <nav class="nav">
      <ul class="nav__list">
        <li class="nav__item">
          <div class="nav__item-container">
            <img class="nav__item-image" src="../assets/images/home-icon.svg" />
            <p class="nav__item-text nav__item-text_home">home</p>
          </div>
          <div class="nav__item-underline"></div>
        </li>
        <li class="nav__item">
          <div class="nav__item-container">
            <img class="nav__item-image" src="../assets/images/comment-icon.svg" />
            <p class="nav__item-text">chat</p>
          </div>
        </li>
      </ul>
    </nav>
    <section class="conteiner">
      <img src="@/assets/images/principal.svg" alt="principal" />

      <div class="conteiner-section1">
        <h3>CONECTAMOS GENERACIONES</h3>
        <P>
          Nuestra aplicación te ayuda a crear posiciones de trabajo personalizadas y conectar con
          personas comprometidas y listas para trabajar</P
        >
        <button class="section1-btn">Ofrece empleo</button>
        <p class="div">___________________ ó ___________________</p>
      </div>

      <div class="container-description">
        <p>
          Nuestra aplicación te ayuda a crear posiciones de trabajo personalizadas y conectar con
          personas comprometidas y listas para trabajar.
        </p>
        <button style="background-color: #1d3d8f" class="description-btn">
          Busca oportunidades
        </button>
      </div>
    </section>

    <section class="section-blog">
      <!-- contenido de la segunda tarjeta -->
    </section>
    <footer>ESPACIO PARA EL FOOTER</footer>
  </main>
</template>

<style scoped>
@import url('https://fonts.googleapis.com/css2?family=Yaldevi:wght@600&display=swap');
ul,
p,
li {
  margin: 0;
  padding: 0;
  list-style: none;
}
.nav {
  padding: 15px 42px;
  height: 42px;
  display: flex;
  align-items: center;
  justify-content: center;
}
.nav__list {
  display: flex;
  justify-content: center;
  gap: 70px;
}
.nav__item {
  display: flex;
  flex-direction: column;
  gap: 10px;
}
.nav__item-image {
  width: 30px;
  height: 27.704px;
}

.nav__item-container {
  display: flex;
  align-items: baseline;
  gap: 13px;
}

.nav__item-text {
  color: var(--blue2, #149ed7);
  font-family: 'Yaldevi';
  font-size: 27.683px;
  font-style: normal;
  font-weight: 600;
  line-height: 67%; /* 18.548px */
  letter-spacing: 2.215px;
}
.nav__item-text_home {
  color: var(--blue1, #1d3d8f);
}
.nav__item-underline {
  border-bottom: solid 9px #1d3d8f;
  width: 130px;
  border-radius: 20px;
}
</style>
