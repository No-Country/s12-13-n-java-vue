<script setup>
import axios from '@/plugins/axios'
const token = localStorage.getItem('token')
const headers = {
  Authorization: `Bearer ${token}`
}



//POST POSTULATION
function postulation(id) {
  console.log('task_id ->:', id )

  /*axios.delete(`task/${id}`, { headers }).then((response) => {
    console.log('response:', response)
  })*/
}



const props = defineProps({
  taskTitle: String,
  taskDate: String,
  category: String,
  description: String,
  address: String,
  price: String,
  currencyType: String,
  id: Number
})
</script>

<template>
  <section
    class="card"
    :class="{ unexpanded: !isExpanded, expanded: isExpanded, 'z-1000': isExpanded }">

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
        <p class="data__value">{{ props.address }}</p>
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
           
              <button class="postulation-button link" @click="postulation({ id })">
                Postular
              </button>
        </div>

  
      </div>
        <button class="expand-button link" :class="{ hidden: isExpanded }" @click="expand()">
          Ver más
        </button>
    </div>


  </section>
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
    },
  }


}
</script>

<style scoped>
h3,
p {
  margin: 0;
  padding: 0;
}

.eventButtonTrash {
  background: url(../assets/images/trashbag-icon.svg);
  background-size: contain;
  width: 20px;
  height: 20px;
  appearance: auto;
  border: initial;
  outline: initial;
  cursor: pointer;
  position: absolute;
  right: 70px;
  top: 21px;
}

.card {
  display: flex;
  min-height: 134px;
  padding: 16px;
  flex-direction: column;
  align-items: flex-start;
  border-radius: 6px;
  background: #fff;
  box-shadow: 0px 5px 0px 0px #4dc9ff inset;
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
  font-size: 20px;
  font-weight: 600;
}

.card__title {
  white-space: nowrap;
  font-family: 'Baloo 2';
  font-size: 20px;
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



.postulation-button {
  width: 200px;
  color: var(--white, #fff);
  font-family: 'Baloo 2';
  font-size: 20px;
  padding: 10px;
  display: flex;
  align-items: center;
  justify-content: center;
  background: var(--blue1, #1d3d8f);
}


.link {
  cursor: pointer;
  border: none;
  border-radius: 6px;
}

.link:hover {
  cursor: pointer;
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

.delete-button {
  border: 2px solid var(--delete-error, #e20c0c);
  background-color: transparent;
  color: var(--delete-error, #e20c0c);
  font-family: 'Baloo 2';
  font-weight: 700;
  padding: 10px;
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

.z-1000 {
  z-index: 1000;
}

.buttons-container {
  display: flex;
  gap: 12px;
  margin-top: 23px;
}

.users-container {
  display: flex;
  align-items: center;
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
</style>
