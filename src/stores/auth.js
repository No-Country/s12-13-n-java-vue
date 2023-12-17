import axios from '@/plugins/axios'
import { defineStore } from 'pinia'

const useAuth = defineStore('auth', {
  state: () => ({
    token: null,
    rol: null,
    feedback: '',
    feedbackError: ''
  }),

  actions: {
    async login(username, password, tipo) {
      this.reset();

      // Definir constantes para los roles
      const CONTRATADOR_ROLE = 0;
      const TRABAJADOR_ROLE = 1;

      let role = tipo === 'contratador' ? CONTRATADOR_ROLE : TRABAJADOR_ROLE;

      try {
        
        // Realizar la solicitud de inicio de sesión
        const response = await axios.post('auth/login', { username, password, role });

        // Verificar si la respuesta es exitosa
        if (response.status === 200) {
      
          const { token } = response.data;

          this.token = token;
          localStorage.setItem('token', token);

          this.notification('Acceso correcto');

          // Obtener información del usuario
          const headers = { Authorization: `Bearer ${this.token}` };
          const userData = await axios.get('user/data', { headers });

          // Verificar si la respuesta es exitosa
          if (userData.status === 200) {
            // Almacenar la información del usuario en localStorage
            localStorage.setItem('userData', JSON.stringify(userData.data));
          }
        }
      } catch (error) {
        // Manejo de errores simplificado
        console.error('Error al obtener el token o la información del usuario:', error);
        // Notificación de error
        this.notificationError(error.response?.data?.error || 'Error desconocido');
      }
    },

    notification(message) {
      this.feedback = message;
      setTimeout(() => {
        this.feedback = '';
      }, 4000);
    },

    notificationError(message) {
      this.feedbackError = message;
      setTimeout(() => {
        this.feedbackError = '';
      }, 4000);
    },

    reset() {
      localStorage.removeItem('token');
      localStorage.removeItem('userData');
      this.token = null;
      this.rol = null;
      this.feedback = '';
      this.feedbackError = '';
    }
  },
});

export default useAuth;
