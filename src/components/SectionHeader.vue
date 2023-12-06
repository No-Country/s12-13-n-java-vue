<script setup></script>

<template>
  <div class="placeholder"></div>
  <section class="container">
    <header>
      <nav>
        <ul class="nav__list">
          <li class="nav__item">
            <img class="nav__item-logo" src="../assets/logo.svg" />
          </li>
          <li class="nav__item">
            <button class="nav__itme-button" @click="toggleSidebar">
              <img class="nav__item-image" src="../assets/images/menu-icon.svg" />
            </button>
          </li>
        </ul>
      </nav>
    </header>
    <section class="side-bar" :class="{ visible: isVisible }">
      <button class="button button-search">Поиск города</button>
      <div class="side-bar-background"></div>
      <div class="weather-content">
        <!-- <img
              class="weather-icon"
              src="src/images/wheather-icon-snow.svg"
              alt="иконка погоды, снег"
            /> -->
        <div class="weather-icon"></div>
        <p class="weather-title">1 <span class="title-celcium">°C</span></p>
        <h2 class="weather-subtitle">Cнег</h2>
        <p class="weather-description">Ощущается как -3 °C</p>
        <div class="weather-date">
          <p class="weather-day">Сегодня</p>
          <p class="weather-date-exact">Вс, 13 мар</p>
        </div>
        <div class="weather-location">
          <img
            class="icon-location"
            src="src/images/icon-location.svg"
            alt="иконка местоположения"
          />
          <p class="location-name">Москва</p>
        </div>
      </div>
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
      const sideBar = document.querySelector('.side-bar')
      console.log('sideBar:', sideBar)

      console.log('isVisible:', this.isVisible)
      this.isVisible = !this.isVisible
    }
  }
}
</script>

<style scoped>
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
.nav__list {
  z-index: 100;
  width: 393px;
  margin-top: -70px;
  display: flex;
  justify-content: space-between;
  align-items: center;
  padding-left: 32px;
  padding-right: 32px;
  height: 53px;
  background: #fff;
}
.nav__item-logo {
  height: 46.672px;
}

.nav__itme-button {
  background: none;
  border: none;
  margin: 0;
  padding: 0;
}
.nav__item-image {
  width: 38px;
  height: 38px;
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

.side-bar {
  width: 200px;
  background-color: white;
  height: 720px;
  overflow-x: visible;
  overflow-y: visible;
  position: absolute;
  right: 0;
  transform: translateX(100%);
  transition: transform 0.3s ease;
}

.visible {
  transform: translateX(0);
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
  .side-bar-search {
    /* width: 289px; */
    height: 667px;
    padding: 40px 50px 0 27px;
  }
  .side-bar {
    /* width: 289px; */
    height: 667px;
    padding: 30px 31px 35px;
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
  .side-bar-background {
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
