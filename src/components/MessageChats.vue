<script setup>
import { onMounted, ref, onUnmounted } from 'vue';
  import axios from '@/plugins/axios';
  import { Stomp } from '@stomp/stompjs';
  import * as SockJS from 'sockjs-client';

  const token = localStorage.getItem('token');
  const headers = {
    Authorization: `Bearer ${token}`,
  };

  const props = defineProps({
    chatRoomId: Number,
    username: String,
    message : String
  });

  const messages = ref([]);
  const stompClient = ref(null)
 

  const getMessage = async (roomId) => {
    try {
      const response = await axios.get(`/messages/${roomId}`, { headers });
      messages.value = response.data;
      console.log('variable', messages.value);
    } catch (error) {
      console.log('Problemas', error);
    }
  };

  const initConnenctionSocket = () => {
    const url = 'https://unamanoapi.onrender.com/chat-socket';
    const socket = new SockJS(url);
    stompClient.value = Stomp.over(socket)

   
  }
  const onSocketMessage = (evt) =>{
      
    const received = JSON.parse(evt.body);

  
    messages.value.push(received)
    }

    const joinRoom = (roomId) => {
      stompClient.value.connect({},()=> {
      stompClient.value.subscribe(`/topic/${roomId}`,onSocketMessage)
    })
  }
  const sendMessage = () => {
  const newMessage = {
    username: props.username,
    message: props.message,
    chatRoomId : props.chatRoomId
  };

  stompClient.value.send(`/app/chat/${props.chatRoomId}`, {}, JSON.stringify(newMessage));
  
};



  onMounted(() => {
    console.log('se ejecutó');
    getMessage(props.chatRoomId);
    initConnenctionSocket(props.roomId)
    joinRoom(props.chatRoomId)
    const handleMessageEvent = (roomId) => {
    sendMessage(roomId);
    
  }
  document.addEventListener('send-message', handleMessageEvent);
  });

  onUnmounted(() => {
    const handleMessageEvent = (roomId) => {
    sendMessage(roomId);
  }
  document.removeEventListener('send-message',handleMessageEvent);
});



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

