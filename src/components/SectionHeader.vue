<script setup></script>

<template>
  <div class="placeholder"></div>
  <section class="container">
    <header>
      <nav class="menu">
        <li class="menu__item">
          <img class="menu__item-logo" src="../assets/logo.svg" />
        </li>
        <li class="menu__item">
          <button class="menu__item-button" @click="toggleSidebar">
            <img class="menu__item-image" src="../assets/images/menu-icon.svg" />
          </button>
        </li>
      </nav>
    </header>
    <section class="sidebar visible" :class="{ visible: isVisible }">
      <img class="sidebar-logo" src="../assets/logo.svg" />
      <ul class="nav__list">
        <li class="nav__item">
          <a class="nav__item nav__item-link">
            <div class="nav__item-picture">
              <img class="nav__item-icon" src="../assets/images/page-icon.svg" alt="page-icon" />
            </div>
            <p class="nav__item-text">Categorías</p>
          </a>
        </li>
        <li class="nav__item">
          <a class="nav__item nav__item-link">
            <div class="nav__item-picture">
              <img class="nav__item-icon" src="../assets/images/blog-icon.svg" alt="blog-icon" />
            </div>
            <p class="nav__item-text">Blog</p>
          </a>
        </li>
        <li class="nav__item">
          <a class="nav__item nav__item-link">
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
          <a class="nav__item nav__item-link">
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
      <section class="sidebar__social">
        <div class="sidebar__line"></div>
        <h3 class="social-title">REDES</h3>
        <ul class="social-list">
          <li class="social-item">
            <a class="social-link"
              ><img src="../assets/images/facebook-icon.svg" alt="facebook" class="social-image"
            /></a>
          </li>
          <li class="social-item">
            <a class="social-link"
              ><img src="../assets/images/instagram-icon.svg" alt="instagram" class="social-image"
            /></a>
          </li>
          <li class="social-item">
            <a class="social-link"
              ><img src="../assets/images/tiktok-icon.svg" alt="tiktok" class="social-image"
            /></a>
          </li>
          <li class="social-item">
            <a class="social-link"
              ><img src="../assets/images/twitter-icon.svg" alt="twitter" class="social-image"
            /></a>
          </li>
        </ul>
      </section>
      <section class="sidebar-size">
        <h3 class="social-title">REDES</h3>
      </section>
      <div class="sidebar__line"></div>
      <div class="sidebar__line"></div>
    </section>
  </section>
</template>

<script>
export default {
  data() {
    return {
      isVisible: false
    }
  },
  methods: {
    toggleSidebar() {
      const sideBar = document.querySelector('.sidebar')
      console.log('sideBar:', sideBar)

      console.log('isVisible:', this.isVisible)
      this.isVisible = !this.isVisible
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
  position: absolute;
  right: 0;
  transform: translateX(100%);
  transition: transform 0.3s ease;
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: space-between;
  gap: 61px;
}

.visible {
  transform: translateX(0);
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

.nav__item-link {
  text-decoration: none;
}

.nav__item-link:hover {
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

.social-title {
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

.button-search {
  width: 180px;
  border: 2px solid var(--hint, #acacac);
  height: 50px;
  border-radius: 10px;
  margin: 42px;
  margin-bottom: 0;
  font-family: Rubik;
  font-size: 18px;
  font-style: normal;
  font-weight: 700;
}

.button-find {
  width: 97px;
  height: 50px;
  border: 2px solid var(--hint, #acacac);
  border-radius: 10px;
  margin-left: 22px;
  font-family: Rubik;
  font-size: 18px;
  font-style: normal;
  font-weight: 700;
}

.button-close {
  background-image: url('../../images/button-close.svg');
  background-repeat: no-repeat;
  width: 26px;
  height: 26px;
  border: none;
  background-color: transparent;
  position: absolute;
  right: 45px;
}

.form-search {
  display: flex;
  margin-top: calc(118px - 45px);
  justify-content: space-between;
}

.search-input {
  width: 250px;
  height: 50px;
  border-radius: 10px;
  border: 2px solid var(--hint, #acacac);
  background: var(--panel-light, #fff);
  padding-left: 44px;
}

.search-input:focus {
  outline-color: #acacac;
}

.icon-search {
  position: absolute;
  margin-top: 14px;
  margin-left: 17px;
}

.weather-icon {
  margin-top: 24px;
  background-image: url('/src/images/wheather-icon-snow.svg');
  background-repeat: no-repeat;
  background-size: contain;
  width: 200px;
  height: 200px;
}

.weather-date-exact {
  margin: 0;
}

.icon-location {
  width: 40px;
  height: 40px;
}

.location-name {
  margin: 0;
}

@media screen and (max-width: 833px) {
  .sidebar-search {
    /* width: 289px; */
    height: 667px;
    padding: 40px 50px 0 27px;
  }
  .sidebar {
    /* width: 289px; */
    height: 802px;
    padding: 60.39px 40.5px;
  }
  .weather-icon {
    margin-top: 20px;
    padding-top: 16px;
    margin-bottom: -17px;
    overflow: hidden;
    height: 180px;
    /* width: 200px; */
    /* width: 100%; */
    width: 67%;
    background-position: center;
    background-size: cover;
    object-fit: cover;
    /* padding-bottom: -2px; */
    /* clip-path: inset(50px 50px 0 0); */
  }
  .button-search {
    margin: 0px;
  }
  .weather-location {
    padding-bottom: 35px;
  }
  .sidebar-background {
    top: 67px;
    /* width: 273px; */
    /* left: -89px; */
  }
  .button-close {
    right: 50px;
  }
  .search-input {
    width: 298px;
  }
  .icon-search {
    margin-left: -32px;
  }
  .button-find {
    margin-left: 22px;
  }
}
</style>
