<template>
    <main>
        <!--mobilebar-->
        <div class="referencias">
            <img src="../assets/images/iconosmobilebar.svg" alt="frecuencia, wifi, bateria">
        </div>
        <!--navbar-->
        <div>
            <div>
                <div class="isotipo">
                    <div>
                        <img src="../assets/images/isotipo.svg" alt="una mano">
                    </div>
                    <div>
                        <img src="../assets/images/una mano.png" alt="una mano texto">
                    </div>
                </div>
                <div class="referencias">
                    <img  src="../assets/images/frame.svg" alt="menú hamburguesa">
                </div>
            </div>
        </div>
        <!--navbar trabajador-->
        <div class="navbar-trabajador">
            <img src="../assets/images/home-icon.svg" alt="ícono home">
            <img src="../assets/images/iconochat.svg" alt="ícono chat">
            <img src="../assets/images/iconocalendario.png" alt="ícono calendario">
        </div>

        <div>
            <h3>Publicaciones activas</h3>
        </div>
        <!--Card trabajador-->
        <div class="accordion" id="accordionExample">
            <div class="accordion-item">
                    <h2 class="accordion-header">
                    <button class="accordion-button" type="button" data-bs-toggle="collapse" data-bs-target="#collapseOne" aria-expanded="true" aria-controls="collapseOne">
                        <strong>Kinesiología</strong>
                    </button>
                    </h2>
                    <div id="collapseOne" class="accordion-collapse collapse show" data-bs-parent="#accordionExample">
                    <div class="accordion-body">
                        <strong><h4>Sesiones semanales</h4></strong> 
                        <p v-bind:style="{'justify-content': alineacion, 'align-items':alineacion,'display': inline}">8/12/2023</p>
                        <ul>
                            <div><strong>Fecha:</strong></div><div class="referencias">8/12/2023</div>
                            <div><strong>Dirección:</strong></div> <div class="referencias">Bogotá, Colombia</div>
                            <div><strong>Precio:</strong></div> <div class="referencias">30 USD</div>
                            <div><strong>Descripción:</strong></div>
                            <div>
                                <p>Necesito tres sesiones semanales a domicilio durante las tardes para tratar una lesión a la rodilla. Precio sugerido por sesión.</p>
                            </div>
                        </ul>
                    </div>
                    </div>
            </div>
        </div>

        <!--<li class="nav__item link" @click="toggleNavItem">
          <div class="nav__item-container">
            <img
              class="nav__item-image"
              :class="{ active: isActive }"
              src="../assets/images/home-icon.svg"
            />
            <p class="nav__item-text" :class="{ active: isActive }">Inicio</p>
          </div>
        </li>-->
            
        <!--footer-->    
        <div class="container-description ">
    
            <h3 class="mb-3">REDES</h3>

            <div class="mb-3">
                <v-icon class="me-3" fill="white" scale="1.5" name="ri-facebook-box-line" />
                <v-icon class="me-3" fill="white" scale="1.5" name="si-instagram" />
                <v-icon class="me-3" fill="white" scale="1.5" name="co-tiktok" />
                <v-icon fill="white" scale="1.5" name="ri-twitter-line" />
            </div>

            <div class="foot">
                <small style="color: #fff;">copyright © 2023 unamano</small>
                <small style="color: #fff;"> seleccionado 12</small>
            </div>
        </div>
    </main>        
</template>

<script lang="js" setup>
import {ref} from 'vue'
let alineacion = ref('right')
/*import Footer from '../components/Footer.vue';
import SectionHeader from '../components/SectionHeader.vue';
import CardTrabajador from '../components/CardTrabajador.vue';
import {ref} from 'vue'*/
</script>

<style scoped>
.isotipo{
    display:flex;
    align-items: center;
    margin: 0;
}

.navbar-trabajador{
    align-items: center;
    align-content: center;
    display: flex;
    justify-content: center;
}

.referencias{
    align-content: right;
    display: flex;
    justify-content: right;
}

.container-description h3 {
    color: #fff;
    margin-top: 48px;
    font-size: 20px;

  }
 
  .container-description {
    display: flex;
    background-color: #1D3D8F;
    flex-direction: column;
    align-items: center;
    text-align: center
  }

  .foot {
    display: flex;
    flex-direction: column;
  }

  @media (min-width: 768px){
    .foot{
      display: flex;
      flex-direction: row;
    }
  }
</style>