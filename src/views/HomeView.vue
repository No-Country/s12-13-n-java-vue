<script setup>
  import SectionInfo from '@/components/SectionInfo.vue'
  import SectionTutorial from '@/components/SectionTutorial.vue'
  import SectionBlog from '@/components/SectionBlog.vue'
  import SectionSoporte from '@/components/SectionSoporte.vue'



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
    
    <SectionInfo />
    <SectionTutorial />
    <SectionBlog />
    <SectionSoporte />
   
  </main>
</template>

<style scoped>
 
 
</style>
