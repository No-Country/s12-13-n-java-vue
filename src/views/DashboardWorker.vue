<script setup>
import SectionHeader from '../components/SectionHeader.vue'
import { ref, onMounted } from 'vue'
import Datepicker from '@vuepic/vue-datepicker'
import '@vuepic/vue-datepicker/dist/main.css'
import FooterPage from '@/components/SectionFooter.vue'
import JobCard from '../components/JobCardWorker.vue'
import useFormContratador from '@/stores/formContratador'
import { categorias, currencies } from '../utils/constants'
import axios from '@/plugins/axios'
const token = localStorage.getItem('token')
const headers = {
  Authorization: `Bearer ${token}`
}

const date = ref()
const store = useFormContratador()

const activeItems = ref([false, false, false])
const isOpen = ref(false)

let taskTitle = ref('')
let category = ref('')
let currency = ref('')
let taskDescription = ref('')
let taskLocation = ref('')
let precio = ref(0)
let cards = ref(null)

// let clientID = ref(0)

const toggleNavItem = (index) => {
  activeItems.value[index] = !activeItems.value[index]
}

const openPopup = () => {
  isOpen.value = true
}

const closePopup = () => {
  isOpen.value = false
}

const onSubmit = async () => {
  await store.submit(
    taskTitle.value,
    taskDescription.value,
    precio.value,
    currency.value,
    [
      {
        occupationName: category.value
      }
    ],
    date.value,
    {
      id: 1
    },
    {
      street: taskLocation.value
    }
  )
  fetchCards()
  closePopup()
}

const fetchCards = async () => {
  await axios.get('task/published', { headers }).then((response) => {
    console.log('response:', response.data.content)
    cards.value = response.data.content
  })
}

// const fetchClient = async () => {
//   await axios.get('auth/details', { headers }).then((response) => {
//     console.log('response:', response.data.content)
//     cards.value = response.data.content.filter((card) => card.username === 'abuelita')
//   })
// }

onMounted(() => {
  // fetchClient()
  fetchCards()
})

const onCardDelete = () => {
  fetchCards()
}
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
        <li class="nav__item link" @click="toggleNavItem">
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
        <p>Publicaciones activas</p>
        <div v-if="cards && cards.length" class="tasks-container">
          <div v-for="card in cards" :key="card.id">
            <JobCard
            
              :taskTitle="card.taskTitle"
              :taskDate="card.taskDate.slice(0, 10).replace(/-/g, '/')"
              :category="card.occupations[0].occupationName"
              :description="card.description"
              :price="card.price"
              :currencyType="card.currencyType"
              :address="card.address.street"
              :id="card.id"
            >
            </JobCard>
          </div>
        </div>

        <div v-else>
           Handle the case when cards is null or empty 
          No cards available.
        </div>

      </section>
      <section class="modal-info">
        <p class="modal-info__text" v-if="!cards">
          Crea tu primera publicación y <br />
          conecta con trabajadores
        </p>
        <button class="modal-info__button link" @click="openPopup">Crear publicación</button>
      </section>
    </div>

    <section class="section-blog">
      <!-- contenido de la segunda tarjeta -->
    </section>
    <Transition>
      <modal class="popup" v-if="isOpen" :class="{ open: isOpen }">
        <div class="popup__container">
          <div class="popup__header">
            <h3 class="popup__title">Crear nueva publicación</h3>
            <button class="popup__close button" @click="closePopup">
              <img src="../assets/images/close-button-icon.svg" alt="Button Image" />
            </button>
          </div>
          <form class="form" @submit.prevent="onSubmit({ taskTitle, taskDescription })">
            <div class="form__labelBox">
              <label htmlFor="eventName" class="form__labelText"> Elige el tipo de servicio </label>
              <select
                class="form__select"
                id="eventName"
                name="eventName"
                v-model="category"
                required
              >
                <option class="form__optionText">Categorias</option>
                <option
                  class="form__optionText"
                  v-for="(category, index) in categorias"
                  :key="index"
                >
                  {{ category }}
                </option>
              </select>
              <img src="../assets/images/shevron.svg" alt="shevron" class="shevron" />
            </div>
            <div class="form__labelBox">
              <label htmlFor="taskTitle" class="form__labelText">Título</label>
              <input
                class="form__input"
                name="eventName"
                placeholder="Escribe un título"
                required
                :value="taskTitle"
                @input="(e) => (taskTitle = e.target.value)"
              />
            </div>
            <div class="form__labelBox">
              <label htmlFor="eventName" class="form__labelText">Descripción</label>
              <textarea
                class="form__textarea"
                type="select"
                name="eventName"
                :value="taskDescription"
                @input="(e) => (taskDescription = e.target.value)"
                placeholder="Agrega una descripción con los
detalles de tu trabajo"
                required
              ></textarea>
            </div>
            <div class="labelBox-container">
              <div class="form__labelBox">
                <label htmlFor="eventName" class="form__labelText">Precio</label>
                <input
                  class="form__input"
                  name="eventName"
                  placeholder="$"
                  type="number"
                  :value="precio"
                  @input="(e) => (precio = Number(e.target.value))"
                />
              </div>
              <div class="form__labelBox">
                <label htmlFor="eventName" class="form__labelText">Moneda</label>
                <select
                  class="form__select select-currency"
                  id="eventName"
                  name="eventName"
                  v-model="currency"
                >
                  <option
                    class="form__optionText"
                    v-for="(currency, index) in currencies"
                    :key="index"
                  >
                    {{ currency }}
                  </option>
                </select>
                <img src="../assets/images/shevron.svg" alt="shevron" class="shevron" />
              </div>
            </div>

            <div class="form__labelBox">
              <label htmlFor="eventName" class="form__labelText">Ubicación</label>
              <img src="../assets/images/location-icon.svg" alt="" class="location-img" />

              <input
                class="form__input input-location"
                name="eventName"
                placeholder="Ingresa tu dirección"
                :value="taskLocation"
                @input="(e) => (taskLocation = e.target.value)"
              />
            </div>
            <div class="form__labelBox">
              <label htmlFor="eventName" class="form__labelText">Fecha de la tarea</label>
              <Datepicker v-model="date" class="date-picker" />
            </div>

            <button class="form__submit-button link">Publicar</button>
          </form>
        </div>
      </modal>
    </Transition>
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
.modal-info {
  width: 361px;
  left: 0;
  right: 0;
  margin-left: auto;
  margin-right: auto;
  bottom: 40%;
  display: flex;
  padding: 15px 10px;
  flex-direction: column;
  justify-content: flex-end;
  align-items: center;
  gap: 25px;
  align-self: stretch;
  border-radius: 6px;
}
.modal-info__button {
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

.modal-info__text {
  text-align: center;
  font-family: 'Baloo 2';
  font-size: 20px;
  font-weight: 600;
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
  /* overflow-x: visible;
  overflow-y: visible;
  transform: translateX(100%);
  transition: transform 0.3s ease; Include transform in the transition property */
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
</style>
