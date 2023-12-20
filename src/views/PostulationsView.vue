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
let isDialogOpen = ref(false)

let posts = ref([])

let isActive = ref(false)

const fetchCards = async () => {
  for (let page = 0; page <= 10; page++) {
    try {
      const response = await axios.get(`postulations/page/${page}`, { headers })
      const newPosts = response.data.response.content
      posts.value = [...posts.value, ...newPosts]
    } catch (error) {
      console.error(`Error fetching posts from page ${page}:`, error)
    }
  }
}

onMounted(() => {
  fetchCards()
})

const contract = async (clickedPost) => {
  clickedPost.isActive = !clickedPost.isActive
  posts.value.forEach((post) => {
    if (post !== clickedPost) {
      post.isDisabled = clickedPost.isActive
    }
  })
  await axios
    .put(
      `postulations/accept_postulation/`,
      {
        postulation_id: clickedPost.id,
        task_id: clickedPost.idTask
      },
      { headers }
    )
    .then((response) => {
      console.log('Response', response)
      filteredPosts()
      console.log('clickedpost:', clickedPost.status)
    })
    .catch((error) => {
      console.log('Error en form', error)
    })
  console.log('clickedpost:', clickedPost.status)

  isActive.value = posts.value.some((post) => post.isActive)
}

const confirmSubmit = async (task) => {
  console.log('fetchPosttask:', task)
  console.log('confirmations')
  isDialogOpen.value = false
  router.push({ name: 'dashboard' })

  if (task) {
    const id = task.id.toString()
    await axios
      .put(`task/completed/${id}`, {}, { headers })
      .then((response) => {
        console.log('fetchPostResponse', response)
      })
      .catch((error) => {
        console.log('fetchPostError en form', error)
      })
  }
}

const openDialog = () => {
  isDialogOpen.value = 'true'
}

const filteredPosts = (posts, id) => {
  const requiredPost = posts.filter((item) => item.idTask == id)
  requiredPost.forEach((post) => {
    console.log('post:', post)
    if (post.status === 'APPROVED') {
      console.log('post', post.status)
      post.isActive = true
      isActive.value = true
    }
  })
  return requiredPost
}
</script>
<template>
  <main>
    <SectionHeader />
    <NavigationDashboard />

    <div class="wrapper">
      <section class="container" style="margin-bottom: 16px" ref="passingData($route.query)">
        <p>Publicaciones activas <strong>/ Postulaciones</strong></p>
        <section class="card" :style="{ boxShadow: `0px 5px 0px 0px ${$route.query.color} inset` }">
          <div>
            <h3 class="card__category">{{ $route.query.category }}</h3>
            <p class="card__title">{{ $route.query.taskTitle }}</p>
          </div>
        </section>

        <section v-if="posts && posts.length" class="post-wrapper">
          <div
            class="post-container"
            v-for="post in filteredPosts(posts, $route.query.id) || posts"
            :key="post.id"
          >
            <div class="post">
              <div class="post-info">
                <div class="circle-container">
                  <img src="../assets/images/post-avatar.svg" alt="avatar" class="avatar" />
                </div>
                <div class="profile__info">
                  <p class="profile__name">{{ post.firstname }} {{ post.lastname.slice(0, 1) }}.</p>
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
              <button
                class="post-button link"
                :style="{
                  background: post.isActive ? '#1b9964' : post.isDisabled ? '#C7C7C7' : '#149ed7'
                }"
                @click="contract(post)"
                :disabled="post.isDisabled"
              >
                <span v-if="post.isActive">Contratado</span> <span v-else>Contratar</span>
              </button>
            </div>
          </div>
        </section>

        <button class="button-back link post-button" @click="router.back()">
          Volver a publicaciones
        </button>
        <button
          class="button-finalize link post-button"
          :style="{ background: isActive ? '#1b9964' : '#C7C7C7' }"
          :disabled="!isActive"
          @click="openDialog"
        >
          Finalizar publicación
        </button>
      </section>
    </div>
    <modal class="dialog" ref="dialogRef" v-if="isDialogOpen" @close="isDialogOpen = false">
      <div class="popup__header">
        <h3>Finalizar publicación</h3>
        <button class="popup__close button" @click="isDialogOpen = false">
          <img src="../assets/images/close-button-icon.svg" alt="Button Image" />
        </button>
      </div>
      <p class="dialog-text">
        Al presionar confirmar contaremos como finalizada la publicación. Esta acción no se puede
        deshacer.
      </p>
      <p class="dialog-subtext">¿Quieres finalizar la publicación?</p>
      <div class="dialog-buttons">
        <button class="edit-button link" @click="isDialogOpen = false">
          Volver a postulaciones
        </button>
        <button class="confirm-button link" @click="confirmSubmit($route.query)">Confirmar</button>
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
  min-height: 90px;
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

.post-container {
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

@media screen and(min-width: 768px){
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
.post-wrapper {
  display: flex;
  flex-direction: column;
  gap: 10px;
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

.confirm-button {
  background-color: transparent;
  background: var(--greensoft, #1b9964);
  font-family: 'Baloo 2';
  font-weight: 700;
  padding: 10px;
  width: 100%;
  color: var(--white, #fff);
}
</style>
