import axios from 'axios';

export default axios.create({
  baseURL:'https://s12-13-n-java-vue-production.up.railway.app/api/',
  headers: {
    'Content-Type': 'application/json'
  }
})