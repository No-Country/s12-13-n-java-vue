<script setup>
  import { onMounted, ref } from 'vue'
  import axios from '@/plugins/axios'

  const token = localStorage.getItem('token')
  const headers = {
    Authorization: `Bearer ${token}`
  }

  const props = defineProps({
  roomId :String
})
  const messages = ref([])

 
  const getMessage = async (roomId) => {
    try {
      const response = await axios.get(`/messages/${roomId}`, { headers })
      messages.value = response.data
      console.log('variable', messages.value)
    } catch (error) {
      console.log('Problemas', error)
    }
  }

  onMounted(() => {
    console.log('se ejecutó')
    getMessage(props.roomId)
  })

  console.log(props.roomId)
</script>

<template>
  <div class="container">
    <div class="chat" v-for="(chat, index) in messages" :key="index">
      <p>{{ chat.username }}</p>
      <p>{{ chat.message }}</p>
    </div>
  </div>
</template>

<style scoped>
  .chat {
    border-radius: 12px;
    border: 1px solid black;
    background-color: white;
    padding: 10px;
    margin-bottom: 15px;
    margin-top: 10px;
  }
</style>
