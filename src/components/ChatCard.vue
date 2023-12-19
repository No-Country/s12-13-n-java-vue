<script setup></script>

<template>
  <p>Chats Activos</p>
  <section
    class="card"
    :class="{ unexpanded: !isExpanded, expanded: isExpanded, 'z-1000': isExpanded }"
  >
    <div class="container">
      <div>
        <h3 class="card__category">Nombre Trabajador</h3>
        <p class="card__title">Titulo de la tarea</p>
      </div>
      <button class="unexpand-button link" @click="expand()" :class="{ hidden: !isExpanded }">
        <img src="../assets/images/unexpand-icon.svg" alt="unexpand" />
      </button>
    </div>

    <div class="container">
      <div class="data-container" :class="{ container_expanded: isExpanded }">
        <p class="data__title">icono</p>
        <p class="data__value">08/12/2023</p>
      </div>
      <div class="data-container" :class="{ container_expanded: isExpanded, hidden: !isExpanded }">
        <p class="data__title">Dirección:</p>
        <p class="data__value">Bogotá, Colombia</p>
      </div>
      <div class="data-container" :class="{ container_expanded: isExpanded, hidden: !isExpanded }">
        <p class="data__title">Precio:</p>
        <p class="data__value">30 USD</p>
      </div>
      <div class="description-container" :class="{ hidden: !isExpanded }">
        <p class="data__title">Descripción:</p>
        <p class="data__value description-text">
          Necesito 3 sesiones semanales a domicilio durante las tardes para tratar una lesión a la
          rodilla. Precio sugerido por sesión.
        </p>
      </div>
    </div>
  </section>
</template>

<script>
export default {
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

.card.unexpanded {
  min-height: 134px;
  animation: unexpand 0.5s ease;
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

.applications-button {
  background: var(--blue1, #1d3d8f);
  color: var(--white, #fff);
  width: 100%;
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
    min-height: 340px;
  }
}
@keyframes unexpand {
  0% {
    min-height: 340px;
  }
  100% {
    min-height: 134px;
  }
}
</style>
