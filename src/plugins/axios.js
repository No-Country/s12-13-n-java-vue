import axios from 'axios'

export default axios.create({
  baseURL:'http://localhost:3333/api/v1', //Colocar endpoint de la API
  headers: {
    'Content-Type': 'application/json'
  }
})