<script setup>
import Carrousel from '@/components/CarrouselBlog.vue'
import router from '@/router'
  const login = ()=>{
    router.push({ name: 'login' })
  }
</script>
<template>
  <section class="conteiner">
    <div class="row gx-0">
      <div class="col-sm-12 col-md-12">
           <Carrousel />
      </div>
      <div class="col-sm-12 col-md-12">
        <div class="container-description">
          <h3>TRABAJA CUIDANDO</h3>
          <button @click="login" class="description-btn">Comienza a trabajar</button>
        </div>
      </div>
    </div>
  </section>
</template>

<style scoped>
.container-description h3 {
  color: #fff;
  margin-top: 48px;
}
.container-description {
 
  display: flex;
  padding: 0px 32px;
  justify-content: center;
  align-items: center;
  gap: 26px;
  flex-direction: column;
}
.container-description {
  background-color: #1d3d8f;
}

.description-btn {
  width: 328px;
  height: 51px;
  padding: 10px;
  border-radius: 6px;
  border: solid 3px #fff;
  background-color: transparent;
  color: white;
  font-size: 20px;
  margin-bottom: 48px;
}

@media screen and(min-width: 768px){
  .container{
    font-size: 14px;
  }
}
</style>
