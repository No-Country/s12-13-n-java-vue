<script setup>
import router from '@/router'
import SectionHeader from '../components/SectionHeader.vue'
import { ref, onMounted, computed } from 'vue'
import '@vuepic/vue-datepicker/dist/main.css'
import FooterPage from '@/components/SectionFooter.vue'
import NavigationDashboard from '../components/NavigationDashboard.vue'
import { categorias, currencies } from '../utils/constants'
import axios from '@/plugins/axios'
const token = localStorage.getItem('token')
const headers = {
  Authorization: `Bearer ${token}`
}

const isOpen = ref(false)
let isDialogOpen = ref(false)
const dialogRef = ref()
console.log('dialogRef:', dialogRef.value)
const isEditMode = ref(false)
let isCardExists = ref(true)
let actionName = ref('')
let taskTitle = ref('')
let category = ref('')
let currency = ref('')
let taskDescription = ref('')
let taskLocation = ref('')
let precio = ref(0)
let cards = ref(null)
const formRef = ref(null)

isCardExists = computed(() => {
  return !!(cards.value && cards.value.length)
})

isCardExists = computed(() => {
  return !!(cards.value && cards.value.length)
})

const openPopup = () => {
  currency.value = ''
  category.value = ''
  isEditMode.value = false
  isOpen.value = true
}

const closePopup = () => {
  isOpen.value = false
}

const fetchCards = async () => {
  await axios.get('task/published', { headers }).then((response) => {
    console.log('response:', response.data.content)
    cards.value = response.data.content.filter((card) => card.id > 0)
  })
}

onMounted(() => {
  fetchCards()
})

const openDialog = () => {
  console.log('openDialog:', openDialog)
  // dialogRef.value.show()
  isDialogOpen.value = 'true'
  console.log('onMounteddialogRef:', dialogRef.value)
}
</script>
<template>
  <main>
    <SectionHeader />
    <NavigationDashboard />

    <div class="cards-container">
      <section class="container" style="margin-bottom: 16px">
        <p>Publicaciones activas <strong>/ Postulaciones</strong></p>
      </section>
      <section class="modal-info" :style="{ padding: isCardExists ? '0' : '10px' }">
        <p class="modal-info__text" v-if="!isCardExists">
          Crea tu primera publicación y <br />
          conecta con trabajadores
        </p>
        <button class="modal-info__button link" @click="openPopup">Crear nueva publicación</button>
      </section>
    </div>

    <section class="section-blog">
      <!-- contenido de la segunda tarjeta -->
    </section>
    <Transition>
      <modal class="popup" v-if="isOpen" :class="{ open: isOpen }">
        <div class="popup__container">
          <div class="popup__header">
            <h3 class="popup__title">
              <span v-if="!isEditMode">Crear nueva publicación </span>
              <span v-if="isEditMode">Editar publicación</span>
            </h3>
            <button class="popup__close button" @click="closePopup">
              <img src="../assets/images/close-button-icon.svg" alt="Button Image" />
            </button>
          </div>
          <form
            ref="formRef"
            class="form"
            @submit.prevent="onSubmit({ taskTitle, taskDescription })"
          >
            <div class="form__labelBox">
              <label htmlFor="categoryName" class="form__labelText">
                Elige el tipo de servicio
              </label>
              <select
                class="form__select"
                id="categoryName"
                name="categoryName"
                v-model="category"
                :disabled="isEditMode"
                required
              >
                <option
                  class="form__optionText"
                  v-for="(cat, index) in categorias"
                  :value="cat"
                  :key="index"
                >
                  {{ cat }}
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
                :value="isEditMode ? editedCard.taskTitle : taskTitle"
                @input="(e) => (taskTitle = e.target.value)"
              />
            </div>
            <div class="form__labelBox">
              <label htmlFor="eventName" class="form__labelText">Descripción</label>
              <textarea
                class="form__textarea"
                type="select"
                name="eventName"
                :value="isEditMode ? editedCard.description : taskDescription"
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
                  :value="isEditMode ? editedCard.price : precio"
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

            <button v-if="!isEditMode" class="form__submit-button link">Publicar</button>
            <button
              v-if="isEditMode"
              @click="openDialog"
              type="button"
              class="form__delete-button link"
            >
              Eliminar
            </button>
            <button v-if="isEditMode" class="form__submit-button link">Guardar</button>
          </form>
        </div>
      </modal>
    </Transition>
    <modal class="dialog" ref="dialogRef" v-if="isDialogOpen" @close="isDialogOpen = false">
      <div class="popup__header">
        <h3>Eliminar publicación</h3>
        <button class="popup__close button" @click="isDialogOpen = false">
          <img src="../assets/images/close-button-icon.svg" alt="Button Image" />
        </button>
      </div>
      <p class="dialog-text">
        Al presionar confirmar eliminarás la de publicación de forma definitiva. Esta acción no se
        puede deshacer. ¿Quieres eliminar la publicación?
      </p>
      <p class="dialog-subtext">¿Quieres eliminar la publicación?</p>
      <div class="dialog-buttons">
        <button class="edit-button link">Volver a editar</button>
        <button class="form__delete-button delete-button link" @click="deleteTask">
          Confirmar
        </button>
      </div>
    </modal>
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
  padding-bottom: 30px;
}
.modal-info {
  width: 361px;
  left: 0;
  right: 0;
  margin-left: auto;
  margin-right: auto;
  /* bottom: 40%; */
  margin-bottom: -10px;
  display: flex;
  flex-direction: column;
  justify-content: flex-end;
  align-items: center;
  gap: 25px;
  align-self: stretch;
  border-radius: 6px;
  background-color: white;
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
  /* height: 736px; */
  background-color: rgba(0, 0, 0, 0.5);
  z-index: 1;
  display: flex;
  justify-content: center;
  align-items: center;
  border-radius: 12px;
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
  width: 100%;
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

.form__delete-button {
  display: flex;
  height: 51px;
  justify-content: center;
  align-items: center;
  padding: 10px;
  gap: 10px;
  border-radius: 6px;
  border: 2px solid var(--delete-error, #e20c0c);
  color: var(--delete-error, #e20c0c);
  font-family: 'Baloo 2';
  font-size: 20px;
  font-weight: 700;
  background-color: white;
  margin-bottom: -11px;
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

.dialog {
  z-index: 2;
  flex-direction: column;
  position: fixed;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
  width: 391px;
  background-color: white;
  display: flex;
  justify-content: center;
  align-items: flex-start;
  border-radius: 12px;
  box-shadow: 0px 4px 20.8px 0px rgba(16, 96, 217, 0.25);
  border: 1px solid grey;
  padding: 26px;
  font-family: 'Baloo 2';
  max-width: 500px;
}

.edit-button {
  border: 2px solid var(--delete-error, #e20c0c);
  background-color: transparent;
  color: var(--delete-error, #e20c0c);
  font-family: 'Baloo 2';
  font-weight: 700;
  padding: 10px;
  width: 100%;
  font-size: 20px;
  border: 2px solid var(--blue1, #1d3d8f);
  color: var(--blue1, #1d3d8f);
  border-radius: 6px;
}

.delete-button {
  border: 2px solid var(--delete-error, #e20c0c);
  background-color: transparent;
  color: var(--delete-error, #e20c0c);
  font-family: 'Baloo 2';
  font-weight: 700;
  padding: 10px;
  width: 100%;
}

.dialog-buttons {
  width: 100%;

  display: flex;
  flex-direction: column;
  gap: 8px;
}

.dialog-text {
  margin-top: 20px;
  margin-bottom: 20px;
}

.dialog-subtext {
  margin-bottom: 24px;
}
</style>
