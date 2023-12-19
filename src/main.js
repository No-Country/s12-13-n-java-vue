import VueSplide from '@splidejs/vue-splide'
import VueDatePicker from '@vuepic/vue-datepicker'
import '@vuepic/vue-datepicker/dist/main.css'
import 'bootstrap'
import 'bootstrap/dist/css/bootstrap.min.css'
import { OhVueIcon, addIcons } from 'oh-vue-icons'
import {
  CoTiktok,
  FaRegularEye,
  FaRegularEyeSlash,
  LaLinkedin,
  RiFacebookBoxLine,
  RiTwitterLine,
  SiInstagram
} from 'oh-vue-icons/icons'
import { createPinia } from 'pinia'
import piniaPluginPersistedstate from 'pinia-plugin-persistedstate'
import { createApp } from 'vue'
import './assets/main.css'
import '@mdi/font/css/materialdesignicons.css'
import '@fortawesome/fontawesome-free/css/all.css'

addIcons(
  FaRegularEyeSlash,
  FaRegularEye,
  LaLinkedin,
  RiFacebookBoxLine,
  SiInstagram,
  CoTiktok,
  RiTwitterLine
)

import App from './App.vue'
import router from './router'

// Vuetify
import 'vuetify/styles'
import { createVuetify } from 'vuetify'
import * as components from 'vuetify/components'
import * as directives from 'vuetify/directives'
import { fa } from 'vuetify/iconsets/fa'
import { aliases, mdi } from 'vuetify/lib/iconsets/mdi'

const vuetify = createVuetify({
  icons: {
    defaultSet: 'mdi',
    aliases,
    sets: {
      mdi,
      fa
    }
  },
  components,
  directives
})

const app = createApp(App)
const pinia = createPinia()

app.component('v-icon', OhVueIcon)
app.component('VueDatePicker', VueDatePicker)
app.use(VueSplide)
pinia.use(piniaPluginPersistedstate)
app.use(pinia)
app.use(router)
app.use(vuetify)

app.mount('#app')
