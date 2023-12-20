<script setup>
import router from '@/router'
const props = defineProps({
  taskTitle: String,
  taskDate: String,
  category: String,
  color: String,
  description: String,
  taskLocation: String,
  price: String,
  currencyType: String,
  id: Number
})

const getPosts = () => {
  router.push({
    name: 'postulations',
    query: {
      category: props.category,
      taskTitle: props.taskTitle,
      color: props.color,
      id: props.id
    }
  })
}
</script>

<template>
  <sections
    class="card"
    :class="{ unexpanded: !isExpanded, expanded: isExpanded }"
    :style="{ boxShadow: `0px 5px 0px 0px ${props.color} inset` }"
  >
    <div class="container">
      <div>
        <h3 class="card__category">{{ props.category }}</h3>
        <p class="card__title">{{ props.taskTitle }}</p>
      </div>
      <button class="unexpand-button link" @click="expand()" :class="{ hidden: !isExpanded }">
        <img src="../assets/images/unexpand-icon.svg" alt="unexpand" />
      </button>
    </div>

    <div class="container">
      <div class="data-container" :class="{ container_expanded: isExpanded }">
        <p class="data__title">Fecha:</p>
        <p class="data__value">{{ props.taskDate }}</p>
      </div>
      <div
        class="data-container"
        :class="{
          container_expanded: isExpanded,
          hidden: !isExpanded,
          container_unrevealed: !isExpanded,
          container_revealed: isExpanded
        }"
      >
        <p class="data__title">Dirección:</p>
        <p class="data__value">{{ props.taskLocation }}</p>
      </div>
      <div
        class="data-container"
        :class="{
          container_expanded: isExpanded,
          hidden: !isExpanded,
          container_unrevealed: !isExpanded,
          container_revealed: isExpanded
        }"
      >
        <p class="data__title">Precio:</p>
        <p class="data__value">{{ props.price }} {{ props.currencyType }}</p>
      </div>
      <div
        class="description-container"
        :class="{
          hidden: !isExpanded,
          container_unrevealed: !isExpanded,
          container_revealed: isExpanded
        }"
      >
        <p class="data__title">Descripción:</p>
        <p class="data__value description-text">
          {{ props.description }}
        </p>
        <div class="container_expanded buttons-container" :class="{ hidden: !isExpanded }">
          <button class="edit-button link" @click="$emit('onEdit', props.id)">Editar</button>
          <!-- <button class="delete-button link" @click="deleteTask({ id })">Del</button> -->
          <button class="applications-button link" @click="getPosts">
            <div class="users-container">
              <!-- <span>3 </span> -->
              <img src="../assets/images/user-icon.svg" alt="user" class="applications-image" />
            </div>
            Postulaciones
          </button>
        </div>
      </div>
      <button class="expand-button link" :class="{ hidden: isExpanded }" @click="expand()">
        Ver más
      </button>
    </div>
  </sections>
</template>

<script>
export default {
  // props: {
  //   taskTitle: String,
  //   id: Number
  // },
  data() {
    return {
      isExpanded: false
    }
  },
  methods: {
    expand() {
      this.isExpanded = !this.isExpanded
    }
  }
}
</script>

<style scoped>
h3,
p {
  margin: 0;
  padding: 0;
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

.card.expanded {
  min-height: 240px;
  animation: expand 0.5s ease;
}

.description-text {
  width: fit-content;
}
.card.unexpanded {
  min-height: 134px;
  animation: unexpand 0.5s ease;
}

.container_unrevealed {
  opacity: 0;
  animation: unreveal 0.5s ease;
}
.container_revealed {
  opacity: 1;
  animation: reveal 0.7s ease;
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
.container {
  display: flex;
  justify-content: space-between;
  padding: 0;
  flex-wrap: wrap;
}

/*.data-container {
}*/

.container_expanded {
  width: 100%;
  display: flex;
  justify-content: space-between;
}

.data__title {
  font-family: 'Baloo 2';
  font-weight: 700;
}

.data__value {
  font-family: 'Baloo 2';
}
.expand-button {
  color: var(--white, #fff);
  font-family: 'Baloo 2';
  font-size: 20px;
  font-weight: 700;
  display: flex;
  height: 51px;
  padding: 10px;
  min-width: 123px;
  justify-content: center;
  align-items: center;
  background: var(--blue1, #1d3d8f);
}
.link {
  cursor: pointer;
  border: none;
  border-radius: 6px;
  font-family: 'Baloo 2';
  font-size: 20px;
}

.link:hover {
  cursor: pointer;
}

.applications-button {
  background: var(--blue1, #1d3d8f);
  color: var(--white, #fff);
  width: 227px;
  gap: 10px;
  display: flex;
  justify-content: center;
  align-items: center;
}

.section-container {
  display: flex;
  justify-content: space-between;
  padding: 0;
}

.unexpand-button {
  width: 27px;
  height: 27px;
  background-color: transparent;
}

.hidden {
  display: none;
}
.buttons-container {
  display: flex;
  gap: 12px;
  margin-top: 23px;
  max-width: 361px;
}

.users-container {
  display: flex;
  align-items: center;
}

.edit-button {
  border: 2px solid var(--blue1, #1d3d8f);
  background-color: transparent;
  color: var(--blue1, #1d3d8f);
  font-family: 'Baloo 2';
  font-weight: 700;
  padding: 10px;
  width: 100px;
}

@keyframes expand {
  0% {
    min-height: 134px;
  }
  100% {
    min-height: 300px;
  }
}
@keyframes unexpand {
  0% {
    min-height: 300px;
  }
  100% {
    min-height: 134px;
  }
}

@keyframes reveal {
  0% {
    opacity: 0;
  }
  100% {
    opacity: 1;
  }
}
@keyframes unreveal {
  0% {
    opacity: 1;
  }
  100% {
    opacity: 0;
  }
}

@media screen (min-width: 768px){
  .card{
    font-size: 14px;
  }
  .container{
    font-size: 14px;
  }

  .data-container{
    font-size: 14px; 
  }

  
  .description-container{
    font-size: 14px;
  }
  .applications-button link{
    font-size: 14px;
  }
}
</style>
