<script setup>
import SectionHeader from '@/components/SectionHeader.vue'
import { ref, onMounted } from 'vue'
import '@vuepic/vue-datepicker/dist/main.css'
import FooterPage from '@/components/SectionFooter.vue'
import JobCard from '@/components/JobCardWorker.vue'
import axios from '@/plugins/axios'

const token = localStorage.getItem('token')
const user = localStorage.getItem('userData')
const headers = {
  Authorization: `Bearer ${token}`
}
import router from '@/router'
const chatworker = () => {
  router.push({ name: 'chatworker' })
}
const history = () => {
  router.push({ name: 'history' })
}



const activeItems = ref([false, false, false])
const postulations = ref([]);
let cards = ref(null)

const toggleNavItem = (index) => {
  activeItems.value[index] = !activeItems.value[index]
}


const fetchPostulations = async () => {
  try {
    let dataUser = JSON.parse(user);
    const worker_id = dataUser['id_worker'];
    const response = await axios.get('postulations/all/' + worker_id, { headers });

    if (response.data && response.data.response && response.data.response.content) {
      const idTasks = response.data.response.content.map(postulation => postulation.idTask);
      console.log('Task IDs:', idTasks);
      postulations.value = response.data.response.content;
    }
  } catch (error) {
    console.error('Error fetching postulations:', error);
  }
};

const fetchCards = async () => {
  try {
    const response = await axios.get('task/published', { headers });
    console.log('response:', response.data.content);

    cards.value = response.data.content.map((card) => {
      const postulation = postulations.value.find((postulation) => postulation.idTask === card.id);
      return {
        ...card,
        postulated: postulation !== undefined,
      };
    });
  } catch (error) {
    console.error('Error fetching cards:', error);
  }
};

onMounted(async () => {
  await fetchPostulations();
  fetchCards();
});

</script>


<template>
  <main>
    <SectionHeader />
    <nav class="nav">
      <ul class="nav__list">
        <li class="nav__item link" @click="toggleNavItem">
          <div class="nav__item-container">
            <img
              class="nav__item-image"
              :class="{ active: isActive }"
              src="../assets/images/home-icon.svg"
            />
            <p class="nav__item-text" :class="{ active: isActive }">Inicio</p>
          </div>
        </li>
        <li class="nav__item link" @click="toggleNavItem" @click.prevent="chatworker">
          <div class="nav__item-container">
            <img
              class="nav__item-image"
              :class="{ active: !isActive }"
              src="../assets/images/comment-icon.svg"
            />
            <p class="nav__item-text" :class="{ active: !isActive }">Chats</p>
          </div>
        </li>
        <li class="nav__item link" @click="toggleNavItem" @click.prevent="history">
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
    <div class="cards-container">
      <section class="container">
        <div class="mt-5 mb-5">
          <h3>Publicaciones activas</h3>
        </div>         
        <div v-if="cards && cards.length" class="tasks-container mt-5 mb-5">
          <div v-for="card in cards" :key="card.id">
  
            <JobCard
              :taskTitle="card.taskTitle ?? ''"
              :taskDate="(card.taskDate ? card.taskDate.slice(0, 10).replace(/-/g, '/') : '') ?? ''"
              :category="(card.occupations && card.occupations[0] && card.occupations[0].occupationName) ?? ''"
              :description="card.description ?? ''"
              :price="card.price ?? ''"
              :color="card.occupations[0].color"
              :currencyType="card.currencyType ?? ''"
              :address="(card.address && card.address.street) ?? ''"
              :id="card.id ?? 0"
              :postulated="card.postulated ?? false"
            ></JobCard>

          </div>
        </div>
        <div v-else class="mp-3 d-flex justify-content-center align-items-center">
          <h3> sin resultados</h3>
        </div>
      </section>


    </div>

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
}

.popup {
  position: fixed;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
  width: 391px;
  height: 736px;
  background-color: rgba(0, 0, 0, 0.5);
  z-index: 100;
  display: flex;
  justify-content: center;
  align-items: center;
}

.popup__container {
  border-radius: 12px;
  background: var(--white, #fff);
  padding: 26px;
  height: 100%;
  width: 100%;
  display: flex;
  flex-direction: column;
}

.popup__container {
  display: flex;
  flex-direction: column;
  gap: 19px;
}
.popup__header {
  display: flex;
  justify-content: space-between;
}
.popup__close {
  border: none;
  vertical-align: middle;
  width: 27px;
  height: 27px;
  background-color: transparent;
}
.popup__title {
  color: var(--black1, #2f2f2f);
  font-family: 'Baloo 2';
  font-size: 22px;
  font-weight: 600;
}
.form {
  display: flex;
  flex-direction: column;
  gap: 19px;
}

.form__labelBox {
  display: flex;
  flex-direction: column;
}

.form__select,
.form__input {
  border-radius: 6px;
  padding-left: 15px;
  padding-top: 5px;
  padding-bottom: 5px;
  border: 1px solid var(--gray1, #4f4f4f);
  background-color: #eee;
  width: 100%;
}
.form__select {
  appearance: none;
}

.select-currency {
  width: 97.714px;
}

.form__labelText,
.form__optionText {
  font-family: 'Baloo 2';
  font-size: 20px;
  font-weight: 500;
}

.form__textarea {
  border-radius: 6px;
  border: 1px solid var(--gray1, #4f4f4f);
  padding: 12px 17px;
  background-color: #eee;
  height: 115px;
}

.form__submit-button {
  height: 51px;
  padding: 10px;
  justify-content: center;
  align-items: center;
  border-radius: 6px;
  color: var(--white, #fff);
  border: none;
  background: var(--blue1, #1d3d8f);
  font-family: 'Baloo 2';
  font-size: 20px;
  font-weight: 700;
}

.input-location {
  position: relative;
}
.location-img {
  position: absolute;
  width: 16px;
  height: 21px;
  right: 36px;
  margin-top: 38px;
  z-index: 10;
}
.date-picker {
  border-radius: 6px;
  border: 1px solid var(--gray1, #4f4f4f);
  background-color: #eee;
  background: var(--blue1, #1d3d8f);
}

.shevron {
  width: 23.2px;
  height: 24px;
  position: absolute;
  right: 36px;
  margin-top: 38px;
}

.labelBox-container {
  display: flex;
  gap: 34px;
  justify-content: space-between;
}
.v-enter-active,
.v-leave-active {
  transition: transform 0.3s ease-in-out;
}

.v-enter-from,
.v-leave-to {
  transform: translateY(100%);
}

.z-index--10 {
  z-index: -10;
}

.isotipo{
    display:flex;
    align-items: center;
    margin: 0;
}

.navbar-trabajador{
    align-items: center;
    align-content: center;
    display: flex;
    justify-content: center;
}

.referencias{
    align-content: right;
    display: flex;
    justify-content: right;
}

.descripcion{
    text-align: justify;
    text-align-last: justify;
    line-height: 1.5;
    max-width: 300px;
    margin: 0 auto;
}

.kinesiologia{
    color:#1D3D8F;
    text-decoration: solid;
}

.perfil-florencia{
    display:flex;
    align-items: center;
}

.info-florencia{
    margin-right: auto;
    
}

.valoracion{
    display: flex;
    flex-direction: column;
    align-items:flex-end;
    
}

.calificaciones{
    margin-left: auto;
}

.contratador{
    color:#149ED7;
}

.fondo-card{
    background-color: #D8DFF2;
}

.container-description h3 {
    color: #fff;
    margin-top: 48px;
    font-size: 20px;
  }
 
  .container-description {
    display: flex;
    background-color: #1D3D8F;
    flex-direction: column;
    align-items: center;
    text-align: center
  }

  .foot {
    display: flex;
    flex-direction: column;
  }

  @media screen and(min-width: 768px){
  .cards-container{
    font-size: 14px;
  }
  .nav{
    font-size: 14px;
  }
    .foot{
      display: flex;
      flex-direction: row;
    }
  }
</style>
