import axios from 'axios';

export default axios.create({
  baseURL:'https://unamanoapi.onrender.com/api/', //Colocar endpoint de la API
  headers: {
    'Content-Type': 'application/json'
  }
})