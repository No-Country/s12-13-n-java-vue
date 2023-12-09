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
          <a class="nav__item link" @click="handleClickScrollNeeds">
            <div class="nav__item-picture">
              <img class="nav__item-icon" src="../assets/images/page-icon.svg" alt="page-icon" />
            </div>
            <p class="nav__item-text">Necesidades</p>
          </a>
        </li>
        <li class="nav__item" @click="handleClickScrollJobs">
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
      <a href="/login" class="sidebar__button link" @onclick="navigateToLogin">Iniciar sesión</a>
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
  height: 4.375rem;
  width: 24.5625rem;
  background: #fff;
}
.container {
  position: fixed;
  z-index: 100;
}
.menu {
  z-index: 100;
  width: 24.5625rem;
  margin-top: -4.375rem;
  display: flex;
  justify-content: space-between;
  align-items: center;
  padding-left: 2rem;
  padding-right: 2rem;
  height: 4.375rem;
  background: #fff;
}
.menu__item-logo {
  height: 2.917rem;
}

.menu__item-button {
  background: none;
  border: none;
  margin: 0;
  padding: 0;
}
.menu__item-image {
  width: 2.375rem;
  height: 2.375rem;
}

.menu__item-container {
  display: flex;
  align-items: baseline;
  gap: 0.8125rem;
}

.menu__item-text {
  color: var(--blue2, #149ed7);
  font-family: 'Yaldevi';
  font-size: 1.7302rem;
  font-style: normal;
  font-weight: 600;
  line-height: 67%;
  letter-spacing: 0.1384rem;
}
.menu__item-text_home {
  color: var(--blue1, #1d3d8f);
}
.menu__item-underline {
  border-bottom: solid 0.5625rem #1d3d8f;
  width: 8.125rem;
  border-radius: 1.25rem;
}
.sidebar {
  width: 18.0625rem;
  background-color: white;
  min-height: 45rem;
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
  gap: 3.8125rem;
  border-radius: 0.375rem 0rem 0rem 0.375rem;
  box-shadow: 0rem 0.25rem 1.3rem 0rem rgba(16, 96, 217, 0.25);
}

.visible {
  transform: translateX(0);
}

.hidden {
  display: none;
}

.sidebar-logo {
  width: 10.6875rem;
}

.nav__list {
  display: flex;
  flex-direction: column;
  align-items: flex-start;
  gap: 1.25rem;
  align-self: flex-start;
}

.nav__item {
  display: flex;
  gap: 0.625rem;
}

.link {
  text-decoration: none;
}
.link:hover {
  cursor: pointer;
}
.nav__item-picture {
  width: 1.75rem;
  display: flex;
  align-items: center;
  justify-content: center;
}
.nav__item-text {
  color: #000;
  font-family: 'Baloo 2';
  /* font-size: 1.25rem; */
  font-style: normal;
  font-weight: 400;
  line-height: normal;
}
.sidebar__line {
  width: 12.9389rem;
  border-bottom: solid 0.0625rem #149ed7;
}

.sidebar__social {
}

.sidebar-title {
  margin-top: 2.25rem;
  margin-bottom: 1.1875rem;
  color: var(--black1, #2f2f2f);
  font-family: 'Baloo 2';
  /* font-size: 1.25rem; */
  font-weight: 500;
}

.social-list {
  display: flex;
  gap: 1.6875rem;
  align-items: center;
}

.sidebar-config {
  margin-top: 1.75rem;
  /* /* height: 55px; */
  /* display: flex; */
  /* flex-direction: column;  */
}
.config-title {
  margin-top: 0;
  margin-bottom: 0.5625rem;
}

.sidebar__button {
  color: var(--white, #fff);
  font-family: 'Baloo 2';
  font-size: 1.25rem;
  font-weight: 700;
  display: flex;
  height: 3.1875rem;
  padding: 0.625rem;
  width: 100%;
  justify-content: center;
  align-items: center;
  border-radius: 0.375rem;
  background: var(--blue1, #1d3d8f);
  border: none;
}

.range-slider {
  background-color: #149ed7;
  border: 0.0313rem solid #149ed7;
  height: 0.0313rem;
  cursor: pointer;
  appearance: none;
  width: 12.5rem;
  margin-left: 0.4375rem;
  z-index: 1000;
}

.slider-container {
  width: 13.125rem;
}

.sidebar-config .range-slider::-webkit-slider-thumb {
  -webkit-appearance: none;
  width: 1.25rem;
  height: 1.25rem;
  background-color: #149ed7;
  border-radius: 50%;
  cursor: pointer;
  z-index: 1000;
  position: relative;
}

.circle {
  width: 0.625rem;
  height: 0.625rem;
  background-color: #1d3d8f;
  border-radius: 50%;
  position: absolute;
  z-index: 1;
  font-size: 10px;
  margin-bottom: -18px;
}

.circle.start {
  left: 0;
  transform: translateX(-50%);
  margin-left: 3.5625rem;
}

.circle.center {
  left: 50%;
  transform: translateX(-50%);
}

.circle.end {
  right: 0;
  transform: translateX(50%);
  margin-right: 3.3125rem;
}

@media screen and (max-width: 52.0625rem) {
  .sidebar {
    min-height: 50.125rem;
    padding: 3.7744rem 2.5313rem;
  }
}
</style>
