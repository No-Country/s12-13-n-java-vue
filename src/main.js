import VueSplide from '@splidejs/vue-splide';
import VueDatePicker from '@vuepic/vue-datepicker';
import '@vuepic/vue-datepicker/dist/main.css';
import "bootstrap";
import "bootstrap/dist/css/bootstrap.min.css";
import { OhVueIcon, addIcons } from "oh-vue-icons";
import { CoTiktok, FaRegularEye, FaRegularEyeSlash, LaLinkedin, RiFacebookBoxLine, RiTwitterLine, SiInstagram } from "oh-vue-icons/icons";
import { createPinia } from 'pinia';
import piniaPluginPersistedstate from 'pinia-plugin-persistedstate';
import { createApp } from 'vue';
import './assets/main.css';

addIcons(FaRegularEyeSlash, FaRegularEye, LaLinkedin, RiFacebookBoxLine, SiInstagram, CoTiktok, RiTwitterLine )

import App from './App.vue';
import router from './router';

const app = createApp(App)
const pinia = createPinia()

app.component('v-icon', OhVueIcon)
app.component('VueDatePicker', VueDatePicker)
app.use( VueSplide )
pinia.use(piniaPluginPersistedstate)
app.use(pinia)
app.use(router)

app.mount('#app')
