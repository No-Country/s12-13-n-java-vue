<!-- eslint-disable no-unused-vars -->
<script setup>
import SectionHeader from '@/components/SectionHeader.vue'
import { ref, onMounted } from 'vue'
import '@vuepic/vue-datepicker/dist/main.css'
import FooterPage from '@/components/SectionFooter.vue'

/*const token = localStorage.getItem('token')
const user = localStorage.getItem('userData')
const headers = {
  Authorization: `Bearer ${token}`
}*/

</script>


<template>
  <main>
    <SectionHeader />
    <div class="cards-container">
      <section class="container">
        <div>
          <p>Historial</p>
        </div>         
      </section>


    </div>

    <FooterPage />
  </main>
</template>

<style scoped>
@media screen and(min-width: 768px){
  .container{
    font-size: 14px;
  }
  
  .container{
    font-size: 14px; 
  }
}
</style>
