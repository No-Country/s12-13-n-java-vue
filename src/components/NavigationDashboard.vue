<script setup>
import router from '@/router'

const chat = () => {
  router.push({ name: 'Chat' })
}

const dashboard = () => {
  router.push({ name: 'dashboard' })
}
</script>

<template>
  <nav class="nav">
    <ul class="nav__list">
      <li class="nav__item link" @click.prevent="dashboard">
        <div class="nav__item-container">
          <img
            class="nav__item-image"
            :class="{ active: isActive }"
            src="../assets/images/home-icon.svg"
          />
          <p class="nav__item-text" :class="{ active: isActive }">Inicio</p>
        </div>
      </li>
      <li class="nav__item link" @click.prevent="chat">
        <div class="nav__item-container">
          <img
            class="nav__item-image"
            :class="{ active: !isActive }"
            src="../assets/images/comment-icon.svg"
          />
          <p class="nav__item-text" :class="{ active: !isActive }">Chats</p>
        </div>
      </li>
      <li class="nav__item link">
        <div class="nav__item-container">
          <img
            class="nav__item-image"
            :class="{ active: !isActive }"
            src="../assets/images/history-icon.svg"
          />
          <p class="nav__item-text" :class="{ active: !isActive }">Historial</p>
        </div>
      </li>
    </ul>
  </nav>
</template>

<style scoped>
p {
  margin: 0;
  padding: 0;
}
.nav {
  padding: 8px 16px;
  display: flex;
  align-items: center;
  justify-content: center;
}
.nav__list {
  display: flex;
  justify-content: center;
  gap: 16px;
}
.nav__item {
  display: flex;
  flex-direction: column;
  gap: 10px;
}
.nav__item-image {
  width: 35px;
  height: 31px;
  color: #3960c2;
}

.nav__item-container {
  display: flex;
  align-items: baseline;
  flex-direction: column;
  align-items: center;
  gap: 13px;
  width: 120.333px;
  color: #3960c2;
}

.nav__item-text {
  font-family: 'Yaldevi';
  font-size: 27.683px;
  font-style: normal;
  font-weight: 600;
  line-height: 67%;
  letter-spacing: 2.215px;
}
.nav__item-text_home {
  color: var(--blue1, #3960c2);
}
</style>
