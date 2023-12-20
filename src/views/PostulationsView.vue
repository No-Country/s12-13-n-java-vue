<script setup>
import router from '@/router'
import SectionHeader from '../components/SectionHeader.vue'
import { ref, onMounted } from 'vue'
import '@vuepic/vue-datepicker/dist/main.css'
import FooterPage from '@/components/SectionFooter.vue'
import NavigationDashboard from '../components/NavigationDashboard.vue'
import axios from '@/plugins/axios'

const token = localStorage.getItem('token')
const headers = {
  Authorization: `Bearer ${token}`
}

let cards = ref(null)

const fetchCards = async () => {
  await axios.get('task/published', { headers }).then((response) => {
    console.log('response:', response.data.content)
    cards.value = response.data.content.filter((card) => card.id > 0)
  })
}

onMounted(() => {
  fetchCards()
})
</script>
<template>
  <main>
    <SectionHeader />
    <NavigationDashboard />

    <div class="wrapper">
      <section class="container" style="margin-bottom: 16px">
        <p>Publicaciones activas <strong>/ Postulaciones</strong></p>
        <section class="card" :style="{ boxShadow: `0px 5px 0px 0px ${$route.query.color} inset` }">
          <div>
            <h3 class="card__category">{{ $route.query.category }}</h3>
            <p class="card__title">{{ $route.query.taskTitle }}</p>
          </div>
        </section>
        <section class="posts-container">
          <div class="post">
            <div class="post-info">
              <div class="circle-container">
                <img src="../assets/images/post-avatar.svg" alt="avatar" class="avatar" />
              </div>
              <div class="profile__info">
                <p class="profile__name">Florencia R.</p>
                <p class="profile__role">Contratador</p>
              </div>

              <div class="ranking">
                <v-rating
                  hover
                  half-increments
                  :length="5"
                  :size="22"
                  :model-value="3"
                  color="#1D3D8F"
                  active-color="#1D3D8F"
                />
                <p class="ranking-text">21 calificaciones</p>
              </div>
            </div>
            <button class="post-button link">Contratar</button>
          </div>
        </section>
        <section class="posts-container">
          <div class="post">
            <div class="post-info">
              <div class="circle-container">
                <img src="../assets/images/post-avatar.svg" alt="avatar" class="avatar" />
              </div>
              <div class="profile__info">
                <p class="profile__name">Florencia R.</p>
                <p class="profile__role">Contratador</p>
              </div>

              <div class="ranking">
                <v-rating
                  hover
                  half-increments
                  :length="5"
                  :size="22"
                  :model-value="3"
                  color="#1D3D8F"
                  active-color="#1D3D8F"
                />
                <p class="ranking-text">21 calificaciones</p>
              </div>
            </div>
            <button class="post-button link">Contratar</button>
          </div>
        </section>
        <button class="button-back link post-button" @click="router.back()">
          Volver a publicaciones
        </button>
        <button class="button-finalize link post-button">Finalizar publicación</button>
      </section>
    </div>
    <section class="section-blog">
      <!-- contenido de la segunda tarjeta -->
    </section>
    <FooterPage />
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

.link:hover {
  cursor: pointer;
}

.button:hover {
  cursor: pointer;
}

.active {
  color: #149ed7;
}
.container {
  width: 100%;
  height: 592px;
  overflow: auto;
  display: flex;
  flex-direction: column;
  gap: 10px;
}

::-webkit-scrollbar {
  height: 0px;
  width: 1px;
  border: 0px solid #fff;
}

.wrapper {
  overflow: auto;
  background-color: #a9b8de;
}
.tasks-container {
  display: flex;
  flex-direction: column;
  gap: 10px;
}

.cards-container {
  background-color: #a9b8de;
  padding-bottom: 30px;
}

.card {
  display: flex;
  min-height: 73px;
  min-width: 361px;
  padding: 16px;
  flex-direction: column;
  align-items: flex-start;
  border-radius: 6px;
  background: #fff;
  box-shadow: 0px 5px 0px 0px #4dc9ff inset;
  overflow: hidden;
}

.card__category {
  color: var(--blue1, #1d3d8f);
  font-family: 'Baloo 2';
  font-weight: 600;
}

.card__title {
  white-space: nowrap;
  font-family: 'Baloo 2';
  font-weight: 400;
  margin-bottom: 8px;
}

.posts-container {
  display: flex;
  min-height: 156px;
  min-width: 361px;
  padding: 16px;
  flex-direction: column;
  align-items: flex-start;
  border-radius: 6px;
  background: #fff;
  overflow: hidden;
  gap: 10px;
}

.post {
  display: flex;
  flex-direction: column;
  gap: 19px;
  width: 100%;
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

.post-info {
  display: flex;
  justify-content: space-between;
  width: 100%;
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

.ranking {
  margin-top: -10px;
  display: flex;
  flex-direction: column;
  gap: 10px;
}

.profile__name {
  font-family: 'Baloo 2';
  font-weight: 500;
}

.profile__role {
  color: var(--blue2, #1d3d8f);
  font-family: 'Baloo 2';
  font-weight: 400;
}
.profile__info {
  display: flex;
  flex-direction: column;
  justify-content: center;
}

.post-button {
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
  background: #149ed7;
  border: none;
}
.button-back {
  margin-top: 6px;
  background: var(--blue1, #1d3d8f);
}

.button-finalize {
  background: var(--disabled, #c7c7c7);
}

.ranking-text {
  font-size: 16px;
  font-family: 'Baloo 2';
  font-weight: 500;
}

@media screen (min-width: 768px){
  .wrapper{
    font-size: 14px;
  }
  .posts-container{
    font-size: 14px;
  }

  .ranking{
    font-size: 14px; 
  }

  .section-blog{
    font-size: 14px;
  }
}
</style>
