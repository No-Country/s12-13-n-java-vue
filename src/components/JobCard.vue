<script setup></script>

<template>
  <section class="card">
    <h3 class="card__category">Kinesiólogía</h3>
    <p class="card__title">Sesiones semanales</p>
    <div class="date-container">
      <p class="date__title">Fecha:</p>
      <p class="date__value">08/12/2023</p>
    </div>
    <button class="card__button link" @click="openPopup">Ver más</button>
  </section>
</template>

<script>
export default {
  setup() {
    return {}
  }
}
</script>

<style lang="scss" scoped></style>
