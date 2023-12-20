<script setup>
import SectionInfo from '@/components/SectionInfo.vue'
import SectionTutorial from '@/components/SectionTutorial.vue'
import SectionBlog from '@/components/SectionBlog.vue'
import SectionSoporte from '@/components/SectionSoporte.vue'
import SectionHeader from '../components/SectionHeader.vue'
import SectionNosotros from '../components/SectionNosotros.vue'

//pasar props
// defineProps({
//   msg: {
//     type: String,
//     required: true
//   }

// })
</script>

<template>
  <main>
    <SectionHeader />
    <SectionInfo />
    <SectionTutorial />
    <SectionBlog />
    <SectionSoporte />
    <SectionNosotros />
  </main>
</template>

<style scoped>
@media screen (min-width: 768px){
  .container{
    font-size: 14px;
  }
  .nav{
    font-size: 14px;
  }
}
</style>
