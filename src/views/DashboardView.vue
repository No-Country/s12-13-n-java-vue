<script setup>
import SectionHeader from '../components/SectionHeader.vue'
import { ref, onMounted, computed } from 'vue'
import Datepicker from '@vuepic/vue-datepicker'
import '@vuepic/vue-datepicker/dist/main.css'
import FooterPage from '@/components/SectionFooter.vue'
import JobCard from '@/components/JobCard.vue'
import useFormContratador from '@/stores/formContratador'
import { categorias, currencies } from '../utils/constants'
import axios from '@/plugins/axios'
const token = localStorage.getItem('token')
const headers = {
  Authorization: `Bearer ${token}`
}
import router from '@/router'
const chat = () => {
  router.push({ name: 'Chat' })
}

const date = ref()
const store = useFormContratador()

const activeItems = ref([false, false, false])
const isOpen = ref(false)
let isDialogOpen = ref(false)
const dialogRef = ref()
console.log('dialogRef:', dialogRef.value)
const isEditMode = ref(false)
let isCardExists = ref(true)
let actionName = ref('')
let profile = ref(null)
let taskTitle = ref('')
let category = ref('')
let currency = ref('')
let taskDescription = ref('')
let taskLocation = ref('')
let precio = ref(0)
let cards = ref(null)
const formRef = ref(null)

const editedCard = ref(null)
const onCardEdit = (card) => {
  isEditMode.value = true
  editedCard.value = card
  isOpen.value = true
  actionName.value = 'Editar publicación'
  taskLocation.value = editedCard.value.address.street

  currency.value = editedCard.value.currencyType
  date.value = editedCard.value.taskDate
  category.value =
    editedCard.value.occupations[0].occupationName.slice(0, 1).toUpperCase() +
    editedCard.value.occupations[0].occupationName.slice(1)
}

isCardExists = computed(() => {
  return !!(cards.value && cards.value.length)
})

isCardExists = computed(() => {
  return !!(cards.value && cards.value.length)
})

const toggleNavItem = (index) => {
  activeItems.value[index] = !activeItems.value[index]
}

const openPopup = () => {
  currency.value = ''
  date.value = ''
  taskLocation.value = ''
  category.value = ''
  isEditMode.value = false
  isOpen.value = true
}

const closePopup = () => {
  isOpen.value = false
}

const onSubmit = async () => {
  console.log('onSubmit:', onSubmit)
  if (isEditMode.value) {
    console.log('onSubmit:', isEditMode.value, currency.value)

    await axios
      .put(
        'task',
        {
          id: editedCard.value.id,
          taskTitle: taskTitle.value,
          description: taskDescription.value,
          price: precio.value,
          currencyType: currency.value,
          occupations: [
            {
              occupationName: category.value.toLowerCase()
            }
          ],
          taskDate: date.value,
          client: {
            id: 1
          },
          address: {
            street: taskLocation.value
          }
        },
        { headers }
      )
      .then((response) => {
        console.log('Response', response)
      })
      .catch((error) => {
        console.log('Error en form', error)
      })
  } else {
    console.log('ELSEonSubmit')
    await store.submit(
      taskTitle.value,
      taskDescription.value,
      precio.value,
      currency.value,
      [
        {
          occupationName: category.value.toLowerCase()
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
  }

  fetchProfileAndCards()
  formRef.value.reset()
  taskTitle.value = ''
  category.value = ''
  currency.value = ''
  taskDescription.value = ''
  taskLocation.value = ''
  precio.value = ''
  date.value = ''
  closePopup()
  editedCard.value = null
  actionName.value = 'Crear publicación'
}

const fetchProfileAndCards = async () => {
  try {
    const fetchProfileInfo = await axios.get('user/data', { headers })
    console.log('fetchProfileInfo.data:', fetchProfileInfo.data)
    profile.value = fetchProfileInfo.data

    const fetchPublishedCards = await axios.get('task/client/', {
      headers,
      params: {
        id: profile.value.id_client,
        status: 'PUBLISHED'
      }
    })

    console.log('fetchPostPublishedCards:', fetchPublishedCards)

    const fetchProgressCards = await axios.get('task/client/', {
      headers,
      params: {
        id: profile.value.id_client,
        status: 'INPROGRESS'
      }
    })
    console.log('fetchPostProgressCards:', fetchProgressCards)

    cards.value = [...fetchPublishedCards.data.content, ...fetchProgressCards.data.content]

    console.log('fetchPostcards.value:', cards.value)
  } catch (error) {
    console.error('Error fetching data:', error)
  }
}

fetchProfileAndCards()

onMounted(() => {
  fetchProfileAndCards()
  if (isEditMode.value && editedCard.value) {
    category.value = editedCard.value.occupations[0].occupationName
    taskLocation.value = editedCard.value.address.street
  }
})

const deleteTask = async () => {
  isDialogOpen.value = false
  const cardID = editedCard.value.id
  console.log('editedCard:', editedCard)
  console.log('id:', cardID)
  await axios.delete(`task/delete/${cardID}`, { headers }).then((response) => {
    console.log('response:', response)
  })
  closePopup()
  await fetchProfileAndCards()
}

const openDialog = () => {
  isDialogOpen.value = 'true'
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
              :class="{ active: !isActive }"
              src="../assets/images/history-icon.svg"
            />
            <p class="nav__item-text" :class="{ active: !isActive }">Historial</p>
          </div>
        </li>
      </ul>
    </nav>
    <div class="cards-container">
      <section class="container" style="margin-bottom: 16px">
        <p>Publicaciones activas</p>
        <div v-if="cards && cards.length" class="tasks-container">
          <div v-for="card in cards" :key="card.id">
            <JobCard
              :taskTitle="card.taskTitle"
              :taskDate="card?.taskDate?.slice(0, 10).replace(/-/g, '/')"
              :category="`${card.occupations[0].occupationName
                .slice(0, 1)
                .toUpperCase()}${card.occupations[0].occupationName.slice(1)}`"
              :color="card.occupations[0].color"
              :description="card.description"
              :price="card.price"
              :currencyType="card.currencyType"
              :taskLocation="card.address.street"
              :id="card.id"
              @onEdit="onCardEdit(card)"
            >
            </JobCard>
          </div>
        </div>
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
                :value="isEditMode ? editedCard.category : category"
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
                name="taskTitle"
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
              <label htmlFor="eventLocation" class="form__labelText">Ubicación</label>
              <img src="../assets/images/location-icon.svg" alt="" class="location-img" />

              <input
                class="form__input input-location"
                id="eventLocation"
                name="eventLocation"
                placeholder="Ingresa tu dirección"
                :value="taskLocation"
                @input="(e) => (taskLocation = e.target.value)"
                :disabled="isEditMode"
                required
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
        <button class="edit-button link" @click="isDialogOpen = false">Volver a editar</button>
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
  height: 527px;
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
  top: 73px;
  left: 50%;
  transform: translateX(-50%);
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
