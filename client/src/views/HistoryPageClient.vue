<script setup>
import SectionHeader from '@/components/SectionHeader.vue';
import { ref, onMounted } from 'vue';
import '@vuepic/vue-datepicker/dist/main.css';
import FooterPage from '@/components/SectionFooter.vue';
import JobCard from '@/components/JobCardStatus.vue';
import axios from '@/plugins/axios';
import router from '@/router'
const dashboard = ()=>{
    router.push({ name: 'dashboard' })
}

const chat = () => {
  router.push({ name: 'Chat' })
}
const token = localStorage.getItem('token');
const user = localStorage.getItem('userData');
const headers = {
  Authorization: `Bearer ${token}`
};

const activeItems = ref([false, false, false]);
const cards = ref(null);

const stateTranslations = {
  PUBLISHED: 'Publicado', 
  INPROGRESS: 'en curso',
  COMPLETED: 'finalizado', 
};


const filters = ref({
  states: 'PUBLISHED'
});

const filtersButtonVisible = ref(false);


const toggleNavItem = (index) => {
  activeItems.value[index] = !activeItems.value[index];
};

const fetchTasks = async (status) => {
  try {
    const dataUser = JSON.parse(user);
    const client_id = dataUser['id_client'];
  const response = await axios.get('task/client/', {
        headers,
        params: {
          id: client_id,
          status: status
        }
  });


    console.log(response.data.content);
    cards.value = response.data.content;
  } catch (error) {
    console.error('Error fetching task:', error);
    throw error;
  }
};

const updateFilters = async () => {
  const selectedStates = filters.value.states;
  await fetchTasks(selectedStates);
  filtersButtonVisible.value = false;
};

onMounted(async () => {
  await fetchTasks(filters.value.states);
});
</script>


<template>
  <main>
    <SectionHeader />
    <nav class="nav">
      <ul class="nav__list">
        <li class="nav__item link" @click="toggleNavItem" @click.prevent="dashboard">
          <div class="nav__item-container">
            <img
              class="nav__item-image"
              :class="{ active: !isActive }"
              src="../assets/images/home-icon.svg"
            />
            <p class="nav__item-text" :class="{ active: !isActive }">Inicio</p>
          </div>
        </li>
        <li class="nav__item link" @click="toggleNavItem" @click.prevent="chat">
          <div class="nav__item-container">
            <img
              class="nav__item-image"
              :class="{ active: !isActive }"
              src="../assets/images/comment-icon.svg"
            />
            <p class="nav__item-text" :class="{ active: !isActive }">Chats</p>
          </div>
        </li>
        <li class="nav__item link" @click="toggleNavItem">
          <div class="nav__item-container">
            <img
              class="nav__item-image"
              :class="{ active: isActive }"
              src="../assets/images/history-icon.svg"
            />
            <p class="nav__item-text" :class="{ active: isActive }">Calendario</p>
          </div>
        </li>
      </ul>
    </nav>

    <div class="cards-container">
      <section class="container">
        <div>
          <div class="mt-3 mb-3">
            <h3>Historial</h3>
          </div>
          <!-- FILTRO DE ESTADO DE TASKS -->
          <div class="mt-2 mb-3">
              <button @click="filtersButtonVisible = !filtersButtonVisible" class="expand-button link">filtrar</button>
              <div v-if="filtersButtonVisible" class="filters">
                <div v-for="state in Object.keys(stateTranslations)" :key="state">
                  <input
                    class="form-check-input"
                    type="radio"
                    :value="state"
                    v-model="filters.states"
                    :class="{ 'selected-state': filters.states === state }"
                  />
                  <label class="form-check-label">{{ stateTranslations[state] }}</label>
                </div>
                <button class="expand-button link" @click="updateFilters">Aplicar</button>
              </div>
            </div>

          <!-- CARDS-->
          <div v-if="cards && cards.length" class="tasks-container mt-5 mb-5">
          <div v-for="card in cards" :key="card.id">
            <JobCard
              :taskTitle="card.taskTitle ?? ''"
              :taskDate="(card.taskDate ? card.taskDate.slice(0, 10).replace(/-/g, '/') : '') ?? ''"
              :category="(card.occupations && card.occupations[0] && card.occupations[0].occupationName) ?? ''"
              :description="card.description ?? ''"
              :color="card.occupations[0].color"
              :price="card.price ?? ''"
              :currencyType="card.currencyType ?? ''"
              :address="(card.address && card.address.street) ?? ''"
              :id="card.id"
            ></JobCard>
          </div>

        </div>
        <div v-else class="mp-3 d-flex justify-content-center align-items-center">
          <h3> sin resultados</h3>
        </div>
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

.link {
  cursor: pointer;
  border: none;
  border-radius: 6px;
}

.link:hover {
  cursor: pointer;
}

.selected-state {
  background-color: var(--blue1, #1d3d8f);
  color:#fff;
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

.expand-button {
  color: var(--white, #fff);
  font-family: 'Baloo 2';
  font-size: 16px;
  font-weight: 700;
  display: flex;
  height: 51px;
  padding: 10px;
  min-width: 123px;
  justify-content: center;
  align-items: center;
  background: var(--blue1, #1d3d8f);
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

  @media (min-width: 768px){
    .foot{
      display: flex;
      flex-direction: row;
    }
  }
</style>
