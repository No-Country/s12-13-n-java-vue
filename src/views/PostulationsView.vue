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

    <div class="cards-container">
      <section class="container" style="margin-bottom: 16px">
        <p>Publicaciones activas <strong>/ Postulaciones</strong></p>
        <section class="card">
          <div>
            <h3 class="card__category">Kinesiólogía</h3>
            <p class="card__title">Sesiones semanales</p>
          </div>
        </section>
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
  height: 653px;
  overflow: auto;
}

::-webkit-scrollbar {
  height: 0px;
  width: 1px;
  border: 0px solid #fff;
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
  min-height: 134px;
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
</style>
