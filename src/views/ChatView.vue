<!-- eslint-disable no-unused-vars -->
<script setup>
import SectionHeader from '../components/SectionHeader.vue'
import MessageChats from '@/components/MessageChats.vue'
import { ref } from 'vue'
import '@vuepic/vue-datepicker/dist/main.css'
import FooterPage from '@/components/SectionFooter.vue'
import ChatCard from '@/components/ChatCard.vue'
import router from '@/router'
import axios from '@/plugins/axios'
const token = localStorage.getItem('token')
const headers = {
  Authorization: `Bearer ${token}`
  }
  const dashboard = ()=>{
    router.push({ name: 'dashboard' })
  }

let id= ref('')
let name= ref('')
let clientName= ref ('')
let workerName= ref('')
let chats= ref (null)


const fetchChat = async () => {
  await axios.get('chats', { headers }).then((response) => {
    console.log('response:', response.data)

    chats.value = response.data.filter((chat) => chat.id > 0)
    
  })
}

fetchChat () 

const date = ref()
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

    <section class="container">
      <div v-if="chats && chats.length" class="tasks-container">
          <div v-for="chat in chats" :key="chat.id">
            <button @click="openPopup">
              <ChatCard 
              :name="chat.name"
              :clientName="chat.clientName"
              :workerName="chat.workerName"
              :id="chat.id"
              @click="openPopup"/> 
            </button>
          </div>
      </div>    
    </section>
    <section class="section-blog">
      <!-- contenido de la segunda tarjeta -->
    </section>
    <Transition>
      <modal class="popup" v-if="isOpen" :class="{ open: isOpen }">
        <div class="popup__container">
          <div class="popup__header">
            <h3 class="popup__title"></h3>
            <img class="me-3" src="" alt="la foto">
                <div>
                  <p>Daniela Martinez</p>
                  <small>Transporte desde el hospital..</small>
                </div>
            <button class="popup__close button" @click="closePopup">
              <img src="../assets/images/close-button-icon.svg" alt="Button Image" />
            </button>

          </div>
          <form class="form">
            <MessageChats />
          </form>
          <div class="d-flex justify-content-between">
            <input style="background-color: #a9b8de;flex: auto" type="text">
            <v-icon scale="1.5" name="ri-send-plane-fill" style="cursor: pointer;"/>
          </div>
        </div>
      </modal>
    </Transition>

    <FooterPage />
  </main>
</template>

<script>
export default {
  data() {
    return {
      activeItems: [false, false, false],
      isOpen: false
    }
  },
  methods: {
    toggleNavItem(index) {
      /* this.$set(this.activeItems, index, !this.activeItems[index]) */
    },
    openPopup() {
      console.log('isOpen:')
      this.isOpen = true
    },
    closePopup() {
      this.isOpen = false
    }
  }
}
</script>

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
  background-color: #a9b8de;
  width: 100%;
  height: 530px;
}

.popup {
  position: fixed;
  top: 0;
  left: 0;
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

/*media queries*/

@media screen (min-width: 768px){
  .container{
    font-size: 14px;
  }
  .nav{
    font-size: 14px;
  }

  .popup{
    font-size: 14px; 
  }
}
</style>
