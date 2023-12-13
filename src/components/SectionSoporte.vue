<script setup></script>

<template>
  <section class="conteiner" id="support">
    <div class="row gx-0">
      <div class="col-sm-12">
        <div class="row">
          <div class="col-12 col-sm-6">
            <div class="d-flex justify-content-center">
              <img src="@/assets/images/empresaria-centro-llamadas.png" alt="empresaria" />
            </div>
          </div>
          <div class="col-12 col-sm-6">
            <div class="conteiner-form">
              <label for="name">Nombre</label>
              <input type="text" name="" id="name" />
              <label for="messaje">Mensaje</label>
              <textarea name="messaje" id="messaje" cols="30" rows="10"></textarea>
            </div>
          </div>
        </div>
      </div>
      <div class="col-sm-12">
        <div class="container-description">
          <h3>COMUNÍCATE CON NOSOTROS</h3>
          <button class="description-btn">whatsapp</button>
        </div>
      </div>
    </div>
  </section>
</template>

<style scoped>
.conteiner {
  padding-top: 48px;
  background-color: #1b9964;
  color: #fff;
}
.conteiner-form {
  display: flex;
  flex-direction: column;
  padding: 0px 32px;
}
.conteiner-form label {
  font-size: 20px;
   color: #fff;
}
.conteiner-form input,
.conteiner-form textarea {
  height: 40px;
  background-color: #fff;
  align-self: stretch;
  border-radius: 6px;
  font-size: 25px;
  border: 1px;
  
}
.conteiner-form textarea {
  height: 135px;
  font-size: 25px;
}
.container-description h3 {
  
  margin-top: 15px;
  font-size: 20px;
}
.container-description {
  margin-top: 30px;
  display: flex;
  padding: 0px 32px;
  justify-content: center;
  align-items: center;
  gap: 26px;
  flex-direction: column;
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

@media (min-width: 768px) {
  img {
    height: 400px;
  }
}
@media (min-width: 576px) {
  .col-sm-6 {
    align-self: center;
  }
}
</style>
