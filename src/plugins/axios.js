import axios from 'axios'

export default axios.create({
  baseURL: 'https://unamanoapi.onrender.com/api/',
  headers: {
    'Content-Type': 'application/json'
  }
})
