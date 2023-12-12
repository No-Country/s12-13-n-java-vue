<script setup></script>

<template>
  <p>Publicaciones activas</p>
  <section class="card">
    <h3 class="card__category">Kinesiólogía</h3>
    <p class="card__title">Sesiones semanales</p>
    <div class="container">
      <div class="date-container">
        <p class="date__title">Fecha:</p>
        <p class="date__value">08/12/2023</p>
      </div>
      <button class="card__button link" @click="openPopup">Ver más</button>
    </div>
  </section>
</template>

<script>
export default {
  setup() {
    return {}
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
}
.container {
  display: flex;
  justify-content: space-between;
  padding: 0;
}

.date-container {
  margin-top: 8px;
}

.date__title {
  font-family: 'Baloo 2';
  font-weight: 700;
}

.date__value {
  font-family: 'Baloo 2';
}
.card__button {
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
  border-radius: 6px;
  background: var(--blue1, #1d3d8f);
  border: none;
}
.link:hover {
  cursor: pointer;
}
</style>
