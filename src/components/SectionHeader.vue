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
    <section :style="getSidebarStyles()" class="sidebar" :class="{ visible: isVisible }">
      <img class="sidebar-logo" src="../assets/logo.svg" />
      <section v-if="$route.name === 'dashboard'" class="profile">
        <div class="circle-container">
          <img src="../assets/images/avatar.svg" alt="avatar" class="avatar" />
        </div>
        <div class="profile__info">
          <p class="profile__name">Florencia R.</p>
          <p class="profile__role">Contratador</p>
        </div>
      </section>
      <ul class="nav__list">
        <div v-if="$route.name === 'dashboard'" class="sidebar__line"></div>
        <li v-if="$route.name === 'home'" class="nav__item">
          <a class="nav__item link" @click="handleClickScrollNeeds">
            <div class="nav__item-picture">
              <img class="nav__item-icon" src="../assets/images/page-icon.svg" alt="page-icon" />
            </div>
            <p class="nav__item-text">Necesidades</p>
          </a>
        </li>
        <li v-if="$route.name === 'dashboard'" class="nav__item">
          <a class="nav__item link" @click="handleClickScrollNeeds">
            <div class="nav__item-picture">
              <img class="nav__item-icon" src="../assets/images/profile-icon.svg" alt="page-icon" />
            </div>
            <p class="nav__item-text">Perfil</p>
          </a>
        </li>
        <li v-if="$route.name === 'dashboard'" class="nav__item">
          <a class="nav__item link" @click="handleClickScrollNeeds">
            <div class="nav__item-picture">
              <img
                class="nav__item-icon"
                src="../assets/images/tutorial-icon.svg"
                alt="page-icon"
              />
            </div>
            <p class="nav__item-text">Tutorial</p>
          </a>
        </li>
        <li v-if="$route.name === 'home'" class="nav__item" @click="handleClickScrollJobs">
          <a class="nav__item link">
            <div class="nav__item-picture">
              <img class="nav__item-icon" src="../assets/images/blog-icon.svg" alt="blog-icon" />
            </div>
            <p class="nav__item-text">Trabajos</p>
          </a>
        </li>
        <li class="nav__item">
          <a class="nav__item link" @click="handleClickScrollSupport">
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
          <a class="nav__item link" @click="handleClickScrollAboutUs">
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
          <h3 class="config-title sidebar-title" id="city">TAMAÑO</h3>
          <div>
            <div class="slider-container">
              <!-- <span class="circle start"></span> -->
              <input
                class="range-slider"
                type="range"
                id="slider"
                step="0.3125"
                v-model="sliderValue"
                @input="handleSliderInput"
                :min="minValue"
                :max="maxValue"
              />
              <!-- <span class="circle center"></span>
              <span class="circle end"></span> -->
            </div>
          </div>
        </section>
      </div>
      <a
        v-if="$route.name === 'home'"
        href="/login"
        class="sidebar__button link"
        @onclick="navigateToLogin"
        >Iniciar sesión</a
      >
      <a v-if="$route.name === 'dashboard'" href="#" class="sidebar__button link">Cerrar sesión</a>
    </section>
  </section>
</template>

<script>
import router from '@/router'

export default {
  data() {
    return {
      isVisible: false,
      sliderValue: 25 / 16,
      minValue: 20 / 16,
      maxValue: 30 / 16
    }
  },
  methods: {
    handleSliderInput() {
      var el = document.getElementById('app')
      console.log('elementHTMLel:', el)
      var style = window.getComputedStyle(el, null).getPropertyValue('font-size')
      console.log('elementHTMLstyle:', style)
      var fontSize = parseFloat(style)
      console.log('elementHTMLfontSize:', fontSize)
      el.style.fontSize = this.sliderValue + 'rem'
    },
    toggleSidebar() {
      this.isVisible = !this.isVisible
    },
    handleClickScrollAboutUs() {
      const element = document.getElementById('about-us')
      if (element) {
        element.scrollIntoView({ behavior: 'smooth' })
      }
    },
    handleClickScrollSupport() {
      const element = document.getElementById('support')
      if (element) {
        element.scrollIntoView({ behavior: 'smooth' })
      }
    },
    handleClickScrollJobs() {
      const element = document.getElementById('jobs')
      if (element) {
        element.scrollIntoView({ behavior: 'smooth' })
      }
    },
    handleClickScrollNeeds() {
      const element = document.getElementById('needs')
      if (element) {
        element.scrollIntoView({ behavior: 'smooth' })
      }
    },
    navigateToLogin() {
      // this.$router.push('/login')
      router.push({ name: 'login' })
    },
    getSidebarStyles() {
      // Determine the route and set gap size accordingly
      const route = this.$route.name
      const gapSize = route === 'home' ? '61px' : '24px'

      // Return an object with the dynamic styles
      return {
        gap: gapSize
        // Add other styles as needed
      }
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
  font-size: 27.6832px;
  font-style: normal;
  font-weight: 600;
  line-height: 67%;
  letter-spacing: 2.2144px;
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
  min-height: 720px;
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
  align-items: center;
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
  /* font-size: 20px; */
  font-style: normal;
  font-weight: 400;
  line-height: normal;
}
.sidebar__line {
  width: 207.0224px;
  border-bottom: solid 1px #149ed7;
}

.sidebar__social {
}

.sidebar-title {
  margin-top: 36px;
  margin-bottom: 19px;
  color: var(--black1, #2f2f2f);
  font-family: 'Baloo 2';
  /* font-size: 20px; */
  font-weight: 500;
}

.social-list {
  display: flex;
  gap: 27px;
  align-items: center;
}

.sidebar-config {
  margin-top: 28px;
  /* /* height: 3.4375rem; */
  /* display: flex; */
  /* flex-direction: column;  */
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
  width: fit-content;
  justify-content: center;
  align-items: center;
  border-radius: 6px;
  background: var(--blue1, #1d3d8f);
  border: none;
}

.range-slider {
  background-color: #149ed7;
  border: 0.5008px solid #149ed7;
  height: 0.5008px;
  cursor: pointer;
  appearance: none;
  width: 200px;
  margin-left: 7px;
  z-index: 1000;
}

.slider-container {
  width: 210px;
}

.sidebar-config .range-slider::-webkit-slider-thumb {
  -webkit-appearance: none;
  width: 20px;
  height: 20px;
  background-color: #149ed7;
  border-radius: 50%;
  cursor: pointer;
  z-index: 1000;
  position: relative;
}

.circle {
  width: 10px;
  height: 10px;
  background-color: #1d3d8f;
  border-radius: 50%;
  position: absolute;
  z-index: 1;
  font-size: 0.625rem;
  margin-bottom: -1.125rem;
}

.circle.start {
  left: 0;
  transform: translateX(-50%);
  margin-left: 57px;
}

.circle.center {
  left: 50%;
  transform: translateX(-50%);
}

.circle.end {
  right: 0;
  transform: translateX(50%);
  margin-right: 53px;
}

.circle-container {
  width: 55px;
  height: 55px;
  overflow: hidden;
  border-radius: 50%;
  background-color: #149ed7;
  display: flex;
  justify-content: center;
  align-items: center;
}

.avatar {
  width: 100%;
  height: auto;
  border-radius: 50%;
}

.profile {
  display: flex;
  gap: 14px;
  align-items: center;
}

.profile__name {
  font-family: 'Baloo 2';
  font-weight: 500;
}

.profile__role {
  color: var(--blue2, #149ed7);
  font-family: 'Baloo 2';
  font-weight: 400;
}
.profile__info {
  display: flex;
  flex-direction: column;
  justify-content: center;
}

@media screen and (max-width: 833px) {
  .sidebar {
    min-height: 802px;
    padding: 60.3904px 40.5008px;
  }
}
</style>
