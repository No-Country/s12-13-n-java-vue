import VueStomp from 'vue-stomp';

export default {
  data() {
    return {
      stomp: null,
      message: '',
      chatMessages: [],
    };
  },
  mounted(roomId) {
    // Configuración de la conexión Stomp
    this.stomp = new VueStomp({
      debug: false,
      reconnectDelay: 5000,
      connection: 'https://unamanoapi.onrender.com/chat-socket', // URL del servidor Stomp
      subscribe:  `/topic/chat/${roomId}`, // Topic al cual te suscribes (roomId se actualizará dinámicamente)
      callback: this.handleMessage, // Función para manejar los mensajes recibidos
    });

    // Inicia la conexión
    this.stomp.start();
  },
  beforeDestroy() {
    // Cierra la conexión al destruir el componente
    if (this.stomp) {
      this.stomp.stop();
    }
  },
  methods: {
    handleMessage(message) {
      // Maneja el mensaje recibido
      console.log('Mensaje recibido:', message.body);
      // Puedes hacer lo que necesites con el mensaje, como agregarlo a un array de mensajes
      this.chatMessages.push(JSON.parse(message.body));
    },
    sendMessage(chatId, usernamechat) {
      // Método para enviar un mensaje al servidor
      const roomId = chatId; // Puedes obtener el ID de la sala de chat de alguna manera
      const message = {
        message: this.message,
        username: usernamechat, // Puedes obtener el nombre de usuario de alguna manera
        chatRoomId: roomId,
      };

      
      this.stomp.send(`/app/chat/${roomId}`, JSON.stringify(message));
      this.message = '';
    },
  },
};



