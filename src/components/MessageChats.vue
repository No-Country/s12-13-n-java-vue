<script setup>
  import { onMounted } from 'vue'
  import axios from '@/plugins/axios'
  const token = localStorage.getItem('token')
  const headers = {
    Authorization: `Bearer ${token}`
  }
 
  let messages = []


  const getMessage= async() =>{
    await axios
      .get(`/messages/1`, { headers })
      .then((response)=>{
        messages= response.data
        console.log('variable', messages)
      })
       .catch((error) => {
        console.log('Problemas', error)
      
      })
  }

  
    onMounted(() => {
     console.log('se ejecuto')
      getMessage()
    })


 
</script>

<template>

  <div  class="container">
    <div class="chat" v-for="(chat,index) in messages" :key="index">

      <p>{{chat.username}}</p>
      <p>{{chat.message}}</p>

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