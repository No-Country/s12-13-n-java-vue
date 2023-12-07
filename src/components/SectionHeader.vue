
<template>
  <div class="placeholder"></div>
  <section class="container gx-0">
    <header>
      <nav class="menu">
        <li class="menu__item" :class="{ hidden: isVisible }">
          <img class="menu__item-logo" src="../assets/logo.svg" />
        </li>
        <li class="menu__item">
          <button class="menu__item-button" @click="toggleSidebar">
            <img class="menu__item-image" src="../assets/images/menu-icon.svg" />
          </button>
        </li>
      </nav>
    </header>
    <section class="sidebar" :class="{ visible: isVisible }">
      <img class="sidebar-logo" src="../assets/logo.svg" />
      <ul class="nav__list">
        <li class="nav__item">
          <a class="nav__item link">
            <div class="nav__item-picture">
              <img class="nav__item-icon" src="../assets/images/page-icon.svg" alt="page-icon" />
            </div>
            <p class="nav__item-text">Categorías</p>
          </a>
        </li>
        <li class="nav__item">
          <a class="nav__item link">
            <div class="nav__item-picture">
              <img class="nav__item-icon" src="../assets/images/blog-icon.svg" alt="blog-icon" />
            </div>
            <p class="nav__item-text">Blog</p>
          </a>
        </li>
        <li class="nav__item">
          <a class="nav__item link">
            <div class="nav__item-picture">
              <img
                class="nav__item-icon"
                src="../assets/images/support-icon.svg"
                alt="support-icon"
              />
            </div>
            <p class="nav__item-text">Soporte</p>
          </a>
        </li>
        <li class="nav__item">
          <a class="nav__item link">
            <div class="nav__item-picture">
              <img
                class="nav__item-icon"
                src="../assets/images/people-icon.svg"
                alt="people-icon"
              />
            </div>
            <p class="nav__item-text">Nosotros</p>
          </a>
        </li>
      </ul>
      <div>
        <section class="sidebar__social">
          <div class="sidebar__line"></div>
          <h3 class="social-title sidebar-title">REDES</h3>
          <ul class="social-list">
            <li class="social-item">
              <a class="social-link link"
                ><img src="../assets/images/facebook-icon.svg" alt="facebook" class="social-image"
              /></a>
            </li>
            <li class="social-item">
              <a class="social-link link"
                ><img
                  src="../assets/images/instagram-icon.svg"
                  alt="instagram"
                  class="social-image"
              /></a>
            </li>
            <li class="social-item">
              <a class="social-link link"
                ><img src="../assets/images/tiktok-icon.svg" alt="tiktok" class="social-image"
              /></a>
            </li>
            <li class="social-item">
              <a class="social-link link"
                ><img src="../assets/images/twitter-icon.svg" alt="twitter" class="social-image"
              /></a>
            </li>
          </ul>
        </section>
        <section class="sidebar-config">
          <h3 class="config-title sidebar-title">TAMAÑO</h3>
          <img
            src="../assets/images/size-config.svg"
            alt="size configuration"
            class="config-image"
          />
        </section>
      </div>
      <a href="/login" class="sidebar__button link" @onclick="navigateToLogin">Iniciar sesión</a>
    </section>
  </section>
</template>

<script>
  import router from '@/router';
  export default {
    data() {
      return {
        isVisible: false
      }
    },
    methods: {
      toggleSidebar() {
        this.isVisible = !this.isVisible
      },
      navigateToLogin() {
        // this.$router.push('/login')
        router.push({name:'login'})
      }
    }
  }
  
</script>

<style scoped>
@import url('https://fonts.googleapis.com/css2?family=Baloo+2&display=swap');
ul,
p,
li {
  margin: 0;
  padding: 0;
  list-style: none;
}

.placeholder {
  height: 70px;
  width: 393px;
  background: #fff;
}
.container {
  position: fixed;
  z-index: 100;
}
.menu {
  z-index: 100;
  width: 393px;
  margin-top: -70px;
  display: flex;
  justify-content: space-between;
  align-items: center;
  padding-left: 32px;
  padding-right: 32px;
  height: 70px;
  background: #fff;
}
.menu__item-logo {
  height: 46.672px;
}

.menu__item-button {
  background: none;
  border: none;
  margin: 0;
  padding: 0;
}
.menu__item-image {
  width: 38px;
  height: 38px;
}

.menu__item-container {
  display: flex;
  align-items: baseline;
  gap: 13px;
}

.menu__item-text {
  color: var(--blue2, #149ed7);
  font-family: 'Yaldevi';
  font-size: 27.683px;
  font-style: normal;
  font-weight: 600;
  line-height: 67%; /* 18.548px */
  letter-spacing: 2.215px;
}
.menu__item-text_home {
  color: var(--blue1, #1d3d8f);
}
.menu__item-underline {
  border-bottom: solid 9px #1d3d8f;
  width: 130px;
  border-radius: 20px;
}
.sidebar {
  width: 289px;
  background-color: white;
  height: 720px;
  overflow-x: visible;
  overflow-y: visible;
  position: fixed;
  top: 0;
  right: 0;
  transform: translateX(100%);
  transition: transform 0.3s ease;
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: space-between;
  gap: 61px;
  border-radius: 6px 0px 0px 6px;
  box-shadow: 0px 4px 20.8px 0px rgba(16, 96, 217, 0.25);
}

.visible {
  transform: translateX(0);
}

.hidden {
  display: none;
}

.sidebar-logo {
  width: 171px;
}

.nav__list {
  display: flex;
  flex-direction: column;
  align-items: flex-start;
  gap: 20px;
  align-self: flex-start;
}

.nav__item {
  display: flex;
  gap: 10px;
}

.link {
  text-decoration: none;
}

.link:hover {
  cursor: pointer;
}
.nav__item-picture {
  width: 28px;
  display: flex;
  align-items: center;
  justify-content: center;
}
.nav__item-text {
  color: #000;
  font-family: 'Baloo 2';
  font-size: 20px;
  font-style: normal;
  font-weight: 400;
  line-height: normal;
}
.sidebar__line {
  width: 207.022px;
  border-bottom: solid 1px #149ed7;
}

.sidebar__social {
}

.sidebar-title {
  margin-top: 36px;
  margin-bottom: 19px;
  color: var(--black1, #2f2f2f);
  font-family: 'Baloo 2';
  font-size: 20px;
  font-weight: 500;
}

.social-list {
  display: flex;
  gap: 27px;
  align-items: center;
}

.sidebar-config {
  margin-top: 28px;
}
.config-title {
  margin-top: 0;
  margin-bottom: 9px;
}

.sidebar__button {
  color: var(--white, #fff);
  font-family: 'Baloo 2';
  font-size: 20px;
  font-weight: 700;
  display: flex;
  height: 51px;
  padding: 10px;
  width: 100%;
  justify-content: center;
  align-items: center;
  border-radius: 6px;
  background: var(--blue1, #1d3d8f);
  border: none;
}

@media screen and (max-width: 833px) {
  .sidebar {
    /* width: 289px; */
    height: 802px;
    padding: 60.39px 40.5px;
  }
}
</style>
