<script setup>
  import FooterPage from '@/components/SectionFooter.vue'
</script>

<template>
  <section>

    <div class="conteiner " id="about-us">
      
      <div class="row gx-2">


          <div class="col">
            <div class="cards">
              <img src="@/assets/images/about-me/juan.png" alt="">
              <h6>Juan Seura</h6>
              <div>
                <span>Back</span>
                <v-icon name="la-linkedin" scale="1.5" />
              </div>
            </div>
          </div>
          
          <div class=" col">
            <div class="cards">
              <img src="@/assets/images/about-me/daniel.png" alt="">
              <h6>Daniel Z.</h6>
              <div>
                <span>Front</span>
                <v-icon name="la-linkedin" scale="1.5" />
              </div>
            </div>
          </div>
  
          <div class=" col">
            <div class="cards">
              <img src="@/assets/images/about-me/leandro.png" alt="">
              <h6>Leandro I.</h6>
              <div>
                <span>Front</span>
                <v-icon name="la-linkedin" scale="1.5" />
              </div>
            </div>
          </div>
  
          <div class=" col">
            <div class="cards">
              <img src="@/assets/images/about-me/felipe.png" alt="">
              <h6>Felipe Ruiz</h6>
              <div>
                <span>Ui-Ux</span>
                <v-icon name="la-linkedin" scale="1.5" />
              </div>
            </div>
          </div>
         
          <div class=" col">
            <div class="cards">
              <img src="@/assets/images/about-me/brian.png" alt="">
              <h6>Brian Diaz</h6>
              <div>
                <span>Back</span>
                <v-icon name="la-linkedin" scale="1.5" />
              </div>
            </div>
          </div>
          
          <div class=" col">
            <div class="cards">
              <img src="@/assets/images/about-me/david.png" alt="">
              <h6>David F.</h6>
              <div>
                <span>Back</span>
                <v-icon name="la-linkedin" scale="1.5" />
              </div>
            </div>
          </div>
  
          <div class=" col">
            <div class="cards">
              <img src="@/assets/images/about-me/naza.png" alt="">
              <h6>Nazareno S.</h6>
              <div>
                <span>Pm/Ui-Ux</span>
                <v-icon name="la-linkedin" scale="1.5" />
              </div>
            </div>
          </div>
          
          <div class=" col">
            <div class="cards">
              <img src="@/assets/images/about-me/enzo.png" alt="">
              <h6>Enzo V.</h6>
              <div>
                <span>Back</span>
                <v-icon name="la-linkedin" scale="1.5" />
              </div>
            </div>
          </div>
          
          <div class=" col">
            <div class="cards">
              <img src="@/assets/images/about-me/artem.png" alt="">
              <h6>Artem L.</h6>
              <div>
                <span>Front</span>
                <v-icon name="la-linkedin" scale="1.5" />
              </div>
            </div>
          </div>
        
        
      </div>
  
    </div>

    <div class="row gx-0">
    
      <div class="col">
        <FooterPage />
      </div>
    </div>
    
  </section>
</template>

<style scoped>
 .conteiner {
    margin-top: 30px;
    padding: 0px 32px;
  }

  .cards {
    display: flex;
    flex-direction: column;
    align-items: center;
  }
  .cards div span{
    margin-right: 25px;
  }

  @media screen and(min-width: 768px){
  .col{
    font-size: 14px;
  }

  .cards{
    font-size: 14px;
  }
}

</style>
