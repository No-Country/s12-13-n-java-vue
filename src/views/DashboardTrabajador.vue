<template>
    <main>
        <!--mobilebar-->
        <section>
            <div class="navbar-div">
                <div>
                    <img src="../assets/images/12_19.png" alt="hora">
                </div>   
                
                <div class="referencias">
                    <img src="../assets/images/iconosmobilebar.svg" alt="frecuencia, wifi, bateria">
                </div>
            </div>
        </section>    
        <!--navbar-->
        <section>
            <div>
                <div class="navbar-div">
                    <div class="isotipo">
                        <div>
                            <img src="../assets/images/isotipo.svg" alt="una mano">
                            <img src="../assets/images/una mano.png" alt="una mano texto">
                        </div>      
                    </div>
                    <div clas="menuhamburguesa">
                        <div>
                            <img src="../assets/images/frame.svg" alt="menú hamburguesa">
                        </div>
                    </div>
                </div>
            </div>
            <!--navbar trabajador-->
            <div class="navbar-trabajador">
                <img src="../assets/images/navbar icon component (5).svg" alt="ícono home">
                <img src="../assets/images/navbar icon component.svg" alt="ícono chat">
                <img src="../assets/images/iconocalendario.png" alt="ícono calendario">
            </div>
        </section>    

        <section class="fondo-card">
            <div class="tarjeta-acordion">
                <h3>Publicaciones activas</h3>
            </div>
            <!--Card trabajador-->
            <section class="tarjeta-acordion">
                <div class="accordion" id="accordionExample">
                    <div class="accordion-item">
                            <h2 class="accordion-header">
                            <button class="accordion-button" type="button" data-bs-toggle="collapse" data-bs-target="#collapseOne" aria-expanded="true" aria-controls="collapseOne">
                                <div>
                                    <strong><p class="kinesiologia">Kinesiología</p></strong>
                                </div>
                            </button>
                            </h2>
                            <div id="collapseOne" class="accordion-collapse collapse show" data-bs-parent="#accordionExample">
                            <div class="accordion-body">
                                <div>
                                    <div>
                                        <strong><h4>Sesiones semanales</h4></strong>
                                    </div> 
                                    <div class="tarea-de-florencia">
                                        <div class="perfil-florencia">
                                            <img src="../assets/images/contratadoraflorenciar.png" alt="contratadora">
                                            <div>
                                                <p><strong>Florencia R.</strong></p>
                                                <p class="contratador">Contratador</p>
                                            </div>  
                                        </div>    
                                        <div class="info-florencia"> 
                                                <div>
                                                    <div> 
                                                        <div>  
                                                            <div>      
                                                                <img src="../assets/images/estrellacompleta.svg" alt="estrella completa">
                                                                <img src="../assets/images/estrellacompleta.svg" alt="estrella completa">
                                                                <img src="../assets/images/estrellacompleta.svg" alt="estrella completa">
                                                                <img src="../assets/images/estrellacompleta.svg" alt="estrella completa">
                                                                <img src="../assets/images/mediaestrella.svg" alt="media estrella">
                                                                <img src="../assets/images/4.5calificacion.svg" alt="4.5">
                                                            </div>
                                                        </div>   
                                                    </div>
                                                    <div>  
                                                        <img src="../assets/images/16 calificaciones.svg" alt="16 calificaciones">
                                                    </div>
                                                </div>    
                                            </div>
                                        </div>
                                    </div>      
                                       <!-- <div class="valoracion">  
                                            <div class="estrellas">
                                                <img src="../assets/images/estrellacompleta.svg" alt="estrella completa">
                                                <img src="../assets/images/estrellacompleta.svg" alt="estrella completa">
                                                <img src="../assets/images/estrellacompleta.svg" alt="estrella completa">
                                                <img src="../assets/images/estrellacompleta.svg" alt="estrella completa">
                                                <img src="../assets/images/mediaestrella.svg" alt="media estrella">
                                                <img src="../assets/images/4.5calificacion.svg" alt="4.5">
                                            </div>
                                            <div class="calificaciones">
                                                <img src="../assets/images/16 calificaciones.svg" alt="16 calificaciones">
                                            </div>
                                        </div>
                                    </div>
                                </div>-->
                                <ul>
                                    <div><strong>Fecha:</strong><span class="referencias">8/12/2023</span></div>
                                    <div><strong>Dirección:</strong><span class="referencias">Bogotá, Colombia</span></div>
                                    <div><strong>Precio:</strong><span class="referencias">30 USD</span></div>
                                    <div><strong>Descripción:</strong></div>
                                    <div>
                                        <div class="descripcion">
                                            <p>Necesito tres sesiones semanales a domicilio <br> durante las tardes para tratar una lesión a <br> la rodilla. Precio sugerido por sesión.</p>
                                        </div>
                                    </div>
                                </ul>
                            </div>
                        </div>
                    </div>
                </div>
            </section>
        </section>

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
        <section class="fondo-card"> 
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
        </section>
    </main>        
</template>

<script lang="js" setup>
import {ref} from 'vue'
/*import Footer from '../components/Footer.vue';
import SectionHeader from '../components/SectionHeader.vue';
import CardTrabajador from '../components/CardTrabajador.vue';
import {ref} from 'vue'*/
</script>

<style scoped>
.navbar-div{
    display:flex;
    justify-content: space-between;
    align-items: center;
    margin-bottom: 30px;
    margin-left: 50px;
    margin-right: 50px;
}

.isotipo{
    display:inline-block;
    align-items: center;
    margin: 0;
}

.navbar-trabajador{
    align-items: center;
    align-content: center;
    display: flex;
    justify-content: center;
    margin-bottom: 10px;
}

.menuhamburguesa{
    display: flex;
    align-items: center;
    justify-content: flex-end;
}

.referencias{
    align-content: right;
    display: flex;
    justify-content:flex-end;
}

.descripcion{
    text-align: justify;
    text-align-last: justify;
    line-height: 1.5;
    max-width: 300px;
    margin: 0 auto;
}

.tarjeta-acordion{
    margin-left: 50px;
    margin-right: 50px;
}

.kinesiologia{
    color:#1D3D8F;
    text-decoration: solid;
}

.tarea-de-florencia{
    display: flex;
    justify-content: space-between;
    padding: 10px;
}

.perfil-florencia{
    display:flex;
    align-items: center;
}

.info-florencia{
    display: flex;
    align-items: center;
}

.valoracion{
    display: flex;
    flex-direction: column;
    align-items:flex-end;
}

.estrellas{
    display: flex;
    margin-right: 10px;
}

.calificacion-container{
    justify-content: space-between;
}

.contratador{
    color:#149ED7;
}

.fondo-card{
    background-color: #D8DFF2;
}

.container-description h3 {
    color: #fff;
    margin-top: 48px;
    font-size: 50px;
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